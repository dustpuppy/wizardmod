package thewizardmod.Wands;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
	  public static ItemWoodenWand woodenWand;
	  public static ItemGoldenWand goldenWand;
	  public static ItemIronWand ironWand;
	  public static ItemBlazeWand blazeWand;

  public static void preInitCommon() {
    woodenWand = (ItemWoodenWand) (new ItemWoodenWand().setUnlocalizedName("twm_wooden_wand"));
    woodenWand.setRegistryName("wooden_wand_registry_name");
    GameRegistry.register(woodenWand);

    goldenWand = (ItemGoldenWand) (new ItemGoldenWand().setUnlocalizedName("twm_golden_wand"));
    goldenWand.setRegistryName("golden_wand_registry_name");
    GameRegistry.register(goldenWand);

    ironWand = (ItemIronWand) (new ItemIronWand().setUnlocalizedName("twm_iron_wand"));
    ironWand.setRegistryName("iron_wand_registry_name");
    GameRegistry.register(ironWand);

    blazeWand = (ItemBlazeWand) (new ItemBlazeWand().setUnlocalizedName("twm_blaze_wand"));
    blazeWand.setRegistryName("blaze_wand_registry_name");
    GameRegistry.register(blazeWand);
}

  public static void initCommon() {
  }

  public static void postInitCommon() {
  }
}

