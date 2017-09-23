package thewizardmod.Backpacks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thewizardmod.TheWizardMod;
import thewizardmod.items.ShadowDust;

public class StartupCommon
{
	  public static SmallBackpack smallBackpack;
	  public static NormalBackpack normalBackpack;
	  public static BigBackpack bigBackpack;
  
  public static void preInitCommon()
  {
	  TheWizardMod.logger.info("Initializing Backpacks");

	  smallBackpack = (SmallBackpack)(new SmallBackpack().setUnlocalizedName("twm_small_backpack"));
	  smallBackpack.setRegistryName("small_backpack_registry_name");
	  GameRegistry.register(smallBackpack);

	  normalBackpack = (NormalBackpack)(new NormalBackpack().setUnlocalizedName("twm_normal_backpack"));
	  normalBackpack.setRegistryName("normal_backpack_registry_name");
	  GameRegistry.register(normalBackpack);

	  bigBackpack = (BigBackpack)(new BigBackpack().setUnlocalizedName("twm_big_backpack"));
	  bigBackpack.setRegistryName("big_backpack_registry_name");
	  GameRegistry.register(bigBackpack);

	}

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}
}
