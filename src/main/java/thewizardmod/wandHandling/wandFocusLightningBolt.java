package thewizardmod.wandHandling;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class wandFocusLightningBolt {
	
	public wandFocusLightningBolt(World worldIn, EntityLivingBase playerIn, int distance_power){
		Vec3d lookVector = playerIn.getLookVec();
		
		  double x = lookVector.xCoord;
		  double y = lookVector.yCoord;
		  double z = lookVector.zCoord;

		  for(int i = 0; i < distance_power; i++){
			  double xCoord = playerIn.posX + x * i;// + random.nextDouble();
			  double yCoord = playerIn.posY + y * i + 1.0F ;// + random.nextDouble();
			  double zCoord = playerIn.posZ + z * i;// + random.nextDouble();
			  
			  if(!worldIn.isRemote){
				  if(worldIn.isBlockFullCube(new BlockPos(xCoord, yCoord, zCoord))){
					  worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, xCoord, yCoord, zCoord, false));
				  }
				  
				  for(Object obj: worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 1, yCoord + 1, zCoord + 1))){
					  if(obj instanceof EntityLivingBase){
						  worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, xCoord, yCoord, zCoord, false));
					  }
			  		}
			  }
		  }


	}
}
