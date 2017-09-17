package thewizardmod.wandHandling;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class wandFocusGrowth {
	
	public wandFocusGrowth(World worldIn, EntityLivingBase playerIn){
		BlockPos pos = Minecraft.getMinecraft().objectMouseOver.getBlockPos();

		

		  if(!worldIn.isRemote){
				  EntityPlayer player = Minecraft.getMinecraft().thePlayer;
				  worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0.5D, 0);
				  ItemDye.applyBonemeal(new ItemStack(Items.DYE, 15, 0), worldIn, pos, player);
			  
		  }
	}
}




