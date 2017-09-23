package thewizardmod.FarmCrops;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.fluids.StartupCommon;

public class TileEntityFarmCrops extends TileFluidHandler implements
		ISidedInventory, ITickable {

	public final String name = "container.twm_cropfarm.name";

	public static final int CAPACITY = 6 * Fluid.BUCKET_VOLUME + 1;

	public IInventory inventory;

	public int slotSize = 14;
	private int bucketInputSlot = 0;
	private int bucketOutputSlot = 1;
	private int boneMealSlot = 2;
	private int seedSlot = 3;

	public ItemStack itemStacks[] = new ItemStack[slotSize + 1];

	private static final int[] SLOTS_TOP = new int[] { 0, 2, 3 };
	private static final int[] SLOTS_BOTTOM = new int[] { 1, 4, 5, 6, 7, 8, 9,
			10, 11, 12 };
	private static final int[] SLOTS_SIDES = new int[] {};

	public int facing;

	private int coolDown = 0;

	public TileEntityFarmCrops() {
		tank = new FluidTankWithTile(this, CAPACITY);
		tank.setFluid(new FluidStack(
				thewizardmod.fluids.StartupCommon.fluidMagic, 1));
	}

	public void setFacing(int facing) {
		this.facing = facing;
		markDirty();
	}

	public int getFacing() {
		return this.facing;
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

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.itemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot");

			if (j >= 0 && j < this.itemStacks.length) {
				this.itemStacks[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound);
			}
		}
		int amount = compound.getInteger("tankAmount");
		if (amount == 0)
			amount = 1;
		tank.drainInternal(CAPACITY, true);
		tank.fill(new FluidStack(thewizardmod.fluids.StartupCommon.fluidMagic,
				amount), true);

		this.facing = compound.getInteger("facing");
		this.coolDown = compound.getInteger("coolDown");
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.itemStacks.length; ++i) {
			if (this.itemStacks[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		compound.setTag("Items", nbttaglist);

		compound.setInteger("tankAmount", tank.getFluidAmount());

		compound.setInteger("facing", facing);
		compound.setInteger("coolDown", coolDown);

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
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.itemStacks[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.pos) != this ? false : player
				.getDistanceSq((double) this.pos.getX() + 0.5D,
						(double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack itemstack) {
		if (itemstack != null) {
			if (index == bucketInputSlot) {
				// Empty buckets are allowed
				if (itemstack.getItem() == Items.BUCKET) {
					return true;
				}
			}
			if (index == seedSlot) {
				if (itemstack.getItem() == Items.WHEAT_SEEDS
						|| itemstack.getItem() == thewizardmod.plants.StartupCommon.wheatSeeds
						|| itemstack.getItem() == Items.CARROT
						|| itemstack.getItem() == Items.POTATO) {
					return true;
				}
			}
			if (index == boneMealSlot) {
				if (itemstack.areItemsEqual(itemstack, new ItemStack(Items.DYE,
						1, 15))) {
					return true;
				}
			}
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
		for (int i = 0; i < this.itemStacks.length; ++i) {
			this.itemStacks[i] = null;
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? SLOTS_BOTTOM
				: (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
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

	public void handleTank() {
		ItemStack inputStack = getStackInSlot(bucketInputSlot);

		if (inputStack != null) {
			// Empty buckets are allowed
			if (inputStack.getItem() == Items.BUCKET) {
				if (tank.getFluidAmount() - 1000 > 0 && getStackInSlot(bucketInputSlot) == null) {
					tank.drain(1000, true);
					decrStackSize(bucketInputSlot, 1);
					ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket,	StartupCommon.fluidMagic);
					setInventorySlotContents(bucketOutputSlot, bucket);
				}
			}
			// It's a forge universal bucket
			if (inputStack.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0) {
				// The fluid in bucket is liquid magic
				if (FluidUtil.getFluidContained(inputStack).containsFluid(new FluidStack(StartupCommon.fluidMagic, 1000))&& (getStackInSlot(bucketOutputSlot) == null || getStackInSlot(bucketOutputSlot).getItem() == Items.BUCKET)) {
					if (tank.getFluidAmount() + 1000 <= tank.getCapacity()) {
						tank.fill(new FluidStack(StartupCommon.fluidMagic, 1000),true);
						decrStackSize(bucketInputSlot, 1);
						if (itemStacks[bucketOutputSlot] != null) {
							itemStacks[bucketOutputSlot].stackSize++;
						} else {
							itemStacks[bucketOutputSlot] = new ItemStack(Items.BUCKET);
						}
						setInventorySlotContents(bucketOutputSlot,itemStacks[bucketOutputSlot]);
					}
				}
			}
			markDirty();
		}
	}

	public BlockPos getCenterOfField()
	{
		BlockPos pos = this.pos;
		BlockPos centerOfField = null;

		// Find the center of 9x9 blocks in front
		switch (this.facing) {
		case 0: // south
			centerOfField = pos.south(3).down();
			break;
		case 1: // west
			centerOfField = pos.west(3).down();
			break;
		case 2: // north
			centerOfField = pos.north(3).down();
			break;
		case 3: // east
			centerOfField = pos.east(3).down();
			break;
		}
		return centerOfField;
	}
	
	public void handleGround() {
		BlockPos centerOfField = getCenterOfField();
		
		// check if the center is a water block
		if (worldObj.getBlockState(centerOfField).getBlock() == Blocks.WATER) {
			// check if space around water block is farm land
			int x = centerOfField.getX() - 2;
			int y = centerOfField.getY();
			int z = centerOfField.getZ() - 2;

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					// It will cost 10mB magic fluid to convert grass to
					// farmland
					if (tank.getFluidAmount() >= 11) {
						BlockPos newpos = new BlockPos(x + i, y, z + j);
						Block block = worldObj.getBlockState(newpos).getBlock();
						if (block == Blocks.GRASS) {
							worldObj.setBlockState(newpos,
									Blocks.FARMLAND.getDefaultState(), 3);
							tank.drain(10, true);
						}
					}
				}
			}
		}
	}

	public void handlePlanting() {
		BlockPos centerOfField = getCenterOfField();

		// check if the center is a water block
		if (worldObj.getBlockState(centerOfField).getBlock() == Blocks.WATER) {
			// check if space around water block is farm land
			int x = centerOfField.getX() - 2;
			int y = centerOfField.getY();
			int z = centerOfField.getZ() - 2;

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					// It will cost 5mB magic fluid to plant a seed
					if (tank.getFluidAmount() >= 6) {
						BlockPos newpos = new BlockPos(x + i, y, z + j);
						Block block = worldObj.getBlockState(newpos).getBlock();
						if (block == Blocks.FARMLAND) {
							BlockPos plantPos = newpos.up();
							if (this.worldObj.isAirBlock(plantPos)) {
								// check what kind of seed is in seed slot
								ItemStack seedStack = getStackInSlot(seedSlot);
								if (seedStack != null) {
									Item seedType = seedStack.getItem();
									if (seedType != null) {
										Block plantBlock = null;
										if (seedType == Items.WHEAT_SEEDS) {
											plantBlock = Blocks.WHEAT;
										} else if (seedType == thewizardmod.plants.StartupCommon.wheatSeeds) {
											plantBlock = thewizardmod.plants.StartupCommon.wheatCrop;
										} else if (seedType == Items.CARROT) {
											plantBlock = Blocks.CARROTS;
										} else if (seedType == Items.POTATO) {
											plantBlock = Blocks.POTATOES;
										}
										// finaly let's plant it
										if (plantBlock != null) {
											worldObj.setBlockState(
													plantPos,
													plantBlock
															.getDefaultState()
															.withProperty(
																	BlockCrops.AGE,
																	0), 3);
											tank.drain(5, true);
											decrStackSize(seedSlot, 1);
										}
									}
								}
							}

						}
					}
				}
			}
		}
	}

	public void handleHarvesting() {
		BlockPos centerOfField = getCenterOfField();

		// check if the center is a water block
		if (worldObj.getBlockState(centerOfField).getBlock() == Blocks.WATER) {
			// check if space around water block is farm land
			int x = centerOfField.getX() - 2;
			int y = centerOfField.getY() + 1;
			int z = centerOfField.getZ() - 2;

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					// It will cost 5mB magic fluid to harvest a plant
					if (tank.getFluidAmount() >= 6) {
						BlockPos newpos = new BlockPos(x + i, y, z + j);
						Block block = worldObj.getBlockState(newpos).getBlock();
						if (block != null) {
							int state = worldObj
									.getBlockState(newpos)
									.getBlock()
									.getMetaFromState(
											worldObj.getBlockState(newpos));
							if ((block == Blocks.WHEAT
									|| block == thewizardmod.plants.StartupCommon.wheatCrop
									|| block == Blocks.CARROTS || block == Blocks.POTATOES)
									&& state == 7) {
								worldObj.destroyBlock(newpos, true);
								tank.drain(5, true);
							}
						}

					}
				}
			}
		}
	}

	public void handleFertalizing() {
		BlockPos centerOfField = getCenterOfField();

		Random random = new Random();
		
		// check if the center is a water block
		if (worldObj.getBlockState(centerOfField).getBlock() == Blocks.WATER) {
			// check if space around water block is farm land
			int x = centerOfField.getX() - 2;
			int y = centerOfField.getY() + 1;
			int z = centerOfField.getZ() - 2;

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					// It will cost 5mB magic fluid to use bonemeal
					if (tank.getFluidAmount() >= 6 && getStackInSlot(boneMealSlot) != null) {
						BlockPos newpos = new BlockPos(x + i, y, z + j);
						Block block = worldObj.getBlockState(newpos).getBlock();
						if (block != null) {
							int state = worldObj
									.getBlockState(newpos)
									.getBlock()
									.getMetaFromState(
											worldObj.getBlockState(newpos));
							if ((block == Blocks.WHEAT
									|| block == thewizardmod.plants.StartupCommon.wheatCrop
									|| block == Blocks.CARROTS || block == Blocks.POTATOES)
									) {
								int chance = random.nextInt(100);
								if(chance == 0)
								{
									ItemDye.applyBonemeal(new ItemStack(Items.DYE, 15, 0), worldObj, newpos, null);
									tank.drain(5, true);
									decrStackSize(boneMealSlot, 1);
								}
							}
						}

					}
				}
			}
		}
	}

	public void handlePickup() {
		BlockPos centerOfField = getCenterOfField();

		double posX = centerOfField.getX();
		double posY = centerOfField.getY();
		double posZ = centerOfField.getZ();
		if (!worldObj.isRemote) {
			for (Object obj : worldObj.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(posX - 3, posY - 3, posZ - 3, posX + 3, posY + 3, posZ + 3))) {
				if (obj instanceof EntityItem) 
				{
					EntityItem item = (EntityItem) obj;
					ItemStack pickupItemStack = item.getEntityItem();
					Item pickupItem = pickupItemStack.getItem();
					
					// go over all output slots
					for (int i = seedSlot; i < slotSize; i++) 
					{
						ItemStack stackSlot = getStackInSlot(i);
						if(stackSlot != null && !item.isDead)
						{
							Item stackItem = stackSlot.getItem();
						
							if(stackItem == pickupItem)
							{
								if(stackSlot.stackSize < getInventoryStackLimit())
								{
									int maxPickupSize = getInventoryStackLimit() - stackSlot.stackSize;
									if(maxPickupSize < pickupItemStack.stackSize)
									{
										stackSlot.stackSize += maxPickupSize;
										pickupItemStack.stackSize -= maxPickupSize;
									}
									else
									{
										stackSlot.stackSize += pickupItemStack.stackSize;
										pickupItemStack.stackSize = 0;
									}
									setInventorySlotContents(i, stackSlot);
									if(pickupItemStack.stackSize <= 0)
									{
										item.setDead();
									}
								}
							}
						}
						else if(!item.isDead)
						{
							ItemStack newStack = new ItemStack(pickupItem, pickupItemStack.stackSize);
							setInventorySlotContents(i, newStack);
							item.setDead();
						}
					}
				}
			}
		}
	}

	@Override
	public void update() {

		handleTank();

		coolDown++;
		// every second we check everything. It's magic, but not high speed :-)
		if (coolDown >= 20) {
			coolDown = 0;
			// a minimum of 5mB is needed to work 
			if (tank.getFluidAmount() >= 6)
			{
				handleGround();
				handlePlanting();
				handleHarvesting();
				handlePickup();
				handleFertalizing();
			}
		}
	}

	// this function indicates whether container texture should be drawn
	@SideOnly(Side.CLIENT)
	public static boolean func_174903_a(IInventory parIInventory) {
		return true;
	}

}