package thewizardmod.fluids;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thewizardmod.TheWizardMod;

public class StartupCommon
{

	public static FluidPoison fluidPoison = new FluidPoison();
	public static BlockFluidPoison blockFluidPoison;
	public static ItemBlock itemFluidPoison;
	
	public static FluidMagic fluidMagic = new FluidMagic();
	public static BlockFluidMagic blockFluidMagic;
	public static ItemBlock itemFluidMagic;
	

	  public static BlockTank blockTank;
	  public static ItemBlock itemBlockTank;
	
	public static void preInitCommon()
	{
		FluidRegistry.registerFluid(fluidPoison);
		fluidPoison.setUnlocalizedName("twm_fluid_poison");
		
		blockFluidPoison = new BlockFluidPoison(fluidPoison);
		
		blockFluidPoison.setRegistryName("blockFluid");
		blockFluidPoison.setUnlocalizedName("twm_fluid_poison");
	    GameRegistry.register(blockFluidPoison);


		itemFluidPoison = new ItemBlock(blockFluidPoison);
		itemFluidPoison.setRegistryName(blockFluidPoison.getRegistryName());
	    GameRegistry.register(itemFluidPoison);
	    
	    FluidRegistry.addBucketForFluid(fluidPoison);

	    
		FluidRegistry.registerFluid(fluidMagic);
		fluidMagic.setUnlocalizedName("twm_fluid_magic");
		
		blockFluidMagic = new BlockFluidMagic(fluidMagic);
		
		blockFluidMagic.setRegistryName("blockFluidMagic");
		blockFluidMagic.setUnlocalizedName("twm_fluid_magic");
	    GameRegistry.register(blockFluidMagic);


		itemFluidMagic = new ItemBlock(blockFluidMagic);
		itemFluidMagic.setRegistryName(blockFluidMagic.getRegistryName());
	    GameRegistry.register(itemFluidMagic);
	    
	    FluidRegistry.addBucketForFluid(fluidMagic);

	    
	    
	    blockTank = (BlockTank)(new BlockTank().setUnlocalizedName("twm_tank"));
	    blockTank.setRegistryName("tank");
	    GameRegistry.register(blockTank);

	    itemBlockTank = new ItemBlock(blockTank);
	    itemBlockTank.setRegistryName(blockTank.getRegistryName());
	    GameRegistry.register(itemBlockTank);

	    GameRegistry.registerTileEntity(TileEntityTank.class, TheWizardMod.MODID + "TileEntityTank");

	}

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}
}
