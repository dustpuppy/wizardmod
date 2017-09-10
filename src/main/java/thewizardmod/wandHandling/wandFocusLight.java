package thewizardmod.wandHandling;

import com.mojang.realmsclient.dto.RealmsServer.WorldType;

import thewizardmod.lightBlock.LightBlock;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class wandFocusLight {
	  static boolean spawn = false;

	  static double oldX;
	  static double oldY;
	  static double oldZ;
		  
	  public wandFocusLight(World worldIn, EntityLivingBase playerIn, int distance_power){
        if (!worldIn.isRemote) 
        { 
        	Vec3d lookVector = playerIn.getLookVec();
    		
  		  	double x = lookVector.xCoord;
  		  	double y = lookVector.yCoord;
  		  	double z = lookVector.zCoord;

  		  	for(int i = 0; i < distance_power; i++){
  			  double xCoord = playerIn.posX + x * i;// + random.nextDouble();
  			  double yCoord = playerIn.posY + y * i + 1.5F;// + random.nextDouble();
  			  double zCoord = playerIn.posZ + z * i;// + random.nextDouble();
  			  
  			  
				  if(worldIn.isBlockFullCube(new BlockPos(xCoord, yCoord, zCoord))){
	  			         Block blk = thewizardmod.lightBlock.StartupCommon.lightBlock;
	  			         BlockPos pos = new BlockPos(xCoord, yCoord, zCoord);
	  			         
	  			         IBlockState state = blk.getDefaultState();
	  			         
	  			         worldIn.setBlockState(new BlockPos(oldX, oldY, oldZ), state);
	  			         spawn = true;
	  			         break;
				  }
				  
			  oldX = xCoord;
			  oldY = yCoord;
			  oldZ = zCoord;
  		  	}
		 }
        /*
         else if(spawn){
			 for(int i =0; i < 100; i++)
			  worldIn.spawnParticle(EnumParticleTypes.FLAME, oldX, oldY + 0.2F, oldZ , 0, 0, 0);
        } 
        */
	}
}
