package thewizardmod.ores;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static ShadowDust_Ore shadowdustOre;
  public static ItemBlock itemShadowDustOre;



  public static void preInitCommon()
  {
    shadowdustOre = (ShadowDust_Ore)(new ShadowDust_Ore().setUnlocalizedName("twm_shadowdust_ore"));
    shadowdustOre.setRegistryName("shadowdust_ore");
    GameRegistry.register(shadowdustOre);

    itemShadowDustOre = new ItemBlock(shadowdustOre);
    itemShadowDustOre.setRegistryName(shadowdustOre.getRegistryName());
    GameRegistry.register(itemShadowDustOre);

    
  
}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
