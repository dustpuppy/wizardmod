package thewizardmod.fluids;


import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
 
public class ContainerTank extends Container {
 
    public final TileEntityTank tileTank;

    public ContainerTank(InventoryPlayer playerInventory, TileEntityTank te)
    {
        this.tileTank = te;
        this.addSlotToContainer(new InputSlot(tileTank, 0, 26, 19));
        this.addSlotToContainer(new OutputSlot(te, 1, 26, 55));

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

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileTank);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);

        }

    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileTank.setField(id, data);
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
 
            if (index < 27) {
                if (!this.mergeItemStack(itemstack1, 27, this.inventorySlots.size(), true)) {
                    return null;
                }
            }
            else if(!this.mergeItemStack(itemstack1, 0, 27, false)) {
                return null;
            }
 
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            }
            else {
                slot.onSlotChanged();
            }
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

	public class OutputSlot extends Slot {

	    public OutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
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
            		if(FluidUtil.getFluidContained(itemstack).containsFluid(new FluidStack(StartupCommon.fluidMagic, 1000)))
            		{
            			return true;
            		}
            	}
            }
            
            return false;
		}
	}

}