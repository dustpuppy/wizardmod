package thewizardmod.runes;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thewizardmod.books.GuiWizardsGuide;

public class Ehwaz extends Item
{
  public Ehwaz()
  {
//    final int MAXIMUM_NUMBER_IN_STACK = 6;
//    this.setMaxStackSize(MAXIMUM_NUMBER_IN_STACK);
    this.setCreativeTab(CreativeTabs.MATERIALS);   // the item will appear on the Miscellaneous tab in creative
  }

}
