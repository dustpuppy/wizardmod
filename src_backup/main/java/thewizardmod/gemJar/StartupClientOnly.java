package thewizardmod.gemJar;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:jar", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemJar, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGemJar.class, new RendererGemJar());

}

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
