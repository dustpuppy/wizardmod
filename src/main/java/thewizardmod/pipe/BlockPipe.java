package thewizardmod.pipe;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.Util.CapabilityUtils;

public class BlockPipe extends Block{ 
	private static final AxisAlignedBB BOUNCING_BOX_SOUTH_NORTH = new AxisAlignedBB(0, 0.0625 * 5,  0.0625 * 5,  0.0625 * 16,  0.0625 * 11, 0.0625 * 11);
	private static final AxisAlignedBB BOUNCING_BOX_EAST_WEST =  new AxisAlignedBB(0.0625 * 5, 0.0625 * 5, 0,  0.0625 * 11,  0.0625 * 11, 0.0625 * 16);
	public static final PropertyDirection PROPERTYFACING = BlockHorizontal.FACING;

	
	public BlockPipe() {
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
	public boolean hasTileEntity() {
		return true;
	}


	private IFluidHandler getFluidHandler(IBlockAccess world, BlockPos pos) {
		return CapabilityUtils.getCapability(world.getTileEntity(pos), CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
	}

	  @Override
	  @Deprecated
	  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		  if(state.getValue(PROPERTYFACING) == EnumFacing.NORTH)
		  {
			  return BOUNCING_BOX_SOUTH_NORTH;
		  }
		  if(state.getValue(PROPERTYFACING) == EnumFacing.SOUTH)
		  {
			  return BOUNCING_BOX_SOUTH_NORTH;
		  }
		  if(state.getValue(PROPERTYFACING) == EnumFacing.EAST)
		  {
			  return BOUNCING_BOX_EAST_WEST;
		  }
		  if(state.getValue(PROPERTYFACING) == EnumFacing.WEST)
		  {
			  return BOUNCING_BOX_EAST_WEST;
		  }
		  return null;
	  }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		final IFluidHandler fluidHandler = getFluidHandler(worldIn, pos);

		if (fluidHandler != null) {
			if(heldItem != null)
			{
					// Try fill/empty the held fluid container from the tank
					boolean success = FluidUtil.interactWithFluidHandler(heldItem, fluidHandler, playerIn);
					//If the held item is a fluid container, stop processing here so it doesn't try to place its contents
					return FluidUtil.getFluidHandler(heldItem) != null;
			}
		}
		return false;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPipe();
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		  // find the quadrant the player is facing
		EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		
//		return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing);
		return this.getDefaultState().withProperty(PROPERTYFACING, BlockPistonBase.getFacingFromEntity(pos, placer));
		  
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


}