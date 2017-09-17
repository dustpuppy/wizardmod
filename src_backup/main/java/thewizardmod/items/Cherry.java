package thewizardmod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thewizardmod.entity.EntityAIMiniZombieMoveTo;
import thewizardmod.entity.EntityMiniZombie;

public class Cherry extends Item {
	
	public Cherry() 
	{
		final int MAXIMUM_NUMBER_IN_STACK = 1;
		this.setMaxStackSize(MAXIMUM_NUMBER_IN_STACK);
		this.setCreativeTab(CreativeTabs.FOOD);
	}

	public EntityMiniZombie littleZombie;

	public void clearTasks()
	{
		for (EntityAITaskEntry t : littleZombie.targetTasks.taskEntries) 
		{
			littleZombie.targetTasks.removeTask(t.action);
		}
		
	}
	
	private void moveZombieTo(BlockPos pos) 
	{
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();
/*
		// Remove the task first
		EntityAIMiniZombieMoveTo moveToTask = null;
		for (EntityAITaskEntry t : littleZombie.targetTasks.taskEntries) 
		{
			if (t.action instanceof EntityAIMiniZombieMoveTo) 
			{
				moveToTask = (EntityAIMiniZombieMoveTo) t.action;
				break;
			}
		}
		if (moveToTask != null) 
		{
			littleZombie.targetTasks.removeTask(moveToTask);
		}
*/
		// add task
		littleZombie.targetTasks.addTask(0, new EntityAIMiniZombieMoveTo(littleZombie, 1.0D, 10, posX, posY, posZ));
		System.out.println(littleZombie.getEntityData());
//		System.out.println(littleZombie);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		clearTasks();
		moveZombieTo(pos);
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing,	hitX, hitY, hitZ);

	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		littleZombie = (EntityMiniZombie) entity;

		return true;
	}

}
