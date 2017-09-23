package thewizardmod.books;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BookMachines extends ItemBook
{
  public BookMachines()
  {
    this.setCreativeTab(CreativeTabs.MISC); 
  }

  @SideOnly(Side.CLIENT)
  @Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
	  	Minecraft.getMinecraft().displayGuiScreen(new GuiMachines());
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
}
