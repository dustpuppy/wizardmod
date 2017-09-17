package thewizardmod.Tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;


public class CherryTree extends WorldGenAbstractTree {

	
	/** The minimum height of a generated tree. */
	private final int minTreeHeight;

	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;

	/** The metadata value of the leaves to use in tree generation. */
	private final int metaLeaves;

	public CherryTree() {
		super(false);
		this.minTreeHeight = 4;
		this.metaWood = 0;
		this.metaLeaves = 0;
	}

	public boolean generate(World par1World, Random par2Random, BlockPos pos) {
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();
		int heightOfTree = par2Random.nextInt(3) + this.minTreeHeight;
		boolean var7 = true;

		if (posY >= 1 && posY + heightOfTree + 1 <= 256) {
			int var8;
			byte var9;
			int var11;
			int var12;

			for (var8 = posY; var8 <= posY + 1 + heightOfTree; ++var8) {
				var9 = 1;

				if (var8 == posY) {
					var9 = 0;
				}

				if (var8 >= posY + 1 + heightOfTree - 2) {
					var9 = 2;
				}

				for (int var10 = posX - var9; var10 <= posX + var9 && var7; ++var10) {
					for (var11 = posZ - var9; var11 <= posZ + var9 && var7; ++var11) {
						if (var8 >= 0 && var8 < 256) {
							Block var12s = par1World.getBlockState(new BlockPos(var10, var8, var11)).getBlock();
							var12 = Block.getIdFromBlock(var12s);

							if (var12 != 0 && var12s != Blocks.VINE && var12s != Blocks.GRASS
									&& var12s != Blocks.GRASS && var12s != StartupCommon.blockCherryLog) {
								var7 = false;
							}
						} else {
							var7 = false;
						}
					}
				}
			}

			if (!var7) {
				return false;
			} else {
				Block var8s = par1World.getBlockState(new BlockPos(posX, posY - 1, posZ)).getBlock();
				var8 = Block.getIdFromBlock(var8s);

				if ((var8s == Blocks.GRASS || var8s == Blocks.GRASS) && posY < 256 - heightOfTree - 1) {
					par1World.setBlockState(new BlockPos(posX, posY - 1, posZ), Blocks.GRASS.getDefaultState(), 3);
					var9 = 3;
					byte var18 = 0;
					int var13;
					int var14;
					int var15;

					for (var11 = posY - var9 + heightOfTree; var11 <= posY + heightOfTree; ++var11) {
						var12 = var11 - (posY + heightOfTree);
						var13 = var18 + 1 - var12 / 2;

						for (var14 = posX - var13; var14 <= posX + var13; ++var14) {
							var15 = var14 - posX;

							for (int var16 = posZ - var13; var16 <= posZ + var13; ++var16) {
								int var17 = var16 - posZ;

								if ((Math.abs(var15) != var13 || Math.abs(var17) != var13 || par2Random.nextInt(2) != 0 && var12 != 0)) {
									par1World.setBlockState(new BlockPos(var14, var11, var16), StartupCommon.blockCherryLeaves.getDefaultState(), 3);
								}
							}
						}
					}

					for (var11 = 0; var11 < heightOfTree; ++var11) {
						Block var12s = par1World.getBlockState(new BlockPos(posX, posY + var11, posZ)).getBlock();
						var12 = Block.getIdFromBlock(var12s);

						if (var12 == 0 || var12s == StartupCommon.blockCherryLeaves) {
//							par1World.setBlockState(new BlockPos(posX, posY + var11, posZ), StartupCommon.blockCherryLog.getDefaultState(), 3);
							par1World.setBlockState(new BlockPos(posX, posY + var11, posZ), StartupCommon.blockCherryLog.getStateFromMeta(0), 3);

						}
					}


					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	protected boolean canGrowInto(Block blockType) {
		return true;
	}

	public void generateSaplings(World worldIn, Random random, BlockPos pos) {
	}

	/**
	 * sets dirt at a specific location if it isn't already dirt
	 */
	protected void setDirtAt(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).getBlock() != Blocks.DIRT) {
			this.setBlockAndNotifyAdequately(worldIn, pos, Blocks.DIRT.getDefaultState());
		}
	}

	public boolean isReplaceable(World world, BlockPos pos) {
		net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos)
				|| state.getBlock().isWood(world, pos) || canGrowInto(state.getBlock());
	}


}
