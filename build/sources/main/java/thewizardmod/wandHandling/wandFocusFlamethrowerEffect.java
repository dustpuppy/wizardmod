package thewizardmod.wandHandling;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class wandFocusFlamethrowerEffect {

	public wandFocusFlamethrowerEffect(World worldIn, EntityLivingBase playerIn, int distance_power){
		Vec3d lookVector = playerIn.getLookVec();
		  double x = lookVector.xCoord / 2;
		  double y = lookVector.yCoord / 2;
		  double z = lookVector.zCoord / 2;
		  Random random = new Random();
		  
		  for(int i = 0; i < distance_power; i++){
			  double xCoord = playerIn.posX + x * i;// + random.nextDouble();
			  double yCoord = playerIn.posY + y * i + 1.0F ;// + random.nextDouble();
			  double zCoord = playerIn.posZ + z * i;// + random.nextDouble();
			  
			  if(worldIn.isRemote){
				  worldIn.spawnParticle(EnumParticleTypes.FLAME, xCoord, yCoord, zCoord , x * 10, y * 10, z * 10);
			  } else {
				  if(worldIn.isBlockFullCube(new BlockPos(xCoord, yCoord, zCoord))){
					  worldIn.setBlockState(new BlockPos(xCoord, yCoord + 1, zCoord), Blocks.FIRE.getDefaultState());
				  }
				  
				  for(Object obj: worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 1, yCoord + 1, zCoord + 1))){
					  if(obj instanceof EntityLivingBase){
						  EntityLivingBase entity = (EntityLivingBase) obj;
						  entity.setFire(2);
					  }
			  		}
			  }
		  }
	}

}
