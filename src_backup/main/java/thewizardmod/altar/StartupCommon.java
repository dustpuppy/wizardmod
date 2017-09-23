package thewizardmod.altar;

import thewizardmod.TheWizardMod;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static BlockAltar altar;
  public static ItemBlock itemAltar;



  public static void preInitCommon()
  {

  	TheWizardMod.logger.info("Setting up Altar");

  	altar = (BlockAltar)(new BlockAltar().setUnlocalizedName("twm_altar"));
    altar.setRegistryName("altar");
    GameRegistry.register(altar);

    itemAltar = new ItemBlock(altar);
    itemAltar.setRegistryName(altar.getRegistryName());
    GameRegistry.register(itemAltar);

    GameRegistry.registerTileEntity(TileEntityAltar.class, TheWizardMod.MODID + "TileEntityAltar");
  
}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
