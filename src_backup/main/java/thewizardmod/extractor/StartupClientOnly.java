package thewizardmod.extractor;

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
	  final int DEFAULT_ITEM_SUBTYPE = 0;
	  ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:magic_extractor", "inventory");
	  ModelLoader.setCustomModelResourceLocation(StartupCommon.itemBlockTank, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
	  
	  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, new RendererExtractor());


  }


  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }

  
}
