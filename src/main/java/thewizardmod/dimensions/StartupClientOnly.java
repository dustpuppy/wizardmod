package thewizardmod.dimensions;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;

//	    ModelResourceLocation testFlintResourceLocation = new ModelResourceLocation("thewizardmod:flint_and_steel", "inventory");
//	    ModelLoader.setCustomModelResourceLocation(StartupCommon.magicFlint, DEFAULT_ITEM_SUBTYPE, testFlintResourceLocation);

	    ModelResourceLocation portalFrameResourceLocation = new ModelResourceLocation("thewizardmod:portalFrame", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemPortalFrame, DEFAULT_ITEM_SUBTYPE, portalFrameResourceLocation);

	    ModelResourceLocation fireResourceLocation = new ModelResourceLocation("thewizardmod:magicFire", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemFire, DEFAULT_ITEM_SUBTYPE, fireResourceLocation);

}

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
