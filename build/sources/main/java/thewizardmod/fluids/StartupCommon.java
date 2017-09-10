package thewizardmod.fluids;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{

	public static FluidPoison fluidPoison = new FluidPoison();
	
	public static BlockFluidPoison blockFluidPoison;
	
	public static ItemBlock itemFluidPoison;
	

	public static void preInitCommon()
	{
		boolean reg = FluidRegistry.registerFluid(fluidPoison);
		fluidPoison.setUnlocalizedName("twm_fluid_poison");
		
		blockFluidPoison = new BlockFluidPoison(fluidPoison);
		
		blockFluidPoison.setRegistryName("blockFluid");
		blockFluidPoison.setUnlocalizedName("twm_fluid_poison");
	    GameRegistry.register(blockFluidPoison);


		itemFluidPoison = new ItemBlock(blockFluidPoison);
		itemFluidPoison.setRegistryName(blockFluidPoison.getRegistryName());
	    GameRegistry.register(itemFluidPoison);
	    
//	    bucketFluidPoison = new BucketFluidPoison(blockFluidPoison);
//	    bucketFluidPoison.setRegistryName("bucketFluidPoison");
//	    GameRegistry.register(bucketFluidPoison);
	    
//	    FluidContainerRegistry.registerFluidContainer(new FluidStack(fluidPoison, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketFluidPoison), new ItemStack(Items.BUCKET));
	    FluidRegistry.addBucketForFluid(fluidPoison);

	    
	}

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}
}