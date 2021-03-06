package thewizardmod.pedestral;

import thewizardmod.TheWizardMod;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static BlockPedestral pedestral;
  public static ItemBlock itemPedestral;



  public static void preInitCommon()
  {
    pedestral = (BlockPedestral)(new BlockPedestral().setUnlocalizedName("twm_pedestral"));
    pedestral.setRegistryName("pedestral");
    GameRegistry.register(pedestral);

    itemPedestral = new ItemBlock(pedestral);
    itemPedestral.setRegistryName(pedestral.getRegistryName());
    GameRegistry.register(itemPedestral);

    GameRegistry.registerTileEntity(TileEntityPedestral.class, TheWizardMod.MODID + "TileEntityPedestral");
  
}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
