package thewizardmod.fireChalice;

import thewizardmod.TheWizardMod;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static BlockChalice fireChalice;
  public static ItemBlock itemFireChalice;



  public static void preInitCommon()
  {
    fireChalice = (BlockChalice)(new BlockChalice().setUnlocalizedName("twm_fire_chalice"));
    fireChalice.setRegistryName("fireChalice");
    GameRegistry.register(fireChalice);

    itemFireChalice = new ItemBlock(fireChalice);
    itemFireChalice.setRegistryName(fireChalice.getRegistryName());
    GameRegistry.register(itemFireChalice);

    GameRegistry.registerTileEntity(TileEntityChalice.class, TheWizardMod.MODID + "TileEntityChalice");
  
}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
