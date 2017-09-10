package thewizardmod.gemJar;

import thewizardmod.TheWizardMod;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static BlockGemJar jar;
  public static ItemBlock itemJar;



  public static void preInitCommon()
  {
    jar = (BlockGemJar)(new BlockGemJar().setUnlocalizedName("twm_magic_gem_jar"));
    jar.setRegistryName("jar");
    GameRegistry.register(jar);

    itemJar = new ItemBlock(jar);
    itemJar.setRegistryName(jar.getRegistryName());
    GameRegistry.register(itemJar);

    GameRegistry.registerTileEntity(TileEntityGemJar.class, TheWizardMod.MODID + "TileEntityJar");
  
}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
