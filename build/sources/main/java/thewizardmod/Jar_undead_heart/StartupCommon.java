package thewizardmod.Jar_undead_heart;

import thewizardmod.TheWizardMod;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static BlockJar_undead_heart jar;
  public static ItemBlock itemJar;



  public static void preInitCommon()
  {
    jar = (BlockJar_undead_heart)(new BlockJar_undead_heart().setUnlocalizedName("twm_magic_jar"));
    jar.setRegistryName("jar");
    GameRegistry.register(jar);

    itemJar = new ItemBlock(jar);
    itemJar.setRegistryName(jar.getRegistryName());
    GameRegistry.register(itemJar);

    GameRegistry.registerTileEntity(TileEntityJar_undead_heart.class, TheWizardMod.MODID + "TileEntityJar");
  
}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
