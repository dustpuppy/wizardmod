package thewizardmod.creative_tab;

import java.util.List;

import thewizardmod.items.StartupCommon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MyTab extends CreativeTabs {
  public MyTab(String label) {
    super(label);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public Item getTabIconItem() {
    return StartupCommon.zombie;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void displayAllRelevantItems(List<ItemStack> itemsToShowOnTab)
  {
    for (Item item : Item.REGISTRY) {
      if (item != null) {
        if (item.getUnlocalizedName().contains(".twm")) {
          item.getSubItems(item, this, itemsToShowOnTab);  // add all sub items to the list
        }
      }
    }
    

  }

}
