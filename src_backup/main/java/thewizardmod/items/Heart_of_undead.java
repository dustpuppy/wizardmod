package thewizardmod.items;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thewizardmod.books.GuiWizardsGuide;

public class Heart_of_undead extends Item
{
  public Heart_of_undead()
  {
//    final int MAXIMUM_NUMBER_IN_STACK = 6;
//    this.setMaxStackSize(MAXIMUM_NUMBER_IN_STACK);
    this.setCreativeTab(CreativeTabs.MISC);   // the item will appear on the Miscellaneous tab in creative
  }

}
