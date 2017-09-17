package thewizardmod.FarmCrops;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thewizardmod.TheWizardMod;

public class StartupCommon
{

	  public static BlockFarmCrops blockMachine;
	  public static ItemBlock itemBlockMachine;
	
	public static void preInitCommon()
	{
	    blockMachine = (BlockFarmCrops)(new BlockFarmCrops().setUnlocalizedName("twm_crop_farm"));
	    blockMachine.setRegistryName("crop_farm");
	    GameRegistry.register(blockMachine);

	    itemBlockMachine = new ItemBlock(blockMachine);
	    itemBlockMachine.setRegistryName(blockMachine.getRegistryName());
	    GameRegistry.register(itemBlockMachine);

	    GameRegistry.registerTileEntity(TileEntityFarmCrops.class, TheWizardMod.MODID + "TileEntityCropFarm");


}

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}
}
