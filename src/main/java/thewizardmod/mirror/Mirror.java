package thewizardmod.mirror;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import thewizardmod.Jar_undead_heart.TileEntityJar_undead_heart;
import thewizardmod.items.StartupCommon;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Mirror extends Block implements ITileEntityProvider{
	private static final AxisAlignedBB BOUNCING_BOX_SOUTH = new AxisAlignedBB(0.0625 * 0,  0, 0.0625 * 14, 0.0625 * 16,  0.0625 * 16, 0.0625 * 16);
	private static final AxisAlignedBB BOUNCING_BOX_NORTH = new AxisAlignedBB(0.0625 * 0,  0, 0.0625 * 0,  0.0625 * 16,  0.0625 * 16, 0.0625 * 2);
	private static final AxisAlignedBB BOUNCING_BOX_EAST =  new AxisAlignedBB(0.0625 * 14, 0, 0.0625 * 0,  0.0625 * 16,  0.0625 * 16, 0.0625 * 16);
	private static final AxisAlignedBB BOUNCING_BOX_WEST =  new AxisAlignedBB(0.0625 * 0,  0, 0.0625 * 0,  0.0625 * 2,   0.0625 * 16, 0.0625 * 16);
	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	
	public static EnumFacing FACING;
	
	public Mirror() {
		super(Material.GLASS);
		this.setCreativeTab(CreativeTabs.MISC);
		this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTYFACING, EnumFacing.NORTH));

	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}
	
	  @Override
	  public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBlockMirror();
	  }

	  @Override
	  @Deprecated
	  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		  if(state.getValue(PROPERTYFACING) == EnumFacing.NORTH)
		  {
			  return BOUNCING_BOX_NORTH;
		  }
		  if(state.getValue(PROPERTYFACING) == EnumFacing.SOUTH)
		  {
			  return BOUNCING_BOX_SOUTH;
		  }
		  if(state.getValue(PROPERTYFACING) == EnumFacing.EAST)
		  {
			  return BOUNCING_BOX_EAST;
		  }
		  if(state.getValue(PROPERTYFACING) == EnumFacing.WEST)
		  {
			  return BOUNCING_BOX_WEST;
		  }
		  return null;
	  }
		@Override
		public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
		{
			  // find the quadrant the player is facing
			EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
			
			FACING = enumfacing;

			return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing);
			  
		}		  		
		@Override
		public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
		{
			return state;
		}

		@Override
		public IBlockState getStateFromMeta(int meta)
		{
			return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.getHorizontal(meta));
		}

		@Override
		public int getMetaFromState(IBlockState state)
		{
			return state.getValue(PROPERTYFACING).getHorizontalIndex();
		}

		@Override
		protected BlockStateContainer createBlockState()
		{
			return new BlockStateContainer(this, new IProperty[] {PROPERTYFACING});
		}

		@Override
		public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) 
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof TileEntityBlockMirror) 
			{
				TileEntityBlockMirror te = (TileEntityBlockMirror) tileEntity;
				double x = te.getX();
				double y = te.getY();
				double z = te.getZ();
				if(worldIn.isRemote)
				{
			        if (entityIn instanceof EntityPlayer) { // should be an EntityPlayerMP; check first just to be sure to avoid crash
			          EntityPlayer entityPlayer = (EntityPlayer)entityIn;
			          float yaw = 0;//entityPlayer.rotationYaw;
			          int facing = te.getFacing();
			          entityPlayer.setPositionAndRotation(x, y, z, yaw, entityPlayer.rotationPitch);
			          entityPlayer.setRotationYawHead(yaw);
			          worldIn.playSound(x, y, z, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
			        }
				}
				else
				{
					if (entityIn instanceof EntityPlayerMP) { // should be an EntityPlayerMP; check first just to be sure to avoid crash
						EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entityIn;
				        final EntityPlayerMP dontPlayForThisPlayer = entityPlayerMP;
				        worldIn.playSound(dontPlayForThisPlayer, x, y, z, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
				    }
					
				}
			}
			super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		}
		
		  @Override
		  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			  ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
			  drops.add(new ItemStack(thewizardmod.mirror.StartupCommon.itemMirror, 1));
			  return drops;
		  }

}
