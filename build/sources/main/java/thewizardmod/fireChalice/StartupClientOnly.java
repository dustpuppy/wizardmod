package thewizardmod.fireChalice;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:fireChalice", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemFireChalice, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChalice.class, new RendererChalice());

}

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
