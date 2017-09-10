package thewizardmod.wandHandling;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class wandFocusBlank {
	
	public wandFocusBlank(World worldIn, EntityLivingBase playerIn){
		Vec3d lookVector = playerIn.getLookVec();
		
		  double x = lookVector.xCoord;
		  double y = lookVector.yCoord;
		  double z = lookVector.zCoord;

//        System.out.println("Block pos:   " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
//        System.out.println("Player pos : " + (int)playerIn.posX + " " + (int)playerIn.posY + " " + (int)playerIn.posZ);
//        System.out.println("Distances  : " + (int)(playerIn.posX-pos.getX()) + " " + (int)(playerIn.posY-pos.getY()) + " " + (int)(playerIn.posZ-pos.getZ()));
        
//        System.out.println((int)oldX + " " + (int)oldY + " " + (int)oldZ);
	}
}
