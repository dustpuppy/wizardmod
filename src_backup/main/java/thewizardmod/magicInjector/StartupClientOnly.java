package thewizardmod.magicInjector;

import thewizardmod.pedestral.RendererPedestral;
import thewizardmod.pedestral.TileEntityPedestral;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * User: brandon3055
 * Date: 06/01/2015
 *
 * The Startup classes for this example are called during startup, in the following order:
 *  preInitCommon
 *  preInitClientOnly
 *  initCommon
 *  initClientOnly
 *  postInitCommon
 *  postInitClientOnly
 *  See MinecraftByExample class for more information
 */
public class StartupClientOnly
{
	public static void preInitClientOnly()
	{
    // This step is necessary in order to make your block render properly when it is an item (i.e. in the inventory
    //   or in your hand or thrown on the ground).
    // It must be done on client only, and must be done after the block has been created in Common.preinit().
    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:magicInjector", "inventory");
    final int DEFAULT_ITEM_SUBTYPE = 0;
    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemBlockInventoryAdvanced, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    
//    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagicInjector.class, new RendererMagicInjector());

  }

	public static void initClientOnly()
	{

	}

	public static void postInitClientOnly()
	{
	}
}
