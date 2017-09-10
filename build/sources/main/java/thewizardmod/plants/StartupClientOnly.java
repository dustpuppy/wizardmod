package thewizardmod.plants;

import thewizardmod.plants.StartupCommon;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
	    ModelResourceLocation wheatSeedsModelResourceLocation = new ModelResourceLocation("thewizardmod:wheat_seeds", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.wheatSeeds, DEFAULT_ITEM_SUBTYPE, wheatSeedsModelResourceLocation);

	    ModelResourceLocation wheatModelResourceLocation = new ModelResourceLocation("thewizardmod:wheat", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.wheat, DEFAULT_ITEM_SUBTYPE, wheatModelResourceLocation);

  }

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
