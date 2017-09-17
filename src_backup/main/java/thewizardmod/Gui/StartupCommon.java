package thewizardmod.Gui;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thewizardmod.ores.ShadowDust_Ore;


public class StartupCommon
{
  public static void preInitCommon()
  {
  }

  public static void initCommon()
  {
	  NetworkRegistry.INSTANCE.registerGuiHandler(thewizardmod.TheWizardMod.instance, new GuiHandler());
  }

  public static void postInitCommon()
  {
  }

}
