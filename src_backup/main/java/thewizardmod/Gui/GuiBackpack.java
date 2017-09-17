package thewizardmod.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.IInventory;

public class GuiBackpack extends GuiChest{

	public GuiBackpack(IInventory lowerInv) {
		super(Minecraft.getMinecraft().thePlayer.inventory, lowerInv);
	}


}
