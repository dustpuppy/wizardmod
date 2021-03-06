package thewizardmod.World;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SetSpawner {

	public static void set(World world, int x, int y, int z, String mob)
	{
		BlockPos blockpos = new BlockPos(x, y, z);
        world.setBlockState(blockpos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
        TileEntity tileentity = world.getTileEntity(blockpos);

        if (tileentity instanceof TileEntityMobSpawner)
        {
            ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic().setEntityName(mob);
        }

	}
}
