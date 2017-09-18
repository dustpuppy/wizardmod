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

public class Bone extends Item {
	
	private boolean setInventoryPos;
	
	public Bone() 
	{
		final int MAXIMUM_NUMBER_IN_STACK = 1;
		this.setMaxStackSize(MAXIMUM_NUMBER_IN_STACK);
		this.setCreativeTab(CreativeTabs.FOOD);
	}

	public EntityMiniZombie littleZombie;

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(littleZombie != null)
		{
			if(setInventoryPos)
			{
				if(littleZombie.inventoryPos != null)
				{
					System.out.println("setting source position");
					littleZombie.setSourcePos(pos);
					setInventoryPos = false;
				}
				else if(littleZombie.inventoryPos == null)
				{
					System.out.println("setting inventory position");
					littleZombie.setInventoryPos(pos);
					setInventoryPos = false;
				}
			}
			else
			{
				littleZombie.moveZombieTo(pos);
			}
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing,	hitX, hitY, hitZ);

	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		littleZombie = (EntityMiniZombie) entity;

		setInventoryPos = false;
		
		if(!player.isSneaking())
		{
			setInventoryPos = true;
		}
		
		return true;
	}

}
