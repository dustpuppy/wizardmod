package thewizardmod.dimensions;

import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MagicFire extends BlockFire
{
	
	public static String name;
	
    protected MagicFire()
    {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(UPPER, Boolean.valueOf(false)));
        this.setTickRandomly(true);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!StartupCommon.portalBlock.trySpawnPortal(worldIn, pos))
        {
            if (!worldIn.getBlockState(pos.down()).isFullyOpaque() && !this.canNeighborCatchFire(worldIn, pos))
            {
                worldIn.setBlockToAir(pos);
            }
            else
            {
                worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
            }
        }
    }
    
    private boolean canNeighborCatchFire(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (this.canCatchFire(worldIn, pos.offset(enumfacing), enumfacing.getOpposite()))
            {
                return true;
            }
        }

        return false;
    }

    public boolean canCatchFire(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return world.getBlockState(pos).getBlock().isFlammable(world, pos, face);
    }
}