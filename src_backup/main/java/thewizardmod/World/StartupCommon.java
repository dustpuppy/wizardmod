package thewizardmod.World;

import net.minecraftforge.fml.common.registry.GameRegistry;
import thewizardmod.ores.ShadowDust_Ore;


public class StartupCommon
{
  public static void preInitCommon()
  {
  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
	  GameRegistry.registerWorldGenerator(new WorldGen(), 0);
  }

}
