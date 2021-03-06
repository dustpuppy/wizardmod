package thewizardmod.magicInjector;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.TheWizardMod;
import thewizardmod.mirror.TileEntityBlockMirror;


public class BlockMagicInjector extends BlockContainer
{
	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool PROPERTYACTIVATED = PropertyBool.create("activated");

	private static final AxisAlignedBB BOUNCING_BOX = new AxisAlignedBB(0.0625 * 1, 0, 0.0625 * 1, 0.0625 * 15, 0.0625 * 15, 0.0625 * 15);
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB(0.0625 * 1, 0, 0.0625 * 1, 0.0625 * 15, 0.0625 * 14, 0.0625 * 15);

	public BlockMagicInjector()
	{
		super(Material.ROCK);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
	    this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTYFACING, EnumFacing.NORTH).withProperty(PROPERTYACTIVATED, Boolean.valueOf(false)));
	}

	// Called when the block is placed or loaded client side to get the tile entity for the block
	// Should return a new instance of the tile entity for the block
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMagicInjector();
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		  // find the quadrant the player is facing
		EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
// FIXME: Rewrite the whole injector shit, because nothing is working propper
		TileEntity tileEntity = worldIn.getTileEntity(pos);
//		System.out.println(tileEntity);
		if (tileEntity instanceof TileEntityMagicInjector) {
			int facing = MathHelper.floor_double((double) ((placer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
//			System.out.println("set facing " + facing);
			((TileEntityMagicInjector) tileEntity).setFacing(facing);
		}

		return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing);
		  
	}		  		
	// Called when the block is right clicked
	// In this block it is used to open the blocks gui when right clicked by a player
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		// Uses the gui handler registered to your mod to open the gui for the given gui id
		// open on the server side only  (not sure why you shouldn't open client side too... vanilla doesn't, so we better not either)
		if (worldIn.isRemote) return true;

		playerIn.openGui(TheWizardMod.instance, TheWizardMod.GUI_ID_MAGICINJECTOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	// This is where you can do something when the block is broken. In this case drop the inventory's contents
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileEntity);
		}


		// Super MUST be called last because it removes the tile entity
		super.breakBlock(worldIn, pos, state);
	}

	// here we have to update the block state depending on the activity state
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileEntityMagicInjector) {
			TileEntityMagicInjector tileInventoryMagicInjector = (TileEntityMagicInjector)tileEntity;
			int burningSlots = tileInventoryMagicInjector.numberOfBurningFuelSlots();
			burningSlots = MathHelper.clamp_int(burningSlots, 0, 4);
			if(burningSlots > 0)
			{
				return state.withProperty(PROPERTYACTIVATED, Boolean.valueOf(true));
			}
			else
			{
				return state.withProperty(PROPERTYACTIVATED, Boolean.valueOf(false));
			}
		}
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

	// necessary to define which properties your blocks use
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {PROPERTYFACING, PROPERTYACTIVATED});
	}


	// the block will render in the SOLID layer.  See http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html for more information.
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.SOLID;
	}

	// used by the renderer to control lighting and visibility of other blocks.
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}

	// used by the renderer to control lighting and visibility of other blocks, also by
	// (eg) wall or fence to control whether the fence joins itself to this block
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return false;
	}

	// render using a BakedModel
  // required because the default (super method) is INVISIBLE for BlockContainers.
	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}
	
	  @Override
	  @Deprecated
	  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		  return BOUNCING_BOX;
	  }
	  
	  @Override
	  @Deprecated
	  public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
		  super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	  }


}
