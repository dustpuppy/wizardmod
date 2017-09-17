package thewizardmod.torch;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
  ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:torch", "inventory");
  ModelLoader.setCustomModelResourceLocation(StartupCommon.itemTorch, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
  }

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
