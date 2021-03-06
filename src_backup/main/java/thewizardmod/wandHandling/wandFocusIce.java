package thewizardmod.wandHandling;

import thewizardmod.Util.RayTrace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class wandFocusIce {
	
	public wandFocusIce(World world, EntityLivingBase playerIn, int distance){
		Vec3d vector = playerIn.getLookVec();
		Vec3d origin = (new Vec3d(playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ));
		
		RayTraceResult p = RayTrace.rayTraceBlocksAndEntities(world, distance, playerIn);
		
		if(p != null)
		{
		  BlockPos coord = p.getBlockPos();
		  int targetX=coord.getX(),targetY=coord.getY(),targetZ=coord.getZ();

			int blocksChanged = 0;
			int rSquared = 9;
			for(int dy = 2; dy >= -2; dy--){
				int y = targetY + dy;
				for(int dx = -2; dx <= 2; dx++){
					int x = targetX + dx;
					for(int dz = -2; dz <= 2; dz++){
						int z = targetZ + dz;
						BlockPos pos = new BlockPos(x,y,z);
						// spheritize
						if((dy * dy + dx * dx + dz * dz) <= rSquared){
							// set to ice
							blocksChanged += freezeBlock(world,pos);
						}
					}
				}
			}
		}
		}
		
		protected int freezeBlock(World w, BlockPos coord){
			IBlockState bs = w.getBlockState(coord);
			Block target = bs.getBlock();
			if(target == Blocks.WATER || target == Blocks.FLOWING_WATER){
				w.setBlockState(coord, Blocks.ICE.getDefaultState());
				return 1;
			} else if(target == Blocks.LAVA || target == Blocks.FLOWING_LAVA){
				w.setBlockState(coord, Blocks.COBBLESTONE.getDefaultState());
				return 1;
			}else if(target == Blocks.SNOW_LAYER) {
				if(((Integer)bs.getValue(BlockSnow.LAYERS)) < 8){
					w.setBlockState(coord, Blocks.SNOW_LAYER.getDefaultState()
							.withProperty(BlockSnow.LAYERS,(((Integer)bs.getValue(BlockSnow.LAYERS)) + 1)));
				} else {
					w.setBlockState(coord, Blocks.SNOW.getDefaultState());
				}
			}else if(target.isFullCube(bs) && w.isAirBlock(coord.up())){
				w.setBlockState(coord.up(), Blocks.SNOW_LAYER.getDefaultState());
				return 1;
			}
			return 0;
		}
}
