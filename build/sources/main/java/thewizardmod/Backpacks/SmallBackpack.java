package thewizardmod.Backpacks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thewizardmod.TheWizardMod;
import thewizardmod.chest.TileEntityInventoryChest;

public class SmallBackpack extends Item
{
	public static int backpackSize = 9;
  public SmallBackpack()
  {
    this.setMaxStackSize(1);
    this.setCreativeTab(CreativeTabs.INVENTORY);
  }

  @Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
	  super.onCreated(stack, worldIn, playerIn);
	  new InventoryBackpack(stack, backpackSize);
	}


  @Override
public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn,
		World worldIn, EntityPlayer playerIn, EnumHand hand) {
	  
	  if(!worldIn.isRemote){
		  playerIn.openGui(TheWizardMod.instance, thewizardmod.TheWizardMod.GUI_ID_SMALL_BACKPACK, worldIn, 0, 0, 0);
	  }
	return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
}
}
