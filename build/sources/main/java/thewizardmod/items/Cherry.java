package thewizardmod.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thewizardmod.World.StructureDungeon;
import thewizardmod.books.GuiWizardsGuide;

public class Cherry extends Item
{
  public Cherry()
  {
//    final int MAXIMUM_NUMBER_IN_STACK = 6;
//    this.setMaxStackSize(MAXIMUM_NUMBER_IN_STACK);
    this.setCreativeTab(CreativeTabs.FOOD);   // the item will appear on the Miscellaneous tab in creative
  }

}
