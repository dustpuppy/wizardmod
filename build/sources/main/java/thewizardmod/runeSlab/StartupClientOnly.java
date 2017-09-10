package thewizardmod.runeSlab;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:runeSlab", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemRuneSlab, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRuneSlab.class, new RendererRuneSlab());

}

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
