package thewizardmod.altar;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:altar", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemAltar, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new RendererAltar());

}

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
