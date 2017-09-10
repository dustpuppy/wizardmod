package thewizardmod.Backpacks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;

public class ContainerBackpack extends ContainerChest{

	public ContainerBackpack(IInventory playerInventory, IInventory chestInventory, EntityPlayer player) {
		super(playerInventory, chestInventory, player);
	}

}
