package thewizardmod.pipe;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thewizardmod.TheWizardMod;

public class StartupCommon
{

	  public static BlockPipe blockTank;
	  public static ItemBlock itemBlockTank;
	
	public static void preInitCommon()
	{
	    
	    blockTank = (BlockPipe)(new BlockPipe().setUnlocalizedName("twm_pipe"));
	    blockTank.setRegistryName("pipe");
	    GameRegistry.register(blockTank);

	    itemBlockTank = new ItemBlock(blockTank);
	    itemBlockTank.setRegistryName(blockTank.getRegistryName());
	    GameRegistry.register(itemBlockTank);

	    GameRegistry.registerTileEntity(TileEntityPipe.class, TheWizardMod.MODID + "TileEntityPipe");


}

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}
}
