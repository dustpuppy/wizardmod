package thewizardmod.mirror;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import thewizardmod.Util.RayTrace;

public class ItemMirror extends Item
{
	
  public ItemMirror()
  {
    final int MAXIMUM_NUMBER_IN_STACK = 1;
    this.setMaxStackSize(MAXIMUM_NUMBER_IN_STACK);

    this.setCreativeTab(CreativeTabs.MISC);   // the item will appear on the Miscellaneous tab in creative
  }

  @Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	    
		RayTraceResult p = RayTrace.rayTraceBlocksAndEntities(worldIn, 5, playerIn);

		BlockPos newpos;
		if(p != null)
		{
		  newpos = p.getBlockPos().offset(facing);
		
		  if(worldIn.getBlockState(p.getBlockPos()).getBlock() != StartupCommon.mirror)
		  {
			  if(facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH || facing == EnumFacing.WEST || facing == EnumFacing.EAST)
			  {
				  worldIn.setBlockState(newpos, StartupCommon.mirror.getDefaultState().withProperty(Mirror.PROPERTYFACING, facing.getOpposite()));
				  NBTTagCompound nbtTagCompound = stack.getTagCompound();
				  if (nbtTagCompound != null && nbtTagCompound.hasKey("Bound") && nbtTagCompound.getBoolean("Bound") == true ) 
				  {
					  TileEntity tileEntity = worldIn.getTileEntity(newpos);
					  if (tileEntity instanceof TileEntityBlockMirror) 
					  {
						  TileEntityBlockMirror te = (TileEntityBlockMirror) tileEntity;
						  double x = nbtTagCompound.getDouble("X");
						  double y = nbtTagCompound.getDouble("Y");
						  double z = nbtTagCompound.getDouble("Z");
						  te.setPosition(x, y, z);
						  
						  int face = MathHelper.floor_double((double) ((playerIn.rotationYaw * 4F) / 360F) + 0.5D) & 3;
						  te.setFacing(face);


						  double oldX = nbtTagCompound.getDouble("oldTeX");
						  double oldY = nbtTagCompound.getDouble("oldTeY");
						  double oldZ = nbtTagCompound.getDouble("oldTeZ");
						  BlockPos oldTePos = new BlockPos(oldX, oldY, oldZ);
						  TileEntity tileEntityOld = worldIn.getTileEntity(oldTePos);
						  if (tileEntity instanceof TileEntityBlockMirror) 
						  {
							  TileEntityBlockMirror te2 = (TileEntityBlockMirror) tileEntityOld;
							  BlockPos targetPos = newpos.offset(facing);
							  te2.setPosition(targetPos.getX(), targetPos.getY(), targetPos.getZ());
						  }						  
					  }
					  
					  stack.stackSize--;
				  }
			  }
		  }
		  else
		  {
		      NBTTagCompound nbt;
		      if (stack.hasTagCompound())
		      {
		          nbt = stack.getTagCompound();
		      }
		      else
		      {
		          nbt = new NBTTagCompound();
		      }
		      BlockPos targetPos = newpos.offset(facing);
		      nbt.setBoolean("Bound", true);
		      nbt.setDouble("X", targetPos.getX());
		      nbt.setDouble("Y", targetPos.getY());
		      nbt.setDouble("Z", targetPos.getZ());
		      nbt.setDouble("oldTeX", p.getBlockPos().getX());
		      nbt.setDouble("oldTeY", p.getBlockPos().getY());
		      nbt.setDouble("oldTeZ", p.getBlockPos().getZ());
		      stack.setTagCompound(nbt);
		      if (worldIn.isRemote) {  // only on the client side, else you will get two messages..
		          playerIn.addChatComponentMessage(new TextComponentString("Mirrors bound together!"));
		        }
		  }
		}
	  return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
