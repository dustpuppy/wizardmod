package thewizardmod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thewizardmod.entity.EntityMiniZombie;

public class Zombie extends Item {
	
	private boolean setInventoryPos;
	
	public Zombie() 
	{
		final int MAXIMUM_NUMBER_IN_STACK = 1;
		this.setMaxStackSize(MAXIMUM_NUMBER_IN_STACK);
		this.setCreativeTab(CreativeTabs.FOOD);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote)
		{
			EntityMiniZombie entity = new EntityMiniZombie(worldIn);
        	int posX = pos.getX();
        	int posY = pos.getY() + 1;
        	int posZ = pos.getZ();
        	if(worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR)
        	{
        		entity.setLocationAndAngles(posX, posY, posZ, playerIn.rotationYaw, 0.0F);
        		worldIn.spawnEntityInWorld(entity);
       			playerIn.setHeldItem(hand, null);
        	}
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing,	hitX, hitY, hitZ);

	}

}
