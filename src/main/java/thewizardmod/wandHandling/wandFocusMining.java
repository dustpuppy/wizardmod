package thewizardmod.wandHandling;

import thewizardmod.Util.RayTrace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class wandFocusMining {
	
	private final ItemStack fauxPick = new ItemStack(Items.DIAMOND_PICKAXE);
	
	public wandFocusMining(World world, EntityLivingBase playerIn){
		Vec3d vector = playerIn.getLookVec();
		Vec3d origin = (new Vec3d(playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ));
		
		RayTraceResult p = RayTrace.rayTraceBlocksAndEntities(world, 5, playerIn);
		
		if(p != null)
		{
		  BlockPos pos = p.getBlockPos();

			IBlockState targetBlock = world.getBlockState(pos);
			if(targetBlock.getBlock() != Blocks.BEDROCK){
			
				if(fauxPick.canHarvestBlock(targetBlock) || targetBlock.getBlockHardness(world,pos) < 1.0F){
					// mine it
					int fortuneLevel = 0;
				//	if(!world.isRemote){ }
					world.setBlockToAir(pos);
					targetBlock.getBlock().dropBlockAsItemWithChance(world, pos, targetBlock, 1F, fortuneLevel);
					/*
					for(int x = 0; x < 3 ; x++)
					{
						for(int z = 0; z < 3; z++)	
						{
							BlockPos newpos = new BlockPos(pos.getX() + x - 1, pos.getY(), pos.getZ() + z - 1);
							world.setBlockToAir(newpos);
							targetBlock.getBlock().dropBlockAsItemWithChance(world, newpos, targetBlock, 1F, fortuneLevel);
						}
					}
					*/
				}
			}
		}
	}
	
}
