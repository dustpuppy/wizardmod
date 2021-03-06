package thewizardmod.camouflageBlock;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
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
public class StartupCommon
{
  public static BlockCamouflage blockCamouflage;  // this holds the unique instance of your block
  public static ItemBlock itemBlockCamouflage;  // this holds the unique instance of the ItemBlock corresponding to your block

  public static void preInitCommon()
  {
    blockCamouflage = (BlockCamouflage)(new BlockCamouflage().setUnlocalizedName("twm_block_camouflage"));
    blockCamouflage.setRegistryName("block_camouflage");
    GameRegistry.register(blockCamouflage);

    // We also need to create and register an ItemBlock for this block otherwise it won't appear in the inventory
    itemBlockCamouflage = new ItemBlock(blockCamouflage);
    itemBlockCamouflage.setRegistryName(blockCamouflage.getRegistryName());
    GameRegistry.register(itemBlockCamouflage);
  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
