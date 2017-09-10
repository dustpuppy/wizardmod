package thewizardmod.creative_tab;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHardenedClay;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StartupCommon
{
  public static AllMbeItemsTab allMbeItemsTab; 
  public static RunesTab runesTab;
  public static BooksTab books;

  public static void preInitCommon()
  {

    allMbeItemsTab = new AllMbeItemsTab("twm_creative_tab_all");
    runesTab = new RunesTab("twm_runes_tab");
    books = new BooksTab("twm_books_tab");
  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }
}
