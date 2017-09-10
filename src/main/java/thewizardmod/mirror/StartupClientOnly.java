package thewizardmod.mirror;

import thewizardmod.runeSlab.RendererRuneSlab;
import thewizardmod.runeSlab.TileEntityRuneSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;
	    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:magic_mirror", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemMirror, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

	    ModelResourceLocation glassModelResourceLocation = new ModelResourceLocation("thewizardmod:mirror_glass", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.mirrorGlass, DEFAULT_ITEM_SUBTYPE, glassModelResourceLocation);


}

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
