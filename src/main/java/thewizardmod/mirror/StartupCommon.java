package thewizardmod.mirror;

import thewizardmod.TheWizardMod;
import thewizardmod.Food.Cherry;
import thewizardmod.Jar_undead_heart.TileEntityJar_undead_heart;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static Mirror mirror;
  public static ItemMirror itemMirror;
  public static MirrorGlass mirrorGlass;



  public static void preInitCommon()
  {
    mirror = (Mirror)(new Mirror().setUnlocalizedName("twm_magic_mirror"));
    mirror.setRegistryName("magic_mirror");
    GameRegistry.register(mirror);

    itemMirror = (ItemMirror)(new ItemMirror().setUnlocalizedName("twm_mirror"));
    itemMirror.setRegistryName("mirror_registry_name");
    GameRegistry.register(itemMirror);
   
    GameRegistry.registerTileEntity(TileEntityBlockMirror.class, TheWizardMod.MODID + "TileEntityMirror");

    mirrorGlass = (MirrorGlass)(new MirrorGlass().setUnlocalizedName("twm_mirror_glass"));
    mirrorGlass.setRegistryName("mirror_glass_registry_name");
    GameRegistry.register(mirrorGlass);
  
}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
