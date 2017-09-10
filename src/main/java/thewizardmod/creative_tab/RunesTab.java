package thewizardmod.creative_tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import thewizardmod.Wands.StartupCommon;

public class RunesTab extends CreativeTabs {
  public RunesTab(String label) {
    super(label);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public Item getTabIconItem() {
    return thewizardmod.runes.StartupCommon.fehu;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void displayAllRelevantItems(List<ItemStack> itemsToShowOnTab)
  {
    for (Item item : Item.REGISTRY) {
      if (item != null) {
        if (item.getUnlocalizedName().contains(".tw_rune")) {
          item.getSubItems(item, this, itemsToShowOnTab);  // add all sub items to the list
        }
      }
    }
    
  }

}
