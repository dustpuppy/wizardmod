package thewizardmod.Wands;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
	    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:wooden_wand", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.woodenWand, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

	    ModelResourceLocation itemGoldenResourceLocation = new ModelResourceLocation("thewizardmod:golden_wand", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.goldenWand, DEFAULT_ITEM_SUBTYPE, itemGoldenResourceLocation);

	    ModelResourceLocation itemIronResourceLocation = new ModelResourceLocation("thewizardmod:iron_wand", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironWand, DEFAULT_ITEM_SUBTYPE, itemIronResourceLocation);

	    ModelResourceLocation itemBlazeResourceLocation = new ModelResourceLocation("thewizardmod:blaze_wand", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.blazeWand, DEFAULT_ITEM_SUBTYPE, itemBlazeResourceLocation);

  }

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
