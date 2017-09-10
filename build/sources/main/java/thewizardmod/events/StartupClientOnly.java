package thewizardmod.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import thewizardmod.ores.StartupCommon;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	  MinecraftForge.EVENT_BUS.register(new ClientEvents());
	  MinecraftForge.EVENT_BUS.register(new MobDropEvents());
  }

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
