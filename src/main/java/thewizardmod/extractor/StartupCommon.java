package thewizardmod.extractor;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thewizardmod.TheWizardMod;

public class StartupCommon
{

	  public static BlockExtractor blockTank;
	  public static ItemBlock itemBlockTank;
	
	public static void preInitCommon()
	{
	    blockTank = (BlockExtractor)(new BlockExtractor().setUnlocalizedName("twm_magic_extractor"));
	    blockTank.setRegistryName("magic_extractor");
	    GameRegistry.register(blockTank);

	    itemBlockTank = new ItemBlock(blockTank);
	    itemBlockTank.setRegistryName(blockTank.getRegistryName());
	    GameRegistry.register(itemBlockTank);

	    GameRegistry.registerTileEntity(TileEntityExtractor.class, TheWizardMod.MODID + "TileEntityExtractor");


}

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}
}
