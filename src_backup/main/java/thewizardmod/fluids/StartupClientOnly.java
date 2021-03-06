package thewizardmod.fluids;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	  registerFluid(StartupCommon.itemFluidPoison, StartupCommon.blockFluidPoison, "poison");
	  registerFluid(StartupCommon.itemFluidMagic, StartupCommon.blockFluidMagic, "magic");

  
	  final int DEFAULT_ITEM_SUBTYPE = 0;
	  ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:tank", "inventory");
	  ModelLoader.setCustomModelResourceLocation(StartupCommon.itemBlockTank, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
	  
	  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new RendererTank());


  }


  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }

  public static void registerFluid(Item item, BlockFluidClassic fluidBlock, String name){
	  
	  final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(fluidBlock.getRegistryName(), name);

	  ModelBakery.registerItemVariants(item);
	  
	  ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {

		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack) {
			return modelResourceLocation;
		}
		  
	  });
	  
	  ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return modelResourceLocation;
			}
	  });
	  
  }
  
}
