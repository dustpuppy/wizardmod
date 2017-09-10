package thewizardmod.ores;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:shadowdust_ore", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemShadowDustOre, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);


}

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
