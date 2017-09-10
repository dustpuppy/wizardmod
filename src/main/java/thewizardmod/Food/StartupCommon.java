package thewizardmod.Food;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
	  public static MagicBread magicBread;
	  public static MagicCookie magicCookie;

  public static void preInitCommon()
  {
	magicBread = (MagicBread)(new MagicBread(4, 4, false).setUnlocalizedName("twm_magic_bread"));
	magicBread.setRegistryName("magic_bread_registry_name");
    GameRegistry.register(magicBread);

    magicCookie = (MagicCookie)(new MagicCookie(4, 4, false).setUnlocalizedName("twm_magic_cookie"));
    magicCookie.setRegistryName("magic_cookie_registry_name");
    GameRegistry.register(magicCookie);
}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }
}
