package thewizardmod.wandHandling;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class wandFocusTeleport {
	
	public wandFocusTeleport(World world, EntityLivingBase playerEntity, int distance_power){
		
		final int maxRange = distance_power;
		Vec3d origin = (new Vec3d(playerEntity.posX, playerEntity.posY + playerEntity.getEyeHeight(), playerEntity.posZ));
		Vec3d vector = playerEntity.getLookVec();
		Vec3d pos = origin;
		Vec3d next = pos;
		BlockPos coord = new BlockPos(pos);
		
		for(int i = 0; i < maxRange; i++){
			next = pos.add(vector);
			if(next.yCoord < 0 || next.yCoord > 255) break;
			BlockPos nextBlock = new BlockPos(next);
			if(world.isAreaLoaded(coord, 1, true) ){
				if(world.isAirBlock(nextBlock)){
					world.spawnParticle(EnumParticleTypes.PORTAL, 
							pos.xCoord + (world.rand.nextDouble() - 0.5), pos.yCoord + (world.rand.nextDouble() - 0.5), pos.zCoord + (world.rand.nextDouble() - 0.5), 
							(world.rand.nextFloat() - 0.5f) * 0.2f, (world.rand.nextFloat() - 0.5f) * 0.2f, (world.rand.nextFloat() - 0.5f) * 0.2f, 
							new int[0]);
					pos = next;
					coord = nextBlock;
					continue;
				} else {
					if(world.isAirBlock(nextBlock.up())){
						coord = nextBlock.up();
					}
					break;
				}
			} else{
				break;
			}
		}

		playerEntity.setLocationAndAngles(coord.getX()+0.5, coord.getY()+0.25, coord.getZ()+0.5,playerEntity.rotationYaw, playerEntity.rotationPitch);
		if(world.isRemote)playerEntity.setVelocity(0, 0, 0);
		playerEntity.fallDistance = 0;

	}
}
