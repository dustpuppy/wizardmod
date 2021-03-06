package thewizardmod.World;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thewizardmod.Wands.StartupCommon;

public class StructureDungeon {

	public static void generate(World world, int x, int y, int z)
	{
		generateTower(world, x, y, z);
		generateWalkwayToLibrary(world, x, y, z);
		generateWayUp(world, x, y, z);
		generateLibrary(world, x, y, z);
		
		BlockPos blockpos = new BlockPos(x, y + 1, z);
		world.setBlockState(blockpos, Blocks.BRICK_BLOCK.getDefaultState(), 2);

	}
	
	public static void generateTower(World world, int x, int y, int z)
	{
		// just a full block
		for(int height = 0; height < 6; height++)
		{
			for(int width = 0; width < 10; width++)
			{
				for(int lenght = 0; lenght < 7; lenght++)
				{
					BlockPos blockpos = new BlockPos(x + width, y + 9 + height, z - 5 + lenght);
					world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
				}
			}
		}
		//let's make it hollow
		for(int height = 0; height < 4; height++)
		{
			for(int width = 0; width < 8; width++)
			{
				for(int lenght = 0; lenght < 5; lenght++)
				{
					BlockPos blockpos = new BlockPos(x + 1 + width, y + 10 + height, z - 4 + lenght);
					world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
				}
			}
		}
		//chimney
		for(int height = 0; height < 10; height++)
		{
			for(int width = 0; width < 3; width++)
			{
				BlockPos blockpos = new BlockPos(x + 3, y + 10 + height, z - 6 + width);
				world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
				blockpos = new BlockPos(x + 5, y + 10 + height, z - 6 + width);
				world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
			}
			BlockPos blockpos = new BlockPos(x + 4, y + 10 + height, z - 6);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
			blockpos = new BlockPos(x + 4, y + 10 + height, z - 5);
			world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		}
		for(int height = 0; height < 5; height++)
		{
			BlockPos blockpos = new BlockPos(x + 4, y + 15 + height, z - 4);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		}
		for(int height = 0; height < 2; height++)
		{
			BlockPos blockpos = new BlockPos(x + 3, y + 10 + height, z - 4);
			world.setBlockState(blockpos, Blocks.STONEBRICK.getDefaultState(), 2);
			blockpos = new BlockPos(x + 5, y + 10 + height, z - 4);
			world.setBlockState(blockpos, Blocks.STONEBRICK.getDefaultState(), 2);
		}
		for(int width = 0; width < 3; width++)
		{
			BlockPos blockpos = new BlockPos(x + 3 + width, y + 12, z - 4);
			world.setBlockState(blockpos, Blocks.STONE_BRICK_STAIRS.getDefaultState(), 2);
			blockpos = new BlockPos(x + 3 + width, y + 13, z - 4);
			world.setBlockState(blockpos, Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.HALF, EnumHalf.TOP), 2);
			blockpos = new BlockPos(x + 3 + width, y + 10, z - 3);
			world.setBlockState(blockpos, Blocks.IRON_BARS.getDefaultState(), 2);
		}
		
		// workbench and furnace
		BlockPos blockpos = new BlockPos(x + 7, y + 10, z - 4);
		world.setBlockState(blockpos, Blocks.CRAFTING_TABLE.getDefaultState(), 2);
		blockpos = new BlockPos(x + 8, y + 10, z - 4);
		world.setBlockState(blockpos, Blocks.FURNACE.getDefaultState().withProperty(BlockFurnace.FACING, EnumFacing.SOUTH), 2);
		
		//door
		blockpos = new BlockPos(x + 9, y + 10, z - 2);
		world.setBlockState(blockpos, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.EAST), 2);
		blockpos = new BlockPos(x + 9, y + 11, z - 2);
		world.setBlockState(blockpos, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.EAST), 2);
		
		// windows
		for(int width = 0; width < 3; width++)
		{
			blockpos = new BlockPos(x, y + 11, z - 3 + width);
			world.setBlockState(blockpos, Blocks.GLASS_PANE.getDefaultState(), 2);
		}
		blockpos = new BlockPos(x + 2, y + 11, z + 1);
		world.setBlockState(blockpos, Blocks.GLASS_PANE.getDefaultState(), 2);
		blockpos = new BlockPos(x + 3, y + 11, z + 1);
		world.setBlockState(blockpos, Blocks.GLASS_PANE.getDefaultState(), 2);
		blockpos = new BlockPos(x + 6, y + 11, z + 1);
		world.setBlockState(blockpos, Blocks.GLASS_PANE.getDefaultState(), 2);
		blockpos = new BlockPos(x + 7, y + 11, z + 1);
		world.setBlockState(blockpos, Blocks.GLASS_PANE.getDefaultState(), 2);
	}
	
	public static void generateWayUp(World world, int x, int y, int z)
	{
		//floor
		for(int i = 0; i < 4; i++)
		{
			BlockPos blockpos = new BlockPos(x + 6 - i, y, z - 4);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		}
		
		//walls
		for(int lenght = 0; lenght < 4;lenght++)
		{
			for(int height = 0; height < 3; height++)
			{
				BlockPos blockpos = new BlockPos(x + 6 - lenght, y + 1 + height, z - 3);
				world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
				blockpos = new BlockPos(x + 6 - lenght, y + 1 + height, z - 5);
				world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
			}
		}

		//roof
		BlockPos blockpos = new BlockPos(x + 6, y + 3, z - 4);
		world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		blockpos = new BlockPos(x + 5, y + 3, z - 4);
		world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		
		// close the end
		for(int i = 0; i < 3; i++)
		{
			blockpos = new BlockPos(x + 3, y + 1 + i, z - 4);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		}
		
		// Tunnel up
		for(int height = 0; height < 6; height++)
		{
			for(int width = 0 ; width < 3; width++)
			{
				for(int lenght = 0; lenght < 3; lenght++)
				{
					blockpos = new BlockPos(x + width + 3, y + 4 + height, z - 5 + lenght);
					world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
				}
			}
		}
		// middel bore and ladders
		for(int height = 0; height < 9; height++)
		{
			blockpos = new BlockPos(x + 4, y + 1 + height, z - 4);
			world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
			world.setBlockState(blockpos, Blocks.LADDER.getDefaultState(), 2);
		}
		
		// trapdoor on top
		blockpos = new BlockPos(x + 4, y + 10, z - 4);
		world.setBlockState(blockpos, Blocks.TRAPDOOR.getDefaultState().withProperty(BlockTrapDoor.FACING, EnumFacing.SOUTH), 2);
		
		
}
	
	public static void generateWalkwayToLibrary(World world, int x, int y, int z)
	{
		for(int lenght = 0; lenght < 5; lenght++)
		{
			for(int height = 0; height < 3; height++)
			{
				for(int width = 0; width < 4; width++)
				{
					BlockPos blockpos = new BlockPos(x + 5 + width, y + 1 + height, z - 1 - lenght);
					world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
				}
			}

			// the walls
			for(int height = 0; height < 3; height++)
			{
				BlockPos blockpos = new BlockPos(x + 6, y + 1 + height, z - 1 - lenght);
				world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
				blockpos = new BlockPos(x + 9, y + 1 +height, z - 1 - lenght);
				world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
			}
			
			// floor and roof
			BlockPos blockpos = new BlockPos(x + 7, y, z - 1 - lenght);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
			blockpos = new BlockPos(x + 8, y, z - 1 - lenght);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
			
			blockpos = new BlockPos(x + 7, y + 3, z - 1 - lenght);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
			blockpos = new BlockPos(x + 8, y + 3, z - 1 - lenght);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);

		}
		// close the end
		BlockPos blockpos = new BlockPos(x + 7, y + 1, z - 6);
		world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		blockpos = new BlockPos(x + 8, y + 1, z - 6);
		world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		blockpos = new BlockPos(x + 7, y + 2, z - 6);
		world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		blockpos = new BlockPos(x + 8, y + 2, z - 6);
		world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		
		// the door
		blockpos = new BlockPos(x + 6, y + 1, z - 4);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		blockpos = new BlockPos(x + 6, y + 2, z - 4);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		
		// torch in tunnel
		blockpos = new BlockPos(x + 8, y + 2, z - 5);
		world.setBlockState(blockpos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH), 2);

	}
	
	public static void generateLibrary(World world, int x, int y, int z)
	{
		int height = 4;
		int width = 16;
		int lenght = 16;
		
		IBlockState floor = Blocks.COBBLESTONE.getDefaultState();
		IBlockState wall = Blocks.COBBLESTONE.getDefaultState();
		IBlockState bookshelf = Blocks.BOOKSHELF.getDefaultState();
		
		clearSpace(world, x, y, z, height + 1, width, lenght);
		
		// floor
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < lenght; j++)
			{
				BlockPos blockpos = new BlockPos(x + i, y, z + j);
				world.setBlockState(blockpos, floor, 2);
			}
		}
		
		// walls
		for(int i = 1; i < height + 1; i++)
		{
			for(int j = 0; j < width; j++)
			{
				BlockPos blockpos = new BlockPos(x + j, y + i, z);
				world.setBlockState(blockpos, wall, 2);
				blockpos = new BlockPos(x + j, y + i, z + lenght - 1);
				world.setBlockState(blockpos, wall, 2);
			}
			for(int j = 0; j < lenght; j++)
			{
				BlockPos blockpos = new BlockPos(x, y + i, z + j);
				world.setBlockState(blockpos, wall, 2);
				blockpos = new BlockPos(x + width - 1, y + i, z + j);
				world.setBlockState(blockpos, wall, 2);
			}
		}
		
		// Bookshelf on walls
		for(int i = 1; i < height; i++)
		{
			for(int j = 2; j < width - 2; j++)
			{
				BlockPos blockpos = new BlockPos(x + j, y + i, z + 1);
				world.setBlockState(blockpos, bookshelf, 2);
				blockpos = new BlockPos(x + j, y + i, z + lenght - 2);
				world.setBlockState(blockpos, bookshelf, 2);
			}
			
			for(int j = 2; j < lenght - 2; j++)
			{
				BlockPos blockpos = new BlockPos(x + 1, y + i, z + j);
				world.setBlockState(blockpos, bookshelf, 2);
				blockpos = new BlockPos(x + width - 2, y + i, z + j);
				world.setBlockState(blockpos, bookshelf, 2);
			}
			
		}

		// Bookshelf on inside
		for(int i = 1; i < height; i++)
		{
			for(int j = 5; j < width - 5; j++)
			{
				BlockPos blockpos = new BlockPos(x + j, y + i, z + 5);
				world.setBlockState(blockpos, bookshelf, 2);
				blockpos = new BlockPos(x + j, y + i, z + lenght - 6);
				world.setBlockState(blockpos, bookshelf, 2);
			}
			
			for(int j = 5; j < lenght - 5; j++)
			{
				BlockPos blockpos = new BlockPos(x + 5, y + i, z + j);
				world.setBlockState(blockpos, bookshelf, 2);
				blockpos = new BlockPos(x + width - 5, y + i, z + j);
				world.setBlockState(blockpos, bookshelf, 2);
			}
			
		}

		// Door 
		BlockPos blockpos = new BlockPos(x + 7, y + 1, z);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		blockpos = new BlockPos(x + 8, y + 1, z);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		blockpos = new BlockPos(x + 7, y + 2, z);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		blockpos = new BlockPos(x + 8, y + 2, z);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		
		// path through bookshelf
		blockpos = new BlockPos(x + 7, y + 1, z + 1);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		blockpos = new BlockPos(x + 8, y + 1, z + 1);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		blockpos = new BlockPos(x + 7, y + 2, z + 1);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		blockpos = new BlockPos(x + 8, y + 2, z + 1);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		
		//Door to inner circle
		blockpos = new BlockPos(x + 8, y + 1, z + 10);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
		blockpos = new BlockPos(x + 8, y + 2, z + 10);
		world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);

		setPillar(world, x + 3, y, z + 3, height);
		setPillar(world, x + 12, y, z + 3, height);
		setPillar(world, x + 3, y, z + 12, height);
		setPillar(world, x + 12, y, z + 12, height);
		
		// The chest
		blockpos = new BlockPos(x + 8, y + 1, z + 6);

        IBlockState iblockstate = Blocks.CHEST.getDefaultState();
        world.setBlockState(blockpos, Blocks.CHEST.correctFacing(world, blockpos, iblockstate), 2);
        TileEntity tileentity = world.getTileEntity(blockpos);

    	Random random = new Random();
        if (tileentity instanceof TileEntityChest)
        {
        	int slotCounter = 0;
        	for(int number = 0; number < 10; number++)
        	{
        		int chance = random.nextInt(5);
        		if(chance == 0)
        		{
        			((TileEntityChest)tileentity).setInventorySlotContents(slotCounter, new ItemStack(Items.ENCHANTED_BOOK));
                	slotCounter++;
        		}
        		if(chance == 1)
        		{
        			((TileEntityChest)tileentity).setInventorySlotContents(slotCounter, new ItemStack(Items.BOOK));
                	slotCounter++;
        		}
        		if(chance == 2)
        		{
        			((TileEntityChest)tileentity).setInventorySlotContents(slotCounter, new ItemStack(Items.BONE));
                	slotCounter++;
        		}
        		if(chance == 3)
        		{
        			((TileEntityChest)tileentity).setInventorySlotContents(slotCounter, new ItemStack(Items.DIAMOND));
                	slotCounter++;
        		}
        	}
    		int chance = random.nextInt(20);
    		if(chance == 0)
    		{
    			((TileEntityChest)tileentity).setInventorySlotContents(slotCounter, new ItemStack(StartupCommon.goldenWand));
            	slotCounter++;
    		}
    		chance = random.nextInt(40);
    		if(chance == 0)
    		{
    			((TileEntityChest)tileentity).setInventorySlotContents(slotCounter, new ItemStack(StartupCommon.blazeWand));
            	slotCounter++;
    		}
        }

		// The Zombie Spawner under the chest
        SetSpawner.set(world, x + 8, y, z + 6, "thewizardmod.WeirdZombie");
        
		// roof
		for(int i = 1; i < width - 1; i++)
		{
			for(int j = 1; j < lenght - 1; j++)
			{
				blockpos = new BlockPos(x + i, y + height, z + j);
				world.setBlockState(blockpos, floor, 2);
			}
		}

	}
	
	public static void setPillar(World world, int x, int y, int z, int height)
	{
		// pillars with torches
		for(int i = 1; i < height; i++)
		{
			BlockPos blockpos = new BlockPos(x, y + i, z);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);
		}		
		BlockPos blockpos = new BlockPos(x - 1, y + 3, z);
		world.setBlockState(blockpos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST), 2);
		blockpos = new BlockPos(x + 1, y + 3, z);
		world.setBlockState(blockpos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST), 2);
		blockpos = new BlockPos(x, y + 3, z - 1);
		world.setBlockState(blockpos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 2);
		blockpos = new BlockPos(x, y + 3, z + 1);
		world.setBlockState(blockpos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH), 2);
	}
	
	public static void clearSpace(World world, int x, int y, int z, int height, int width, int lenght)
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				for(int k = 0; k < lenght; k++)
				{
					BlockPos blockpos = new BlockPos(x + j, y + i, z + k);
					world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
				}
			}
		}
	}
}
