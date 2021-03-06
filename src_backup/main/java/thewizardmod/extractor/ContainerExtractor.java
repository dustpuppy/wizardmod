package thewizardmod.extractor;


import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thewizardmod.magicInjector.TileEntityMagicInjector;
import thewizardmod.ores.StartupCommon;
 
public class ContainerExtractor extends Container {
 
    public final TileEntityExtractor tileTank;

    public ContainerExtractor(InventoryPlayer playerInventory, TileEntityExtractor te)
    {
        this.tileTank = te;
        this.addSlotToContainer(new InputSlot(tileTank, 0, 26, 19));
        this.addSlotToContainer(new OutputSlot(tileTank, 1, 26, 55));
        this.addSlotToContainer(new OreInputSlot(tileTank, 2, 80, 19));
        this.addSlotToContainer(new StoneSlot(tileTank, 3, 134, 19));
        this.addSlotToContainer(new FuelInputSlot(tileTank, 4, 80, 54));

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
 
            if (index < 5) {
                if (!this.mergeItemStack(itemstack1, 5, this.inventorySlots.size(), true)) {
                    return null;
                }
            }
            else
            {
            	// FIXME: Dirty workaround to prevent shift clicking stone into output slot
            	if(itemstack1.getItem() == Item.getItemFromBlock(Blocks.STONE))
            	{
            		return null;
            	}

            	if(!this.mergeItemStack(itemstack1, 0, 5, false)) 
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
    
    

	public class InputSlot extends Slot {

	    public InputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
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
            return false;
		}
		
		
	}

	public class OreInputSlot extends Slot {

	    public OreInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert the given item into this slot
		@Override
		public boolean isItemValid(ItemStack itemstack) {
            if(itemstack != null)
            {
            	if(itemstack.getItem() == Item.getItemFromBlock(StartupCommon.shadowdustOre))
            	{
            		return true;
            	}
            }
            
            return false;
		}
	}

	public class StoneSlot extends Slot {

	    public StoneSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert the given item into this slot
		@Override
		public boolean isItemValid(ItemStack itemstack) {
            return false;
		}
	}

	public class FuelInputSlot extends Slot {

	    public FuelInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert the given item into this slot
		@Override
		public boolean isItemValid(ItemStack itemstack) {
            if(itemstack != null)
            {
            	return tileTank.isItemFuel(itemstack);
            }
            
            return false;
		}
	}

}