package thewizardmod.KeyBindings;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import thewizardmod.Tree.StartupCommon;

public class StartupClientOnly
{
	public static KeyBinding MY_KEYBINDING = new KeyBinding("key.wandFocusSelect", Keyboard.KEY_M, "category.thewizardmod");
	
  public static void preInitClientOnly()
  {
	  MinecraftForge.EVENT_BUS.register(MY_KEYBINDING);
  }

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
