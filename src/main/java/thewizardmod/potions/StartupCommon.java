package thewizardmod.potions;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 *
 * The Startup classes for this example are called during startup, in the following order:
 *  preInitCommon
 *  preInitClientOnly
 *  initCommon
 *  initClientOnly
 *  postInitCommon
 *  postInitClientOnly
 *  See MinecraftByExample class for more information
 */
public class StartupCommon
{
  public static PotionBottles itemVariants;  // this holds the unique instance of your block

  public static void preInitCommon()
  {
    itemVariants = (PotionBottles)(new PotionBottles().setUnlocalizedName("twm_potion"));
    itemVariants.setRegistryName("twm_potion_registry_name");
    GameRegistry.register(itemVariants);
 }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }
}
