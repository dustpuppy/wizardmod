package thewizardmod.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidPoison extends BlockFluidClassic{

	public BlockFluidPoison(Fluid fluid) {
		super(fluid, Material.WATER);
	}

	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1));
			}
			else if(entity instanceof EntityLiving) 
			{
				((EntityLiving)entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1));
			}
	}
}
