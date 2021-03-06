package thewizardmod.items;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thewizardmod.Materials;
import thewizardmod.books.GuiWizardsGuide;

public class MagicIronShovel extends ItemSpade
{
  public MagicIronShovel()
  {
    super(Materials.magicIronToolMaterial);
    final int MAXIMUM_NUMBER_IN_STACK = 1;
    this.setMaxStackSize(MAXIMUM_NUMBER_IN_STACK);
    this.setCreativeTab(CreativeTabs.TOOLS);
  }

}
