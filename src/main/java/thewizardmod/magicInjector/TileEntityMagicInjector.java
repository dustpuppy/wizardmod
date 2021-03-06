package thewizardmod.magicInjector;

import java.util.Arrays;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import thewizardmod.items.StartupCommon;

public class TileEntityMagicInjector extends TileEntity implements IInventory, ITickable {
	// Create and initialize the itemStacks variable that will store store the itemStacks
	public static final int FUEL_SLOTS_COUNT = 1;
	public static final int INPUT_SLOTS_COUNT = 1;
	public static final int OUTPUT_SLOTS_COUNT = 1;
	
	public static final int TOTAL_SLOTS_COUNT = FUEL_SLOTS_COUNT + INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

	public static final int FIRST_FUEL_SLOT = 0;
	public static final int FIRST_INPUT_SLOT = FIRST_FUEL_SLOT + FUEL_SLOTS_COUNT;
	public static final int FIRST_OUTPUT_SLOT = FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT;
	

	private ItemStack[] itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];

	/** The number of burn ticks remaining on the current piece of fuel */
	public int [] burnTimeRemaining = new int[FUEL_SLOTS_COUNT];
	/** The initial fuel value of the currently burning fuel (in ticks of burn duration) */
	private int [] burnTimeInitialValue = new int[FUEL_SLOTS_COUNT];

	/**The number of ticks the current item has been cooking*/
	private short cookTime;
	/**The number of ticks required to cook an item*/
	public short COOK_TIME_FOR_COMPLETION = 200;  // vanilla value is 200 = 10 seconds

	private int cachedNumberOfBurningSlots = -1;
	
	public static ItemStack fuelInUse = null;
	
	public long lastChangeTime;

	public static ItemStack outputItem = null;
	
	public int facing;
	
	public int getFacing()
	{
		return facing;
	}
	
	public void setFacing(int facing)
	{
		this.facing = facing;
		markDirty();
	}
	

	public Item getItem()
	{
		if(outputItem != null)
		{
			return outputItem.getItem();
		}
		return null;
	}
	
	public Item getFuelInUse()
	{
		if(fuelInUse != null)
		{
			return fuelInUse.getItem();
		}
		return null;
	}
	/**
	 * Returns the amount of fuel remaining on the currently burning item in the given fuel slot.
	 * @fuelSlot the number of the fuel slot (0..3)
	 * @return fraction remaining, between 0 - 1
	 */
	public double fractionOfFuelRemaining(int fuelSlot)
	{
		if (burnTimeInitialValue[fuelSlot] <= 0 ) return 0;
		double fraction = burnTimeRemaining[fuelSlot] / (double)burnTimeInitialValue[fuelSlot];
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}

	/**
	 * return the remaining burn time of the fuel in the given slot
	 * @param fuelSlot the number of the fuel slot (0..3)
	 * @return seconds remaining
	 */
	public int secondsOfFuelRemaining(int fuelSlot)
	{
		if (burnTimeRemaining[fuelSlot] <= 0 ) return 0;
		return burnTimeRemaining[fuelSlot] / 20; // 20 ticks per second
	}

	/**
	 * Get the number of slots which have fuel burning in them.
	 * @return number of slots with burning fuel, 0 - FUEL_SLOTS_COUNT
	 */
	public int numberOfBurningFuelSlots()
	{
		int burningCount = 0;
		for (int burnTime : burnTimeRemaining) {
			if (burnTime > 0) ++burningCount;
		}
		return burningCount;
	}

	/**
	 * Returns the amount of cook time completed on the currently cooking item.
	 * @return fraction remaining, between 0 - 1
	 */
	public double fractionOfCookTimeComplete()
	{
		double fraction = cookTime / (double)COOK_TIME_FOR_COMPLETION;
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}

	// This method is called every tick to update the tile entity, i.e.
	// - see if the fuel has run out, and if so turn the furnace "off" and slowly uncook the current item (if any)
	// - see if any of the items have finished smelting
	// It runs both on the server and the client.
	@Override
	public void update() {
//		// First call is just for putting liquid magic into internal tank without burning something
//		burnFuel(false);
		// If there is nothing to smelt or there is no room in the output, reset cookTime and return
		if (canSmelt()) {
			int numberOfFuelBurning = burnFuel(true);

			// If fuel is available, keep cooking the item, otherwise start "uncooking" it at double speed
			if (numberOfFuelBurning > 0) {
				cookTime += numberOfFuelBurning;
			}	else {
				cookTime -= 2;
			}

			if (cookTime < 0) cookTime = 0;

			// If cookTime has reached maxCookTime smelt the item and reset cookTime
			if (cookTime >= COOK_TIME_FOR_COMPLETION) {
				smeltItem();
				cookTime = 0;
				burnTimeRemaining[0] = 0;
			}
		}	else {
			cookTime = 0;
		}

		// when the number of burning slots changes, we need to force the block to re-render, otherwise the change in
		//   state will not be visible.  Likewise, we need to force a lighting recalculation.
		// The block update (for renderer) is only required on client side, but the lighting is required on both, since
		//    the client needs it for rendering and the server needs it for crop growth etc
		int numberBurning = numberOfBurningFuelSlots();
		if (cachedNumberOfBurningSlots != numberBurning) {
			cachedNumberOfBurningSlots = numberBurning;
			if (worldObj.isRemote) {
				IBlockState iblockstate = this.worldObj.getBlockState(pos);
				final int FLAGS = 3;  // I'm not sure what these flags do, exactly.
				worldObj.notifyBlockUpdate(pos, iblockstate, iblockstate, FLAGS);
			}
		}
	}

	
	/**
	 * 	for each fuel slot: decreases the burn time, checks if burnTimeRemaining = 0 and tries to consume a new piece of fuel if one is available
	 * @return the number of fuel slots which are burning
	 */
	private int burnFuel(boolean burn) {
		int burningCount = 0;
		boolean inventoryChanged = false;
		// Iterate over all the fuel slots
		for (int i = 0; i < FUEL_SLOTS_COUNT; i++) {
			int fuelSlotNumber = i + FIRST_FUEL_SLOT;
			if (burnTimeRemaining[i] > 0 && burn) {
				--burnTimeRemaining[i];
				++burningCount;
			}
			if (burnTimeRemaining[i] == 0) {
				if (itemStacks[fuelSlotNumber] != null && getItemBurnTime(itemStacks[fuelSlotNumber]) > 0) {
					// If the stack in this slot is not null and is fuel, set burnTimeRemaining & burnTimeInitialValue to the
					// item's burn time and decrease the stack size
					burnTimeRemaining[i] = burnTimeInitialValue[i] = getItemBurnTime(itemStacks[fuelSlotNumber]);
					--itemStacks[fuelSlotNumber].stackSize;
					++burningCount;
					inventoryChanged = true;
				// If the stack size now equals 0 set the slot contents to the items container item. This is for fuel
				// items such as lava buckets so that the bucket is not consumed. If the item dose not have
				// a container item getContainerItem returns null which sets the slot contents to null
					if (itemStacks[fuelSlotNumber].stackSize == 0) {
						itemStacks[fuelSlotNumber] = itemStacks[fuelSlotNumber].getItem().getContainerItem(itemStacks[fuelSlotNumber]);
					}
				}
			}
		}
		if (inventoryChanged){
			markDirty();
		}
		return burningCount;
	}

	/**
	 * Check if any of the input items are smeltable and there is sufficient space in the output slots
	 * @return true if smelting is possible
	 */
	private boolean canSmelt() {return smeltItem(false);}

	/**
	 * Smelt an input item into an output slot, if possible
	 */
	private void smeltItem() {smeltItem(true);}

	/**
	 * checks that there is an item to be smelted in one of the input slots and that there is room for the result in the output slots
	 * If desired, performs the smelt
	 * @param performSmelt if true, perform the smelt.  if false, check whether smelting is possible, but don't change the inventory
	 * @return false if no items can be smelted, true otherwise
	 */
	private boolean smeltItem(boolean performSmelt)
	{
		Integer firstSuitableInputSlot = null;
		Integer firstSuitableOutputSlot = null;
		ItemStack result = null;

		// finds the first input slot which is smeltable and whose result fits into an output slot (stacking if possible)
		for (int inputSlot = FIRST_INPUT_SLOT; inputSlot < FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT; inputSlot++)	{
			if (itemStacks[inputSlot] != null) {
				result = getSmeltingResultForItem(itemStacks[inputSlot]);
  			if (result != null) {
					// find the first suitable output slot- either empty, or with identical item that has enough space
					for (int outputSlot = FIRST_OUTPUT_SLOT; outputSlot < FIRST_OUTPUT_SLOT + OUTPUT_SLOTS_COUNT; outputSlot++) {
						ItemStack outputStack = itemStacks[outputSlot];
						if (outputStack == null) {
							firstSuitableInputSlot = inputSlot;
							firstSuitableOutputSlot = outputSlot;
							break;
						}

						if (outputStack.getItem() == result.getItem() && (!outputStack.getHasSubtypes() || outputStack.getMetadata() == outputStack.getMetadata())
										&& ItemStack.areItemStackTagsEqual(outputStack, result)) {
							int combinedSize = itemStacks[outputSlot].stackSize + result.stackSize;
							if (combinedSize <= getInventoryStackLimit() && combinedSize <= itemStacks[outputSlot].getMaxStackSize()) {
								firstSuitableInputSlot = inputSlot;
								firstSuitableOutputSlot = outputSlot;
								break;
							}
						}
					}
					if (firstSuitableInputSlot != null) break;
				}
			}
		}

		if (firstSuitableInputSlot == null) return false;
		if (!performSmelt) return true;
/*
		// alter input and output
		itemStacks[firstSuitableInputSlot].stackSize--;
		if (itemStacks[firstSuitableInputSlot].stackSize <=0) itemStacks[firstSuitableInputSlot] = null;
		if (itemStacks[firstSuitableOutputSlot] == null) {
			itemStacks[firstSuitableOutputSlot] = result.copy(); // Use deep .copy() to avoid altering the recipe
		} else {
			itemStacks[firstSuitableOutputSlot].stackSize += result.stackSize;
		}
*/
		// Inject magic into the wand in outputslot
		if(fuelInUse != null && fuelInUse.getItem() == StartupCommon.shadowDust)
		{
			if(itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.Wands.StartupCommon.woodenWand)
			{
				itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
				itemStacks[firstSuitableInputSlot] = null;
				thewizardmod.Wands.ItemWoodenWand.injectMagic(itemStacks[firstSuitableOutputSlot]);
			}
			else if(itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.Wands.StartupCommon.goldenWand)
			{
				itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
				itemStacks[firstSuitableInputSlot] = null;
				thewizardmod.Wands.ItemGoldenWand.injectMagic(itemStacks[firstSuitableOutputSlot]);
			}
			else if(itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.Wands.StartupCommon.ironWand)
			{
				itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
				itemStacks[firstSuitableInputSlot] = null;
				thewizardmod.Wands.ItemIronWand.injectMagic(itemStacks[firstSuitableOutputSlot]);
			}
			else if(itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.Wands.StartupCommon.blazeWand)
			{
				itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
				itemStacks[firstSuitableInputSlot] = null;
				thewizardmod.Wands.ItemBlazeWand.injectMagic(itemStacks[firstSuitableOutputSlot]);
			}
			else if(Block.getBlockFromItem(itemStacks[firstSuitableInputSlot].getItem()) == Blocks.TORCH
					|| itemStacks[firstSuitableInputSlot].getItem() == Items.DIAMOND
					|| itemStacks[firstSuitableInputSlot].getItem() == Items.IRON_INGOT
					|| Block.getBlockFromItem(itemStacks[firstSuitableInputSlot].getItem()) == Blocks.GLASS
					|| itemStacks[firstSuitableInputSlot].getItem() == Items.STRING
					|| itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.camouflageBlock.StartupCommon.itemBlockCamouflage)
			{
				itemStacks[firstSuitableInputSlot].stackSize--;
				if (itemStacks[firstSuitableInputSlot].stackSize <=0) itemStacks[firstSuitableInputSlot] = null;
				if (itemStacks[firstSuitableOutputSlot] == null) {
					itemStacks[firstSuitableOutputSlot] = result.copy(); // Use deep .copy() to avoid altering the recipe
				} else {
					itemStacks[firstSuitableOutputSlot].stackSize += result.stackSize;
				}
			}
		}
		
		if(fuelInUse != null && fuelInUse.getItem() == Items.FIRE_CHARGE)
		{
			itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
			itemStacks[firstSuitableInputSlot] = null;
			thewizardmod.wandHandling.WandHandling.addFocus(itemStacks[firstSuitableOutputSlot], 1);
		}

		if(fuelInUse != null && fuelInUse.getItem() == thewizardmod.torch.StartupCommon.itemTorch)
		{
			itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
			itemStacks[firstSuitableInputSlot] = null;
			thewizardmod.wandHandling.WandHandling.addFocus(itemStacks[firstSuitableOutputSlot], 3);
		}

		if(fuelInUse != null && fuelInUse.getItem() == thewizardmod.plants.StartupCommon.wheatSeeds)
		{
			itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
			itemStacks[firstSuitableInputSlot] = null;
			thewizardmod.wandHandling.WandHandling.addFocus(itemStacks[firstSuitableOutputSlot], 4);
		}

		if(fuelInUse != null && fuelInUse.getItem() == Items.NETHER_STAR)
		{
			itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
			itemStacks[firstSuitableInputSlot] = null;
			thewizardmod.wandHandling.WandHandling.addFocus(itemStacks[firstSuitableOutputSlot], 2);
		}

		if(fuelInUse != null && Block.getBlockFromItem(fuelInUse.getItem()) == Blocks.VINE)
		{
			itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
			itemStacks[firstSuitableInputSlot] = null;
			thewizardmod.wandHandling.WandHandling.addFocus(itemStacks[firstSuitableOutputSlot], 6);
		}

		if(fuelInUse != null && Block.getBlockFromItem(fuelInUse.getItem()) == Blocks.WEB)
		{
			itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
			itemStacks[firstSuitableInputSlot] = null;
			thewizardmod.wandHandling.WandHandling.addFocus(itemStacks[firstSuitableOutputSlot], 7);
		}

		if(fuelInUse != null && Block.getBlockFromItem(fuelInUse.getItem()) == Blocks.ICE)
		{
			itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
			itemStacks[firstSuitableInputSlot] = null;
			thewizardmod.wandHandling.WandHandling.addFocus(itemStacks[firstSuitableOutputSlot], 8);
		}

		if(fuelInUse != null && fuelInUse.getItem() == thewizardmod.items.StartupCommon.ironPickaxe)
		{
			itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
			itemStacks[firstSuitableInputSlot] = null;
			thewizardmod.wandHandling.WandHandling.addFocus(itemStacks[firstSuitableOutputSlot], 5);
		}

		if(fuelInUse != null && fuelInUse.getItem() == Items.ENDER_PEARL)
		{
			if(itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.items.StartupCommon.magicGem)
			{
				itemStacks[firstSuitableOutputSlot] = new ItemStack(thewizardmod.teleporter.StartupCommon.itemNBTAnimate); 
				itemStacks[firstSuitableInputSlot] = null;
			}
			if(itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.Wands.StartupCommon.woodenWand
					|| itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.Wands.StartupCommon.ironWand
					|| itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.Wands.StartupCommon.goldenWand
					|| itemStacks[firstSuitableInputSlot].getItem() == thewizardmod.Wands.StartupCommon.blazeWand)
			{
				itemStacks[firstSuitableOutputSlot] = itemStacks[firstSuitableInputSlot].copy(); 
				itemStacks[firstSuitableInputSlot] = null;
				thewizardmod.wandHandling.WandHandling.addFocus(itemStacks[firstSuitableOutputSlot], 9);
			}
		}
		outputItem = itemStacks[firstSuitableOutputSlot];
		markDirty();
		return true;
	}

	// returns the smelting result for the given stack. Returns null if the given stack can not be smelted
	public static ItemStack getSmeltingResultForItem(ItemStack stack) {
		// here we need all results in
		if(stack.getItem() == thewizardmod.Wands.StartupCommon.woodenWand){
			return stack;
		}
		if(stack.getItem() == thewizardmod.Wands.StartupCommon.goldenWand){
			return stack;
		}
		if(stack.getItem() == thewizardmod.Wands.StartupCommon.ironWand){
			return stack;
		}
		if(stack.getItem() == thewizardmod.Wands.StartupCommon.blazeWand){
			return stack;
		}
		if(Block.getBlockFromItem(stack.getItem()) == Blocks.TORCH){
			return new ItemStack(thewizardmod.torch.StartupCommon.itemTorch);
		}
		if(Block.getBlockFromItem(stack.getItem()) == Blocks.GLASS){
			return new ItemStack(thewizardmod.camouflageBlock.StartupCommon.itemBlockCamouflage);
		}
		if(stack.getItem() == Items.DIAMOND)
		{
			return new ItemStack(StartupCommon.magicGem);
		}
		
		if(stack.getItem() == Items.IRON_INGOT)
		{
			return new ItemStack(StartupCommon.magicIron);
		}
		
		if(stack.getItem() == Items.STRING)
		{
			return new ItemStack(StartupCommon.magicString);
		}
		
		if(stack.getItem() == StartupCommon.magicGem)
		{
			return new ItemStack(StartupCommon.magicGem);
		}
		if(stack.getItem() == thewizardmod.camouflageBlock.StartupCommon.itemBlockCamouflage)
		{
			return new ItemStack(thewizardmod.dimensions.StartupCommon.itemPortalFrame);
		}
		
		return null;//FurnaceRecipes.instance().getSmeltingResult(stack); 
		}

	// returns the number of ticks the given item will burn. Returns 0 if the given item is not a valid fuel
	public static short getItemBurnTime(ItemStack stack)
	{
		int burntime = TileEntityFurnace.getItemBurnTime(stack);  // just use the vanilla values
		if(stack.getItem() == StartupCommon.shadowDust){
			burntime = 200;
		}
		if(stack.getItem() == Items.FIRE_CHARGE){
			burntime = 200;
		}
		if(stack.getItem() == Items.ENDER_PEARL){
			burntime = 200;
		}
		if(stack.getItem() == thewizardmod.torch.StartupCommon.itemTorch){
			burntime = 200;
		}
		if(stack.getItem() == thewizardmod.plants.StartupCommon.wheatSeeds){
			burntime = 200;
		}
		if(stack.getItem() == Items.NETHER_STAR){
			burntime = 200;
		}
		if(Block.getBlockFromItem(stack.getItem()) == Blocks.VINE){
			burntime = 200;
		}
		if(Block.getBlockFromItem(stack.getItem()) == Blocks.WEB){
			burntime = 200;
		}
		if(Block.getBlockFromItem(stack.getItem()) == Blocks.ICE){
			burntime = 200;
		}
		if(stack.getItem() == thewizardmod.items.StartupCommon.ironPickaxe){
			burntime = 200;
		}
		return (short)MathHelper.clamp_int(burntime, 0, Short.MAX_VALUE);
	}

	// Gets the number of slots in the inventory
	@Override
	public int getSizeInventory() {
		return itemStacks.length;
	}

	// Gets the stack in the given slot
	@Override
	public ItemStack getStackInSlot(int i) {
		return itemStacks[i];
	}

	/**
	 * Removes some of the units from itemstack in the given slot, and returns as a separate itemstack
	 * @param slotIndex the slot number to remove the items from
	 * @param count the number of units to remove
	 * @return a new itemstack containing the units removed from the slot
	 */
	@Override
	public ItemStack decrStackSize(int slotIndex, int count) {
		ItemStack itemStackInSlot = getStackInSlot(slotIndex);
		if (itemStackInSlot == null) return null;

		ItemStack itemStackRemoved;
		if (itemStackInSlot.stackSize <= count) {
			itemStackRemoved = itemStackInSlot;
			setInventorySlotContents(slotIndex, null);
			if(slotIndex == FIRST_OUTPUT_SLOT)
			{
				outputItem = null;
			}
		} else {
			itemStackRemoved = itemStackInSlot.splitStack(count);
			if (itemStackInSlot.stackSize == 0) {
				setInventorySlotContents(slotIndex, null);
			}
		}
		markDirty();
		return itemStackRemoved;
	}

	// overwrites the stack in the given slotIndex with the given stack
	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
		itemStacks[slotIndex] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	// This is the maximum number if items allowed in each slot
	// This only affects things such as hoppers trying to insert items you need to use the container to enforce this for players
	// inserting items via the gui
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	// Return true if the given player is able to use this block. In this case it checks that
	// 1) the world tileentity hasn't been replaced in the meantime, and
	// 2) the player isn't too far away from the centre of the block
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (this.worldObj.getTileEntity(this.pos) != this) return false;
		final double X_CENTRE_OFFSET = 0.5;
		final double Y_CENTRE_OFFSET = 0.5;
		final double Z_CENTRE_OFFSET = 0.5;
		final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
		return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
	}

	// Return true if the given stack is allowed to be inserted in the given slot
	// Unlike the vanilla furnace, we allow anything to be placed in the fuel slots
	static public boolean isItemValidForFuelSlot(ItemStack itemStack)
	{
		if(itemStack.getItem() == StartupCommon.shadowDust){
			fuelInUse = itemStack;
			return true;
		}
		if(itemStack.getItem() == Items.FIRE_CHARGE){
			fuelInUse = itemStack;
			return true;
		}
		if(itemStack.getItem() == Items.ENDER_PEARL){
			fuelInUse = itemStack;
			return true;
		}
		if(itemStack.getItem() == thewizardmod.plants.StartupCommon.wheatSeeds){
			fuelInUse = itemStack;
			return true;
		}
		if(itemStack.getItem() == Items.NETHER_STAR){
			fuelInUse = itemStack;
			return true;
		}
		if(Block.getBlockFromItem(itemStack.getItem()) == Blocks.VINE){
			fuelInUse = itemStack;
			return true;
		}
		if(Block.getBlockFromItem(itemStack.getItem()) == Blocks.WEB){
			fuelInUse = itemStack;
			return true;
		}
		if(Block.getBlockFromItem(itemStack.getItem()) == Blocks.ICE){
			fuelInUse = itemStack;
			return true;
		}
		if(itemStack.getItem() == thewizardmod.items.StartupCommon.ironPickaxe){
			fuelInUse = itemStack;
			return true;
		}
		if(itemStack.getItem() == thewizardmod.torch.StartupCommon.itemTorch){
			fuelInUse = itemStack;
			return true;
		}
		return false;
	}

	// Return true if the given stack is allowed to be inserted in the given slot
	// Unlike the vanilla furnace, we allow anything to be placed in the fuel slots
	static public boolean isItemValidForInputSlot(ItemStack itemStack)
	{
		if(itemStack.getItem() == thewizardmod.Wands.StartupCommon.woodenWand)
		{
			return true;
		}
		if(itemStack.getItem() == thewizardmod.Wands.StartupCommon.goldenWand)
		{
			return true;
		}
		if(itemStack.getItem() == thewizardmod.Wands.StartupCommon.ironWand)
		{
			return true;
		}
		if(itemStack.getItem() == thewizardmod.Wands.StartupCommon.blazeWand)
		{
			return true;
		}
		if(Block.getBlockFromItem(itemStack.getItem()) == Blocks.TORCH){
			return true;
		}
		if(itemStack.getItem() == Items.DIAMOND){
			return true;
		}
		if(itemStack.getItem() == Items.IRON_INGOT){
			return true;
		}
		if(itemStack.getItem() == StartupCommon.magicGem){
			return true;
		}
		if(Block.getBlockFromItem(itemStack.getItem()) == Blocks.GLASS){
			return true;
		}
		if(itemStack.getItem() == Items.STRING){
			return true;
		}
		if(itemStack.getItem() == thewizardmod.camouflageBlock.StartupCommon.itemBlockCamouflage){
			return true;
		}
		return false;
	}

	// Return true if the given stack is allowed to be inserted in the given slot
	// Unlike the vanilla furnace, we allow anything to be placed in the fuel slots
	static public boolean isItemValidForOutputSlot(ItemStack itemStack)
	{
		return false;
	}

	//------------------------------

	// This is where you save any data that you don't want to lose when the tile entity unloads
	// In this case, it saves the state of the furnace (burn time etc) and the itemstacks stored in the fuel, input, and output slots
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound)
	{
		super.writeToNBT(parentNBTTagCompound); // The super call is required to save and load the tiles location

//		// Save the stored item stacks

		// to use an analogy with Java, this code generates an array of hashmaps
		// The itemStack in each slot is converted to an NBTTagCompound, which is effectively a hashmap of key->value pairs such
		//   as slot=1, id=2353, count=1, etc
		// Each of these NBTTagCompound are then inserted into NBTTagList, which is similar to an array.
		NBTTagList dataForAllSlots = new NBTTagList();
		for (int i = 0; i < this.itemStacks.length; ++i) {
			if (this.itemStacks[i] != null) {
				NBTTagCompound dataForThisSlot = new NBTTagCompound();
				dataForThisSlot.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(dataForThisSlot);
				dataForAllSlots.appendTag(dataForThisSlot);
			}
		}
		// the array of hashmaps is then inserted into the parent hashmap for the container
		parentNBTTagCompound.setTag("Items", dataForAllSlots);


		if(fuelInUse != null)
		{
			// Save the fuel we are using at the moment
			NBTTagCompound fuelStack = new NBTTagCompound();
			this.fuelInUse.writeToNBT(fuelStack);
			parentNBTTagCompound.setTag("fuelInUse", fuelStack);
		}
		
		if(outputItem != null)
		{
			NBTTagCompound stack = new NBTTagCompound();
			this.outputItem.writeToNBT(stack);
			parentNBTTagCompound.setTag("outputItem", stack);
		}
		
		// Save everything else
		parentNBTTagCompound.setInteger("facing", facing);
		parentNBTTagCompound.setLong("lastChangeTime", lastChangeTime);
		parentNBTTagCompound.setShort("CookTime", cookTime);
	  parentNBTTagCompound.setTag("burnTimeRemaining", new NBTTagIntArray(burnTimeRemaining));
		parentNBTTagCompound.setTag("burnTimeInitial", new NBTTagIntArray(burnTimeInitialValue));
    return parentNBTTagCompound;
	}

	// This is where you load the data that you saved in writeToNBT
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound); // The super call is required to save and load the tiles location
		final byte NBT_TYPE_COMPOUND = 10;       // See NBTBase.createNewByType() for a listing
		NBTTagList dataForAllSlots = nbtTagCompound.getTagList("Items", NBT_TYPE_COMPOUND);

		Arrays.fill(itemStacks, null);           // set all slots to empty
		for (int i = 0; i < dataForAllSlots.tagCount(); ++i) {
			NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
			byte slotNumber = dataForOneSlot.getByte("Slot");
			if (slotNumber >= 0 && slotNumber < this.itemStacks.length) {
				this.itemStacks[slotNumber] = ItemStack.loadItemStackFromNBT(dataForOneSlot);
			}
		}

		// Read the current fuel we are using
		this.fuelInUse = ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("fuelInUse"));
		
		this.outputItem = ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("outputItem"));

		// Load everything else.  Trim the arrays (or pad with 0) to make sure they have the correct number of elements
		facing = nbtTagCompound.getInteger("facing");
		lastChangeTime = nbtTagCompound.getLong("lastChangeTime");
		cookTime = nbtTagCompound.getShort("CookTime");
		burnTimeRemaining = Arrays.copyOf(nbtTagCompound.getIntArray("burnTimeRemaining"), FUEL_SLOTS_COUNT);
		burnTimeInitialValue = Arrays.copyOf(nbtTagCompound.getIntArray("burnTimeInitial"), FUEL_SLOTS_COUNT);
		cachedNumberOfBurningSlots = -1;
	}

//	// When the world loads from disk, the server needs to send the TileEntity information to the client
//	//  it uses getUpdatePacket(), getUpdateTag(), onDataPacket(), and handleUpdateTag() to do this
  @Override
  @Nullable
  public SPacketUpdateTileEntity getUpdatePacket()
  {
    NBTTagCompound updateTagDescribingTileEntityState = getUpdateTag();
    final int METADATA = 0;
    return new SPacketUpdateTileEntity(this.pos, METADATA, updateTagDescribingTileEntityState);
  }

  @Override
  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    NBTTagCompound updateTagDescribingTileEntityState = pkt.getNbtCompound();
    handleUpdateTag(updateTagDescribingTileEntityState);
  }

  /* Creates a tag containing the TileEntity information, used by vanilla to transmit from server to client
     Warning - although our getUpdatePacket() uses this method, vanilla also calls it directly, so don't remove it.
   */
  @Override
  public NBTTagCompound getUpdateTag()
  {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
    return nbtTagCompound;
  }

  /* Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client
   Warning - although our onDataPacket() uses this method, vanilla also calls it directly, so don't remove it.
 */
  @Override
  public void handleUpdateTag(NBTTagCompound tag)
  {
    this.readFromNBT(tag);
  }
  //------------------------

	// set all slots to empty
	@Override
	public void clear() {
		Arrays.fill(itemStacks, null);
	}

	// will add a key for this container to the lang file so we can name it in the GUI
	@Override
	public String getName() {
		return "container.twm_magicInjector.name";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	// standard code to look up what the human-readable name is
  @Nullable
  @Override
  public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}

	// Fields are used to send non-inventory information from the server to interested clients
	// The container code caches the fields and sends the client any fields which have changed.
	// The field ID is limited to byte, and the field value is limited to short. (if you use more than this, they get cast down
	//   in the network packets)
	// If you need more than this, or shorts are too small, use a custom packet in your container instead.

	private static final byte COOK_FIELD_ID = 0;
	private static final byte FIRST_BURN_TIME_REMAINING_FIELD_ID = 1;
	private static final byte FIRST_BURN_TIME_INITIAL_FIELD_ID = FIRST_BURN_TIME_REMAINING_FIELD_ID + (byte)FUEL_SLOTS_COUNT;
	private static final byte NUMBER_OF_FIELDS = FIRST_BURN_TIME_INITIAL_FIELD_ID + (byte)FUEL_SLOTS_COUNT;

	@Override
	public int getField(int id) {
		if (id == COOK_FIELD_ID) return cookTime;
		if (id >= FIRST_BURN_TIME_REMAINING_FIELD_ID && id < FIRST_BURN_TIME_REMAINING_FIELD_ID + FUEL_SLOTS_COUNT) {
			return burnTimeRemaining[id - FIRST_BURN_TIME_REMAINING_FIELD_ID];
		}
		if (id >= FIRST_BURN_TIME_INITIAL_FIELD_ID && id < FIRST_BURN_TIME_INITIAL_FIELD_ID + FUEL_SLOTS_COUNT) {
			return burnTimeInitialValue[id - FIRST_BURN_TIME_INITIAL_FIELD_ID];
		}
		System.err.println("Invalid field ID in TileInventorySmelting.getField:" + id);
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
		if (id == COOK_FIELD_ID) {
			cookTime = (short)value;
		} else if (id >= FIRST_BURN_TIME_REMAINING_FIELD_ID && id < FIRST_BURN_TIME_REMAINING_FIELD_ID + FUEL_SLOTS_COUNT) {
			burnTimeRemaining[id - FIRST_BURN_TIME_REMAINING_FIELD_ID] = value;
		} else if (id >= FIRST_BURN_TIME_INITIAL_FIELD_ID && id < FIRST_BURN_TIME_INITIAL_FIELD_ID + FUEL_SLOTS_COUNT) {
			burnTimeInitialValue[id - FIRST_BURN_TIME_INITIAL_FIELD_ID] = value;
		} else {
			System.err.println("Invalid field ID in TileInventorySmelting.setField:" + id);
		}
	}

	@Override
	public int getFieldCount() {
		return NUMBER_OF_FIELDS;
	}

	// -----------------------------------------------------------------------------------------------------------
	// The following methods are not needed for this example but are part of IInventory so they must be implemented

	// Unused unless your container specifically uses it.
	// Return true if the given stack is allowed to go in the given slot
	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack) {
		return false;
	}

	/**
	 * This method removes the entire contents of the given slot and returns it.
	 * Used by containers such as crafting tables which return any items in their slots when you close the GUI
	 * @param slotIndex
	 * @return
	 */
	@Override
	public ItemStack removeStackFromSlot(int slotIndex) {
		ItemStack itemStack = getStackInSlot(slotIndex);
		if (itemStack != null) setInventorySlotContents(slotIndex, null);
		return itemStack;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

}
