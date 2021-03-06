package thewizardmod.magicInjector;

import thewizardmod.TheWizardMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
	public static Block blockInventoryAdvanced;  // this holds the unique instance of your block
  public static ItemBlock itemBlockInventoryAdvanced; // this holds the unique instance of the ItemBlock corresponding to your block

	public static void preInitCommon()
	{
		blockInventoryAdvanced = new BlockMagicInjector().setUnlocalizedName("twm_magicInjector");
	    blockInventoryAdvanced.setRegistryName("magicInjector");
			GameRegistry.register(blockInventoryAdvanced);

	    // We also need to create and register an ItemBlock for this block otherwise it won't appear in the inventory
	    itemBlockInventoryAdvanced = new ItemBlock(blockInventoryAdvanced);
	    itemBlockInventoryAdvanced.setRegistryName(blockInventoryAdvanced.getRegistryName());
	    GameRegistry.register(itemBlockInventoryAdvanced);

		GameRegistry.registerTileEntity(TileEntityMagicInjector.class, "magicInjector_tile_entity");
	}

	public static void initCommon()
	{

	}

	public static void postInitCommon()
	{
	}
}
