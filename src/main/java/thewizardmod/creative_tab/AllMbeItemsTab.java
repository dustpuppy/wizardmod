package thewizardmod.creative_tab;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.datafix.fixes.SpawnEggNames;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.Wands.StartupCommon;

public class AllMbeItemsTab extends CreativeTabs {
  public AllMbeItemsTab(String label) {
    super(label);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public Item getTabIconItem() {
    return StartupCommon.blazeWand;
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
    
    // Put all our fluids into the tab
    itemsToShowOnTab.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, thewizardmod.fluids.StartupCommon.fluidPoison));
    itemsToShowOnTab.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, thewizardmod.fluids.StartupCommon.fluidMagic));
    

//    itemsToShowOnTab.add();
  }

}
