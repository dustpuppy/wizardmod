package thewizardmod.torch;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static Torch torch;  
  public static ItemBlock itemTorch;

  public static void preInitCommon()
  {
	  torch = (Torch)(new Torch().setUnlocalizedName("twm_torch"));
	  torch.setRegistryName("torch");
    GameRegistry.register(torch);

    itemTorch = new ItemBlock(torch);
    itemTorch.setRegistryName(torch.getRegistryName());
    GameRegistry.register(itemTorch);
  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
