package thewizardmod.World;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StructureCircle {

	public static void generate(World world, int x, int y, int z)
	{
		BlockPos blockpos = new BlockPos(x, y + 1, z);
		world.setBlockState(blockpos, Blocks.BRICK_BLOCK.getDefaultState(), 2);

		for(int height = 0; height < 3; height++)
		{
			blockpos = new BlockPos(x - 5, y + 1 + height, z - 5);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);

			blockpos = new BlockPos(x + 5, y + 1 + height, z + 5);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);

			blockpos = new BlockPos(x - 5, y + 1 + height, z + 5);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);

			blockpos = new BlockPos(x + 5, y + 1 + height, z - 5);
			world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 2);

		}
	}
	
}
