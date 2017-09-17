package thewizardmod.runeSlab;

import thewizardmod.TheWizardMod;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
  public static BlockRuneSlab runeSlab;
  public static ItemBlock itemRuneSlab;



  public static void preInitCommon()
  {
    runeSlab = (BlockRuneSlab)(new BlockRuneSlab().setUnlocalizedName("twm_rune_slab"));
    runeSlab.setRegistryName("runeSlab");
    GameRegistry.register(runeSlab);

    itemRuneSlab = new ItemBlock(runeSlab);
    itemRuneSlab.setRegistryName(runeSlab.getRegistryName());
    GameRegistry.register(itemRuneSlab);

    GameRegistry.registerTileEntity(TileEntityRuneSlab.class, TheWizardMod.MODID + "TileEntityRuneSlab");
  
}

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
