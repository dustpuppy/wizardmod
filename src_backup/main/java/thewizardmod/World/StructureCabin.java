package thewizardmod.World;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.collect.Lists;

public class StructureCabin
{
	/** BLUEPRINTS **/
	// Format: int[][] { {distanceRight, distanceUp, distanceBack} } 
	private final static int[][] logsPos = new int[][] 
		{
			// corners (first layer)
			{0,0,0},{0,1,0},	// front-left
			{4,0,0},{4,1,0},	// front-right
			{4,0,4},{4,1,4},	// back-right
			{0,0,4},{0,1,4},	// back-left
			// upper trim (first part of roof)
			{0,2,0},{1,2,0},{2,2,0},{3,2,0},{4,2,0},	// front
			{0,2,4},{1,2,4},{2,2,4},{3,2,4},{4,2,4},	// back
			{0,2,1},{0,2,2},{0,2,3},	// left
			{4,2,1},{4,2,2},{4,2,3},	// right
			// roof
			{1,3,1},{2,3,1},{3,3,1},
			{1,3,2},{2,3,2},{3,3,2},
			{1,3,3},{2,3,3},{3,3,3}
		};
	private final static int[][] planksPos = new int[][]
		{
			// floor
			{0,-1,0},{1,-1,0},{2,-1,0},{3,-1,0},{4,-1,0},
			{0,-1,1},{1,-1,1},{2,-1,1},{3,-1,1},{4,-1,1},
			{0,-1,2},{1,-1,2},{2,-1,2},{3,-1,2},{4,-1,2},
			{0,-1,3},{1,-1,3},{2,-1,3},{3,-1,3},{4,-1,3},
			{0,-1,4},{1,-1,4},{2,-1,4},{3,-1,4},{4,-1,4},
			// walls
			//// front
			{1,0,0},{2,0,0},{3,0,0},	// I want a door here, but I place
			{1,1,0},{2,1,0},{3,1,0},	// planks anyway for consistency
			//// right
			{4,0,1},{4,0,2},{4,0,3},
			{4,1,1},{4,1,2},{4,1,3},
			//// back
			{1,0,4},{2,0,4},{3,0,4},
			{1,1,4},{2,1,4},{3,1,4},
			//// left
			{0,0,1},{0,0,2},{0,0,3},
			{0,1,1},{0,1,2},{0,1,3}
		};
	private final static int[] doorBottomPos = new int[] {2,0,0};
	private final static int[] doorTopPos = new int[] {2,1,0};
	private final static int[] chestPos = new int[] {1,0,3};
	private final static int[] workbenchPos = new int[] {2,0,3};
	private final static int[] furnacePos = new int[] {3,0,3};
	private final static int[] torchPos = new int[] {2,0,2};
	

	public static boolean generate(World worldIn, Random rand, int x, int y, int z) 
	{
			BlockPos corner = new BlockPos(x, y, z);
			// figure out each IBlockState we will use
			IBlockState spruceLogs = Blocks.LOG.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata());
			IBlockState sprucePlank = Blocks.PLANKS.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata());
			IBlockState doorLower = Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER);
			IBlockState doorUpper = Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER);
			IBlockState craftingTable = Blocks.CRAFTING_TABLE.getDefaultState();
			IBlockState furnace = Blocks.FURNACE.getDefaultState();
			IBlockState chest = Blocks.CHEST.getDefaultState();
			IBlockState torch = Blocks.TORCH.getDefaultState();

			// build the layers using the arrays
			buildLayer(worldIn, corner, logsPos, spruceLogs);
			buildLayer(worldIn, corner, planksPos, sprucePlank);

			// place the other features LAST
			placeBlock(worldIn, corner, doorBottomPos, doorLower);
			placeBlock(worldIn, corner, doorTopPos, doorUpper);
			placeBlock(worldIn, corner, workbenchPos, craftingTable);
			placeBlock(worldIn, corner, furnacePos, furnace);
			placeBlock(worldIn, corner, torchPos, torch);

			// I saved the chest for last
			// here's where we use the WeightedRandomChestContent[]
			placeBlock(worldIn, corner, chestPos, chest); 
			// here we have to do some back-math...
			// we need to know the actual location of the TileEntityChest,
			// not just its offsets from the corner
			BlockPos actualPos = corner.add(chestPos[0], chestPos[1], chestPos[2]);

			// debug:
			System.out.println("Built a cabin starting at " + corner + "!");
			return true;
	}

	// use an int[][] to place a lot of one block at once
	private static void buildLayer(World world, BlockPos frontLeftCorner, int[][] blockPositions, IBlockState toPlace)
	{
		// iterate through the entire int[][]
		for(int[] coord : blockPositions)
		{
			placeBlock(world, frontLeftCorner, coord[0], coord[1], coord[2], toPlace);
		}
	}
	
	/** Helper Method **/
	private static void placeBlock(World world, BlockPos frontLeftCorner, int[] offsets, IBlockState toPlace)
	{
		placeBlock(world, frontLeftCorner, offsets[0], offsets[1], offsets[2], toPlace);
	}

	/** Places a block using corner position and offsets **/
	private static void placeBlock(World world, BlockPos frontLeftCorner, int offsetX, int offsetY, int offsetZ, IBlockState toPlace)
	{	
		// figure out where that block is relative to the corner
		BlockPos placePos = frontLeftCorner.add(offsetX, offsetY, offsetZ);
		world.setBlockState(placePos, toPlace, 2);
	}

	
}