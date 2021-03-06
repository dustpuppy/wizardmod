package thewizardmod.wandHandling;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class wandFocusWeb {
	
	private static int MAX_RANGE = 0;
	
	public wandFocusWeb(World worldIn, EntityLivingBase playerIn, int DISTANCE_POWER){
		  

			Vec3d vector = playerIn.getLookVec();
			Vec3d origin = (new Vec3d(playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ)).add(vector);
			
			MAX_RANGE = DISTANCE_POWER;
			boolean success = placeSpiderWeb(worldIn, origin, vector, MAX_RANGE);
	}

	private boolean placeSpiderWeb(World w, Vec3d start, Vec3d velocity, int rangeLimit) {
		BlockPos block = new BlockPos(start);
		if(w.isAirBlock(block)){
			Vec3d pos = start;
			for(int i = 0; i < rangeLimit; i++){
				Vec3d next = pos.add(velocity);
				BlockPos nextBlock = new BlockPos(next);
				if(w.isAirBlock(nextBlock)){
					// keep moving
					w.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, pos.xCoord, pos.yCoord, pos.zCoord, 
							(w.rand.nextFloat() - 0.5f) * 0.2f, (w.rand.nextFloat() - 0.5f) * 0.2f, (w.rand.nextFloat() - 0.5f) * 0.2f,
							new int[0]);
					pos = next;
					block = nextBlock;
					if(pos.yCoord < 0 ){
						pos = new Vec3d(pos.xCoord, 0, pos.yCoord);
						break;
					}
					if(pos.yCoord > 255){
						pos = new Vec3d(pos.xCoord, 255, pos.yCoord);
						break;
					}
				} else {
					//place mage light
					break;
				}
			}
			if(!w.isRemote){
				w.setBlockState(block, Blocks.WEB.getDefaultState());
				Random r = w.rand;
				for(int dx = -2; dx <= 2; dx++){
					for(int dy = -2; dy <= 2; dy++){
						for(int dz = -2; dz <= 2; dz++){
							BlockPos p = block.add(dx,dy,dz);
							if(w.isAirBlock(p) && r.nextInt(5) == 0) {
								w.setBlockState(p, Blocks.WEB.getDefaultState());
							}
						}
					}
				}
			}
			return true;
		}
		return false;
	}
}
