package thewizardmod.Food;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
	  public static MagicBread magicBread;
	  public static MagicCookie magicCookie;
	  public static Cherry cherry;

  public static void preInitCommon()
  {
	magicBread = (MagicBread)(new MagicBread(4, 4, false).setUnlocalizedName("twm_magic_bread"));
	magicBread.setRegistryName("magic_bread_registry_name");
    GameRegistry.register(magicBread);

    magicCookie = (MagicCookie)(new MagicCookie(4, 4, false).setUnlocalizedName("twm_magic_cookie"));
    magicCookie.setRegistryName("magic_cookie_registry_name");
    GameRegistry.register(magicCookie);

    cherry = (Cherry)(new Cherry(4, 4, false).setUnlocalizedName("twm_cherry"));
    cherry.setRegistryName("cherry_registry_name");
    GameRegistry.register(cherry);

}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }
}
