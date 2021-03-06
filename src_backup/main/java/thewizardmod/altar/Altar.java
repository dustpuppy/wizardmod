package thewizardmod.altar;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thewizardmod.pedestral.TileEntityPedestral;
import thewizardmod.runeSlab.StartupCommon;
import thewizardmod.runeSlab.TileEntityRuneSlab;

public class Altar {

	public static Item altarRecipeResult[] = {
		thewizardmod.Wands.StartupCommon.blazeWand,
		Item.getItemFromBlock(thewizardmod.chest.StartupCommon.blockInventoryBasic),
		thewizardmod.runes.StartupCommon.fehu,
		thewizardmod.runes.StartupCommon.uruz,
		thewizardmod.runes.StartupCommon.thurisaz,
		thewizardmod.runes.StartupCommon.ansuz,
		thewizardmod.runes.StartupCommon.raido,
		thewizardmod.runes.StartupCommon.kaunan,
		thewizardmod.runes.StartupCommon.gibo,
		thewizardmod.runes.StartupCommon.wunjo,
		thewizardmod.runes.StartupCommon.haglaz,
		thewizardmod.runes.StartupCommon.naudiz,
		thewizardmod.runes.StartupCommon.eisa,
		thewizardmod.runes.StartupCommon.jeran,
		thewizardmod.runes.StartupCommon.ihwaz,
		thewizardmod.runes.StartupCommon.pertho,
		thewizardmod.runes.StartupCommon.algiz,
		thewizardmod.runes.StartupCommon.sowulo,
		thewizardmod.runes.StartupCommon.teiwaz,
		thewizardmod.runes.StartupCommon.berko,
		thewizardmod.runes.StartupCommon.ehwaz,
		thewizardmod.runes.StartupCommon.mann,
		thewizardmod.runes.StartupCommon.laguz,
		thewizardmod.runes.StartupCommon.ingwaz,
		thewizardmod.runes.StartupCommon.othalan,
		thewizardmod.runes.StartupCommon.dagaz,
		thewizardmod.runes.StartupCommon.blank,
		thewizardmod.mirror.StartupCommon.itemMirror,
		Item.getItemFromBlock(thewizardmod.fluids.StartupCommon.blockTank),
		thewizardmod.items.StartupCommon.bone,
		thewizardmod.items.StartupCommon.zombie,
		};
	
	public static Item altarRecipeInputs[][] = {
		{thewizardmod.items.StartupCommon.magicGem, thewizardmod.items.StartupCommon.shadowDust, Items.BLAZE_ROD, Items.STICK},
		{thewizardmod.items.StartupCommon.magicGem, thewizardmod.items.StartupCommon.shadowDust, Items.CLOCK, Item.getItemFromBlock(Blocks.CHEST)},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.GOLD_INGOT},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.BLAZE_POWDER},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.FIRE_CHARGE},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.BOOK},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Item.getItemFromBlock(thewizardmod.Tree.StartupCommon.blockCherrySapling)},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.SPIDER_EYE},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Item.getItemFromBlock(Blocks.CHEST)},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.BANNER},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.ARROW},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.COAL},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Item.getItemFromBlock(Blocks.ICE)},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, thewizardmod.plants.StartupCommon.wheatSeeds},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, thewizardmod.items.StartupCommon.magicString},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, thewizardmod.runes.StartupCommon.thurisaz},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.SHIELD},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.NETHER_STAR},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, thewizardmod.runes.StartupCommon.fehu},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.APPLE},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, thewizardmod.runes.StartupCommon.ansuz},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.POISONOUS_POTATO},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.ENDER_EYE},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.FEATHER},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.BED},
		{thewizardmod.runes.StartupCommon.blank, thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.FLINT_AND_STEEL},
		{Item.getItemFromBlock(Blocks.STONE), thewizardmod.items.StartupCommon.shadowDust,thewizardmod.items.StartupCommon.magicGem, Items.REDSTONE},
		{thewizardmod.items.StartupCommon.shadowDust, thewizardmod.mirror.StartupCommon.mirrorGlass, Items.ENDER_PEARL, Items.GOLD_INGOT},
		{Items.BUCKET, thewizardmod.items.StartupCommon.shadowDust, Item.getItemFromBlock(Blocks.GLASS), Items.IRON_INGOT},
		{Items.BONE, Items.GOLD_INGOT, thewizardmod.items.StartupCommon.shadowDust, thewizardmod.items.StartupCommon.heart},
		{Items.BONE, thewizardmod.items.StartupCommon.magicGem, thewizardmod.items.StartupCommon.heart, Items.ROTTEN_FLESH}
	};
	
	public static BlockPos pedestralPos[] = {null, null, null, null};
	public static Item pedestralItems[] = {null, null, null, null};
	
	public static Item returnAltarResult(World world, int chance)
	{
		Random random = new Random();
		for(int i = 0; i < altarRecipeInputs.length; i++)
		{
			int itemCount = 0;
			for(int j = 0; j < altarRecipeInputs[i].length; j++)
			{
				for(int k = 0; k < pedestralItems.length; k++)
				{
					if(altarRecipeInputs[i][j] == pedestralItems[k])
					{
						itemCount += 1;
						break;
					}
				}
				if(itemCount == 4)
				{
					for(int k = 0; k < pedestralPos.length; k ++)
					{
						TileEntity tileEntity = world.getTileEntity(pedestralPos[k]);
						if(tileEntity instanceof TileEntityPedestral)
						{
						TileEntityPedestral pedestral = (TileEntityPedestral) tileEntity;
						// depending on the chance we delete the item on the pedestral, or just kick it down
						int ch = random.nextInt(chance);
							if(ch == 0)
							{
								pedestral.removeItem();
							}
							else
							{
								pedestral.deleteItem();
							}
						}
					}
					return altarRecipeResult[i];
				}
			}
		}
		return null;
	}
	
	
	
	public static int runeTypes[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

	public static int checkForRunes(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		int runeCount = 0;
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		// Runes can be 4 blocks around the altar
		double startX = x - 4;
		double endX   = x + 4;
		double startZ = z - 4;
		double endZ   = z + 4;
		
		for (int r = 0; r < 25; r++)
		{
			runeTypes[r] = 0;
		}
		
		for(double i = startX; i <= endX; i++)
		{
			for(double j = startZ; j <= endZ;j++)
			{
				if(world.getBlockState(new BlockPos(i, y, j)).getBlock() == StartupCommon.runeSlab)
				{
					runeCount += 1;
					 TileEntity tileEntity = world.getTileEntity(new BlockPos(i, y, j));
					  if(tileEntity instanceof TileEntityRuneSlab)
					  {
						  TileEntityRuneSlab runeSlab = (TileEntityRuneSlab) tileEntity;
						  runeCount += runeSlab.getMagicChance();
						  for(int r = 0; r < 25; r++)
						  {
							  if(runeSlab.getRuneType() == r)
							  {
								  runeTypes[r] += 1;
							  }
						  }
					  }
				}
			}
		}
		return runeCount;
	}
	
	public static int checkForTorches(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		int torchCount = 0;
		
		// During night time, when the torches are burning, power is bigger ;-) 
		long time = world.getWorldTime();
  		if(time < 23500 && time >12800)
  		{
			
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();
		
			// Torches can be 6 blocks around the altar
			double startX = x - 6;
			double endX   = x + 6;
			double startZ = z - 6;
			double endZ   = z + 6;
		
		
			for(double i = startX; i <= endX; i++)
			{
				for(double j = startZ; j <= endZ;j++)
				{
					if(world.getBlockState(new BlockPos(i, y, j)).getBlock() == thewizardmod.torch.StartupCommon.torch)
					{
						torchCount += 1;
					}
				}
			}
  		}				
		return torchCount;
	}
	
	public static boolean checkForPedestrals(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		int pedestralCount = 0;
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		BlockPos blockpos = new BlockPos(x - 3, y, z);
		if(world.getBlockState(blockpos).getBlock() == thewizardmod.pedestral.StartupCommon.pedestral)
		{
			pedestralItems[0] = null;
			TileEntity tileEntity = world.getTileEntity(blockpos);
			if(tileEntity instanceof TileEntityPedestral)
			{
			  TileEntityPedestral pedestral = (TileEntityPedestral) tileEntity;
			  pedestralItems[0] = pedestral.getItem();
			  pedestralPos[0] = blockpos;
			}
			pedestralCount += 1;
		}
		
		blockpos = new BlockPos(x + 3, y, z);
		if(world.getBlockState(blockpos).getBlock() == thewizardmod.pedestral.StartupCommon.pedestral)
		{
			pedestralItems[1] = null;
			TileEntity tileEntity = world.getTileEntity(blockpos);
			if(tileEntity instanceof TileEntityPedestral)
			{
			  TileEntityPedestral pedestral = (TileEntityPedestral) tileEntity;
			  pedestralItems[1] = pedestral.getItem();
			  pedestralPos[1] = blockpos;
			}
			pedestralCount += 1;
		}
		
		blockpos = new BlockPos(x, y, z - 3);
		if(world.getBlockState(blockpos).getBlock() == thewizardmod.pedestral.StartupCommon.pedestral)
		{
			pedestralItems[2] = null;
			TileEntity tileEntity = world.getTileEntity(blockpos);
			if(tileEntity instanceof TileEntityPedestral)
			{
			  TileEntityPedestral pedestral = (TileEntityPedestral) tileEntity;
			  pedestralItems[2] = pedestral.getItem();
			  pedestralPos[2] = blockpos;
			}
			pedestralCount += 1;
		}
		
		blockpos = new BlockPos(x, y, z + 3);
		if(world.getBlockState(blockpos).getBlock() == thewizardmod.pedestral.StartupCommon.pedestral)
		{
			pedestralItems[3] = null;
			TileEntity tileEntity = world.getTileEntity(blockpos);
			if(tileEntity instanceof TileEntityPedestral)
			{
			  TileEntityPedestral pedestral = (TileEntityPedestral) tileEntity;
			  pedestralItems[3] = pedestral.getItem();
			  pedestralPos[3] = blockpos;
			}
			pedestralCount += 1;
		}
		if(pedestralCount == 4)
		{
			return true;
		}
		return false;
		
	}
	
	public static int checkForChalice(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		int count = 0;
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		
		// Runes can be 4 blocks around the altar
		double startX = x - 4;
		double endX   = x + 4;
		double startZ = z - 4;
		double endZ   = z + 4;
		
		for(double i = startX; i <= endX; i++)
		{
			for(double j = startZ; j <= endZ;j++)
			{
				if(world.getBlockState(new BlockPos(i, y, j)).getBlock() == thewizardmod.fireChalice.StartupCommon.fireChalice)
				{
					count += 1;
				}
			}
		}
		return count;
	}
	

}
