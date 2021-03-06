package thewizardmod.fluids;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.TileFluidHandler;

public class TileEntityTank extends TileFluidHandler implements ISidedInventory, ITickable{
	
	public static final int CAPACITY = 10 * Fluid.BUCKET_VOLUME + 1;
	
	
	public ItemStack itemStacks[] = new ItemStack[3];

	public IInventory inventory;


    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {1};
    private static final int[] SLOTS_SIDES = new int[] {};

	public TileEntityTank() {
		tank = new FluidTankWithTile(this, CAPACITY);
		tank.setFluid(new FluidStack(StartupCommon.fluidMagic, 1));
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
        tank.fill(new FluidStack(StartupCommon.fluidMagic, amount), true);

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

        if (index == 0 && !flag)
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
        if (index == 2)
        {
            return false;
        }
        else
        {
            if(itemstack != null)
            {
            	// Empty buckets are allowed
            	if(itemstack.getItem() == Items.BUCKET)
            	{
            		return true;
            	}
            	// It's a forge universal bucket
            	if(itemstack.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0)
            	{
            		// The fluid in bucket is liquid magic
            		if(FluidUtil.getFluidContained(itemstack).containsFluid(new FluidStack(StartupCommon.fluidMagic, 1000)))
            		{
            			return true;
            		}
            	}
            }
            return false;
        }
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
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item == Items.BUCKET)
            {
                return true;
            }
        	// It's a forge universal bucket
        	if(stack.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0)
        	{
        		return true;
        	}
        }

        return false;
	}



	@Override
	public void update() {
		ItemStack inputStack = getStackInSlot(0);

        if(inputStack != null)
        {
        	// Empty buckets are allowed
        	if(inputStack.getItem() == Items.BUCKET)
        	{
    			if(tank.getFluidAmount() - 1000 > 0 && getStackInSlot(1) == null)
    			{
    				tank.drain(1000, true);
    				decrStackSize(0, 1);
    				ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, StartupCommon.fluidMagic);
     		       	setInventorySlotContents(1, bucket);
    			}
        	}
        	// It's a forge universal bucket
        	if(inputStack.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0)
        	{
        		// The fluid in bucket is liquid magic
        		if(FluidUtil.getFluidContained(inputStack).containsFluid(new FluidStack(StartupCommon.fluidMagic, 1000)) && (getStackInSlot(1) == null || getStackInSlot(1).getItem() == Items.BUCKET))
        		{
        			if(tank.getFluidAmount() + 1000 <= tank.getCapacity())
        			{
        		       tank.fill(new FluidStack(StartupCommon.fluidMagic, 1000), true);
        		       decrStackSize(0, 1);
        		       if(itemStacks[1] != null)
        		       {
        		    	   itemStacks[1].stackSize++;
        		       }
        		       else
        		       {
        		    	   itemStacks[1] = new ItemStack(Items.BUCKET);
        		       }
        		       setInventorySlotContents(1, itemStacks[1]);
        			}
        		}
        	}
        }
	}



}