package thewizardmod.wandHandling;

import thewizardmod.Util.RayTrace;
import net.minecraft.block.BlockVine;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class wandFocusVines {
	
	public wandFocusVines(World worldIn, EntityLivingBase playerIn){
		Vec3d vector = playerIn.getLookVec();
		Vec3d origin = (new Vec3d(playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ));
		
		RayTraceResult p = RayTrace.rayTraceBlocksAndEntities(worldIn, 5, playerIn);

		if(p != null)
		{
		
		  BlockPos pos = p.getBlockPos();
		  
		  EnumFacing blockFace = p.sideHit;
		  
//		  EnumFacing blockFace = Minecraft.getMinecraft().objectMouseOver.sideHit;
		  
			if(blockFace != EnumFacing.UP || blockFace != EnumFacing.DOWN){
				growVines(worldIn, pos.offset(blockFace));
			}
		}
	}

	private int growVines( World world, BlockPos coord) {
		int numVinesAdded = 0;
		
		BlockPos p = coord;
		// first, go down
		while(p.getY() > 0 && world.isAirBlock(p)){
			int n = placeVinesAt(world,p);
			if(n == 0)break;
			numVinesAdded += n;
			p = p.down();
		}
		
		p = coord.up();
		// then go up
		while(p.getY() > 0 && world.isAirBlock(p)){
			int n = placeVinesAt(world,p);
			if(n == 0)break;
			numVinesAdded += n;
			p = p.up();
		}
		
		
		return numVinesAdded ;
	}

	private int placeVinesAt(World world,BlockPos p) {
		boolean n=false,e=false,s=false,w=false,canDo=false;
		if(world.getBlockState(p.north()).getMaterial().blocksMovement()){
			n = true;
			canDo = true;
		}
		if(world.getBlockState(p.east()).getMaterial().blocksMovement()){
			e = true;
			canDo = true;
		}
		if(world.getBlockState(p.south()).getMaterial().blocksMovement()){
			s = true;
			canDo = true;
		}
		if(world.getBlockState(p.west()).getMaterial().blocksMovement()){
			w = true;
			canDo = true;
		}
		if(canDo){
			world.setBlockState(p, Blocks.VINE.getDefaultState()
					.withProperty(BlockVine.UP, false)
					.withProperty(BlockVine.NORTH, n)
					.withProperty(BlockVine.EAST, e)
					.withProperty(BlockVine.SOUTH, s)
					.withProperty(BlockVine.WEST, w));
			return 1;
		}else{
			return 0;
		}
		
		
		
	}
}

