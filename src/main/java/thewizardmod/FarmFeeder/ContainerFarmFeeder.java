package thewizardmod.FarmFeeder;


import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import thewizardmod.ores.StartupCommon;
 
public class ContainerFarmFeeder extends Container {
 
    public final TileEntityFarmFeeder tileTank;

    public int slotSize = 14;
    
    public ContainerFarmFeeder(InventoryPlayer playerInventory, TileEntityFarmFeeder te)
    {
        this.tileTank = te;
        this.addSlotToContainer(new BucketInputSlot(tileTank, 0, 26, 19));
        this.addSlotToContainer(new BucketOutputSlot(tileTank, 1, 26, 55));
        this.addSlotToContainer(new BoneMealSlot(tileTank, 2, 80, 55));
        this.addSlotToContainer(new SeedSlot(tileTank, 3, 62, 55));
        
        for(int i = 0; i < 3; i++)
        {
        	for(int j = 0;j < 3; j++)
        	{
        		this.addSlotToContainer(new OutputSlot(tileTank, j + i * 3 + 5, 116 + j * 18, 19 + i * 18));
        	}
        }
        		

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }
    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileTank.isUseableByPlayer(playerIn);
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);
 
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
 
            if (index < slotSize) {
                if (!this.mergeItemStack(itemstack1, slotSize, this.inventorySlots.size(), true)) {
                    return null;
                }
            }
            else
            {
            	if(!this.mergeItemStack(itemstack1, 0, slotSize, false)) 
            	{
            		return null;
            	}
            	
            }
 
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            }
            else {
                slot.onSlotChanged();
            }
            
            if(itemstack1.stackSize == itemstack.stackSize)
            {
            	return null;
            }
            slot.onPickupFromSlot(playerIn, itemstack1);
        }
 
        return itemstack;
    }
    
    

	public class BucketInputSlot extends Slot {

	    public BucketInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert the given item into this slot
		@Override
		public boolean isItemValid(ItemStack itemstack) {
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
            		if(FluidUtil.getFluidContained(itemstack).containsFluid(new FluidStack(thewizardmod.fluids.StartupCommon.fluidMagic, 1000)))
            		{
            			return true;
            		}
            	}
            }
            
            return false;
		}
	}

	public class BucketOutputSlot extends Slot {

	    public BucketOutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert the given item into this slot
		@Override
		public boolean isItemValid(ItemStack itemstack) {
            return false;
		}
		
		
	}

	public class SeedSlot extends Slot {

	    public SeedSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert the given item into this slot
		@Override
		public boolean isItemValid(ItemStack itemstack) {
			if (itemstack.getItem() == Items.WHEAT_SEEDS
					|| itemstack.getItem() == Items.CARROT
					|| itemstack.getItem() == Items.POTATO
					|| itemstack.getItem() == Items.WHEAT
					|| itemstack.getItem() == Items.BEETROOT
					|| itemstack.getItem() == Items.PUMPKIN_SEEDS
					|| itemstack.getItem() == Items.MELON_SEEDS
					|| itemstack.getItem() == Items.BEETROOT_SEEDS
					|| itemstack.getItem() == Items.GOLDEN_APPLE
					|| itemstack.getItem() == Items.GOLDEN_CARROT
					) {
				return true;
			}
            return false;
		}
		
		
	}

	public class BoneMealSlot extends Slot {

	    public BoneMealSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert the given item into this slot
		@Override
		public boolean isItemValid(ItemStack itemstack) {
			if (itemstack.getItem() == Items.WHEAT_SEEDS
					|| itemstack.getItem() == Items.CARROT
					|| itemstack.getItem() == Items.POTATO
					|| itemstack.getItem() == Items.WHEAT
					|| itemstack.getItem() == Items.BEETROOT
					|| itemstack.getItem() == Items.PUMPKIN_SEEDS
					|| itemstack.getItem() == Items.MELON_SEEDS
					|| itemstack.getItem() == Items.BEETROOT_SEEDS
					|| itemstack.getItem() == Items.GOLDEN_APPLE
					|| itemstack.getItem() == Items.GOLDEN_CARROT
					) {
				return true;
			}
            return false;
		}
		
		
	}

	public class OutputSlot extends Slot {

	    public OutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert the given item into this slot
		@Override
		public boolean isItemValid(ItemStack itemstack) {
            return true;
		}
		
		
	}


}