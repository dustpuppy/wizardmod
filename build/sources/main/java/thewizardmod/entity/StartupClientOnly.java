package thewizardmod.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class StartupClientOnly
{
	
  public static void preInitClientOnly()
  {
      RenderingRegistry.registerEntityRenderingHandler(EntityWeirdZombie.class, RenderWeirdZombie.FACTORY);
  }


  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }

  
}
