package thewizardmod.extractor;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityExtractor extends TileFluidHandler implements ISidedInventory, ITickable{
	
	public static final int CAPACITY = 6 * Fluid.BUCKET_VOLUME + 1;
	
	
	public ItemStack itemStacks[] = new ItemStack[5];

	public IInventory inventory;
	
	public int coolDown;
	public int burntime;
	public int burntimeLeft;
	public int burntimeMax;
	
	private int bucketInputSlot = 0;
	private int bucketOutputSlot = 1;
	private int oreInputSlot = 2;
	private int stoneOutputSlot = 3;
	private int fuelInputSlot = 4;


    private static final int[] SLOTS_TOP = new int[] {0, 2, 4};
    private static final int[] SLOTS_BOTTOM = new int[] {1, 3};
    private static final int[] SLOTS_SIDES = new int[] {};

	public TileEntityExtractor() {
		tank = new FluidTankWithTile(this, CAPACITY);
		tank.setFluid(new FluidStack(thewizardmod.fluids.StartupCommon.fluidMagic, 1));
		this.coolDown = 0;
		this.burntime = 0;
		this.burntimeLeft = 0;
		this.burntimeMax = 0;
	}

	
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.itemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");

            if (j >= 0 && j < this.itemStacks.length)
            {
                this.itemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
        int amount = compound.getInteger("tankAmount");
        if(amount == 0)
        	amount = 1;
        tank.drainInternal(CAPACITY, true);
        tank.fill(new FluidStack(thewizardmod.fluids.StartupCommon.fluidMagic, amount), true);

        this.coolDown = compound.getInteger("coolDown");
        this.burntime = compound.getInteger("burntime");
        this.burntimeLeft = compound.getInteger("burntimeLeft");
        this.burntimeMax = compound.getInteger("burntimeMax");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.itemStacks.length; ++i)
        {
            if (this.itemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.itemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        compound.setTag("Items", nbttaglist);
        
        compound.setInteger("tankAmount", tank.getFluidAmount());

        compound.setInteger("coolDown", this.coolDown);
        compound.setInteger("burntime", this.burntime);
        compound.setInteger("burntimeLeft", this.burntimeLeft);
        compound.setInteger("burntimeMax", this.burntimeMax);

        return compound;
    }


	@Override
	public int getSizeInventory() {
		return this.itemStacks.length;
	}



	@Override
	@Nullable
	public ItemStack getStackInSlot(int index) {
		return this.itemStacks[index];
	}



	@Override
	@Nullable
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.itemStacks, index, count);
	}



	@Override
	@Nullable
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.itemStacks, index);
	}



	@Override
	public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        boolean flag = stack != null && stack.isItemEqual(this.itemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.itemStacks[index]);
        this.itemStacks[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (!flag)
        {
            this.markDirty();
        }
	}



	@Override
	public int getInventoryStackLimit() {
		return 64;
	}



	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}



	@Override
	public void openInventory(EntityPlayer player) {
	}



	@Override
	public void closeInventory(EntityPlayer player) {
	}



	@Override
	public boolean isItemValidForSlot(int index, ItemStack itemstack) {
        if (index == oreInputSlot)
        {
        	if(itemstack.getItem() == Item.getItemFromBlock(thewizardmod.ores.StartupCommon.shadowdustOre))
        		return true;
        }
        else if(index == bucketInputSlot)
        {
            if(itemstack != null)
            {
            	// Empty buckets are allowed
            	if(itemstack.getItem() == Items.BUCKET)
            	{
            		return true;
            	}
            }
        }
        else if(index == fuelInputSlot)
        {
            return isItemFuel(itemstack) || SlotFurnaceFuel.isBucket(itemstack) && (itemstack == null || itemstack.getItem() != Items.BUCKET);
        }
        return false;
	}




	@Override
	public void setField(int id, int value) {
	}



	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public int getField(int id) {
		return 0;
	}



	@Override
	public void clear() {
        for (int i = 0; i < this.itemStacks.length; ++i)
        {
            this.itemStacks[i] = null;
        }
	}



	@Override
	public String getName() {
        return "container.twm_tank.name";
	}



	@Override
	public boolean hasCustomName() {
		return false;
	}



	@Override
	public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
	}



	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn,
			EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
		}



	@Override
	public boolean canExtractItem(int index, ItemStack stack,
			EnumFacing direction) {
			return true;
	}



	@Override
	public void update() {
		ItemStack inputStack = getStackInSlot(bucketInputSlot);

        if(inputStack != null)
        {
        	// Empty buckets are allowed
        	if(inputStack.getItem() == Items.BUCKET)
        	{
    			if(tank.getFluidAmount() - 1000 > 0 && getStackInSlot(bucketOutputSlot) == null)
    			{
    				tank.drain(1000, true);
    				decrStackSize(bucketInputSlot, 1);
    				ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, thewizardmod.fluids.StartupCommon.fluidMagic);
     		       	setInventorySlotContents(bucketOutputSlot, bucket);
     		        markDirty();
    			}
        	}
        }
        
        ItemStack oreInputStack = getStackInSlot(oreInputSlot);

        if(oreInputStack != null)
        {
        	if(burntimeMax > 0)
        	{
        		this.coolDown++;
        		// It takes 2 second(40 ticks) to extract liquid magic from ore
        		if(this.coolDown >= 40)
        		{
    				coolDown = 40;
        			// We need 10 Ore to get a full bucket (1000mB)
        			if(tank.getFluidAmount() + 100 <= tank.getCapacity())
        			{
        				if(itemStacks[stoneOutputSlot] != null)
 		       			{	
        					if(itemStacks[stoneOutputSlot].stackSize < getInventoryStackLimit())
        					{
 		    	   				itemStacks[stoneOutputSlot].stackSize++;
 		        				tank.fill(new FluidStack(thewizardmod.fluids.StartupCommon.fluidMagic, 100), true);
 		        				decrStackSize(oreInputSlot, 1);
 		        				this.coolDown = 0;
        					}
 		       			}
 		       			else
 		       			{
 		       				itemStacks[stoneOutputSlot] = new ItemStack(Blocks.STONE);
 	        				tank.fill(new FluidStack(thewizardmod.fluids.StartupCommon.fluidMagic, 100), true);
 	        				decrStackSize(oreInputSlot, 1);
 	        				this.coolDown = 0;
 		       			}
 		       			setInventorySlotContents(stoneOutputSlot, itemStacks[stoneOutputSlot]);
 		       			markDirty();
        			}
        		}
        	}
        }
        else
        {
        	coolDown = 0;
            markDirty();
        }
        
        // Nothing burning
        if(this.burntimeMax == 0)
        {
        	ItemStack oreStack = getStackInSlot(oreInputSlot);
        	ItemStack fuelInputStack = getStackInSlot(fuelInputSlot);
        	if(fuelInputStack != null && oreStack != null)
        	{
        		if(tank.getFluidAmount() < tank.getCapacity())
        		{
        			if(itemStacks[stoneOutputSlot] != null)
        			{
        				if(itemStacks[stoneOutputSlot].stackSize < getInventoryStackLimit())
        				{
        					burntimeMax = getItemBurnTime(fuelInputStack);
        					if(burntimeMax > 0)
        					{
        						decrStackSize(fuelInputSlot, 1);
        						markDirty();
        					}
        				}
        			}
        			else
        			{
        				burntimeMax = getItemBurnTime(fuelInputStack);
        				if(burntimeMax > 0)
        				{
        					decrStackSize(fuelInputSlot, 1);
        					markDirty();
        				}
        			}
        		}
        	}
        }
        
        // burn fuel
        if(this.burntime < burntimeMax)
        {
        	burntime++;
            markDirty();
        }
        else
        {
        	burntime = 0;
        	burntimeMax = 0;
            markDirty();
        }
        markDirty();
	}

    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.WOODEN_SLAB)
                {
                    return 150;
                }

                if (block.getDefaultState().getMaterial() == Material.WOOD)
                {
                    return 300;
                }

                if (block == Blocks.COAL_BLOCK)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;
            if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;
            if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
            if (item == Items.STICK) return 100;
            if (item == Items.COAL) return 1600;
            if (item == Items.LAVA_BUCKET) return 20000;
            if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
            if (item == Items.BLAZE_ROD) return 2400;
            return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }
    
 // this function indicates whether container texture should be drawn
    @SideOnly(Side.CLIENT)
    public static boolean func_174903_a(IInventory parIInventory)
    {
        return true ;
    }

}