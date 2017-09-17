package thewizardmod.lightBlock;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static LightBlock lightBlock;  // this holds the unique instance of your block

  public static void preInitCommon()
  {
    lightBlock = (LightBlock)(new LightBlock().setUnlocalizedName("twm_light_block"));
    lightBlock.setRegistryName("light_block");
    GameRegistry.register(lightBlock);

  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
