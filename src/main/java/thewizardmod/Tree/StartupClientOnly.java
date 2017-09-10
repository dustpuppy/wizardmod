package thewizardmod.Tree;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
	    ModelResourceLocation cherryLogResourceLocation = new ModelResourceLocation("thewizardmod:cherry_log", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemCherryLog, DEFAULT_ITEM_SUBTYPE, cherryLogResourceLocation);

	    ModelResourceLocation cherryLeavesResourceLocation = new ModelResourceLocation("thewizardmod:cherry_leaves", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemCherryLeaves, DEFAULT_ITEM_SUBTYPE, cherryLeavesResourceLocation);

	    ModelResourceLocation saplingCherryResourceLocation = new ModelResourceLocation("thewizardmod:cherry_sapling", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemCherrySapling, DEFAULT_ITEM_SUBTYPE, saplingCherryResourceLocation);

  
}

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
  

}
