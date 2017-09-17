package thewizardmod.FarmFeeder;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thewizardmod.TheWizardMod;

public class StartupCommon
{

	  public static BlockFarmFeeder blockMachine;
	  public static ItemBlock itemBlockMachine;
	
	public static void preInitCommon()
	{
	    blockMachine = (BlockFarmFeeder)(new BlockFarmFeeder().setUnlocalizedName("twm_feeder"));
	    blockMachine.setRegistryName("feeder");
	    GameRegistry.register(blockMachine);

	    itemBlockMachine = new ItemBlock(blockMachine);
	    itemBlockMachine.setRegistryName(blockMachine.getRegistryName());
	    GameRegistry.register(itemBlockMachine);

	    GameRegistry.registerTileEntity(TileEntityFarmFeeder.class, TheWizardMod.MODID + "TileEntityFeeder");


}

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}
}
