package thewizardmod.Gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import thewizardmod.Backpacks.BigBackpack;
import thewizardmod.Backpacks.ContainerBackpack;
import thewizardmod.Backpacks.InventoryBackpack;
import thewizardmod.Backpacks.NormalBackpack;
import thewizardmod.Backpacks.SmallBackpack;
import thewizardmod.chest.ContainerChest;
import thewizardmod.chest.TileEntityInventoryChest;
import thewizardmod.magicInjector.ContainerMagicInjector;
import thewizardmod.magicInjector.GuiMagicInjector;
import thewizardmod.magicInjector.TileEntityMagicInjector;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case thewizardmod.TheWizardMod.GUI_ID_SMALL_BACKPACK:
			return new ContainerBackpack(player.inventory, new InventoryBackpack(player.inventory.getCurrentItem(),SmallBackpack.backpackSize), player);
		case thewizardmod.TheWizardMod.GUI_ID_NORMAL_BACKPACK:
			return new ContainerBackpack(player.inventory, new InventoryBackpack(player.inventory.getCurrentItem(),NormalBackpack.backpackSize), player);
		case thewizardmod.TheWizardMod.GUI_ID_BIG_BACKPACK:
			return new ContainerBackpack(player.inventory, new InventoryBackpack(player.inventory.getCurrentItem(),BigBackpack.backpackSize), player);
		case thewizardmod.TheWizardMod.GUI_ID_MAGIC_CHEST:
			return new ContainerChest(player.inventory, (TileEntityInventoryChest) world.getTileEntity(new BlockPos(x, y, z)));
		case thewizardmod.TheWizardMod.GUI_ID_MAGICINJECTOR:
			return new ContainerMagicInjector(player.inventory, (TileEntityMagicInjector) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		switch(ID){
		case thewizardmod.TheWizardMod.GUI_ID_SMALL_BACKPACK:
			return new GuiBackpack(new InventoryBackpack(player.inventory.getCurrentItem(),SmallBackpack.backpackSize));
		case thewizardmod.TheWizardMod.GUI_ID_NORMAL_BACKPACK:
			return new GuiBackpack(new InventoryBackpack(player.inventory.getCurrentItem(),NormalBackpack.backpackSize));
		case thewizardmod.TheWizardMod.GUI_ID_BIG_BACKPACK:
			return new GuiBackpack(new InventoryBackpack(player.inventory.getCurrentItem(),BigBackpack.backpackSize));
		case thewizardmod.TheWizardMod.GUI_ID_MAGIC_CHEST:
			return new GuiInventoryChest(player.inventory, (TileEntityInventoryChest) world.getTileEntity(new BlockPos(x, y, z)));
		case thewizardmod.TheWizardMod.GUI_ID_MAGICINJECTOR:
			return new GuiMagicInjector(player.inventory, (TileEntityMagicInjector) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

}
