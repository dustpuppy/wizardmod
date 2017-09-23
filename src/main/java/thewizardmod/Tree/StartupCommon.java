package thewizardmod.Tree;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{

	public static BlockLog blockCherryLog;
	  public static ItemBlock itemCherryLog;

	  public static CherryLeaves blockCherryLeaves;
	  public static ItemBlock itemCherryLeaves;

	  public static CherrySapling blockCherrySapling;
	  public static ItemBlock itemCherrySapling;



	  public static void preInitCommon()
	  {
		  blockCherryLog = (BlockLog)(new BlockLog().setUnlocalizedName("twm_log_cherry"));
		  blockCherryLog.setRegistryName("cherry_log");
		  GameRegistry.register(blockCherryLog);

		  itemCherryLog = new ItemBlock(blockCherryLog);
		  itemCherryLog.setRegistryName(blockCherryLog.getRegistryName());
		  GameRegistry.register(itemCherryLog);

	  
		  blockCherryLeaves = (CherryLeaves)(new CherryLeaves().setUnlocalizedName("twm_leaves_cherry"));
		  blockCherryLeaves.setRegistryName("cherry_leaves");
		  GameRegistry.register(blockCherryLeaves);

		  itemCherryLeaves = new ItemBlock(blockCherryLeaves);
		  itemCherryLeaves.setRegistryName(blockCherryLeaves.getRegistryName());
		  GameRegistry.register(itemCherryLeaves);

	  
		  blockCherrySapling = (CherrySapling)(new CherrySapling().setUnlocalizedName("twm_cherry_sapling"));
		  blockCherrySapling.setRegistryName("cherry_sapling");
		  GameRegistry.register(blockCherrySapling);

		  itemCherrySapling = new ItemBlock(blockCherrySapling);
		  itemCherrySapling.setRegistryName(blockCherrySapling.getRegistryName());
		  GameRegistry.register(itemCherrySapling);
		  
		  
	  }

	  
  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
