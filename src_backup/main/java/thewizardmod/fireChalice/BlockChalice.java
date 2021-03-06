package thewizardmod.fireChalice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.items.StartupCommon;

import com.google.common.collect.Lists;

public class BlockChalice extends Block implements ITileEntityProvider
{
	private static final AxisAlignedBB BOUNCING_BOX = new AxisAlignedBB(0.0625 * 4, 0, 0.0625 * 4, 0.0625 * 12, 0.0625 * 15, 0.0625 * 12);
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB(0.0625 * 5, 0, 0.0625 * 5, 0.0625 * 11, 0.0625 * 14, 0.0625 * 11);
	
  public BlockChalice()
  {
    super(Material.ROCK);
    this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    this.setTickRandomly(true);
    this.setLightLevel(0.3F);

    this.setTickRandomly(true);
    setHardness(0.3F);
    
  }

  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer()
  {
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
  @Deprecated
	public boolean hasTileEntity() {
	return true;
  }
  
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

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
	return new TileEntityChalice();
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
	  if(!worldIn.isRemote)
	  {
		  TileEntity tileEntity = worldIn.getTileEntity(pos);
		  if(tileEntity instanceof TileEntityChalice)
		  {
			  TileEntityChalice pedestral = (TileEntityChalice) tileEntity;
			  if(heldItem != null && pedestral.counter == 0)
			  {
				  if(heldItem.getItem() == thewizardmod.Wands.StartupCommon.woodenWand
						  ||heldItem.getItem() == thewizardmod.Wands.StartupCommon.ironWand
						  ||heldItem.getItem() == thewizardmod.Wands.StartupCommon.goldenWand
						  ||heldItem.getItem() == thewizardmod.Wands.StartupCommon.blazeWand)
				  if(pedestral.addItem())
				  {
//					  heldItem.stackSize--;
					  worldIn.scheduleBlockUpdate(pos, state.getBlock(), 0, 0);
					  return true;
				  }
			  }
			  pedestral.removeItem();
		  }
	  }
	  return true;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	  TileEntity tileEntity = worldIn.getTileEntity(pos);
	  if(tileEntity instanceof TileEntityChalice)
	  {
		  TileEntityChalice pedestral = (TileEntityChalice) tileEntity;
		  if(pedestral.counter > 0)
		  {
			  thewizardmod.Util.ParticleSpread.spread(worldIn, pos.getX(), pos.getY(), pos.getZ(), 2, 0.1D, 1.0D, EnumParticleTypes.DRAGON_BREATH);
		  }
		  if(pedestral.itemDeleted)
		  {
			  thewizardmod.Util.ParticleSpread.spread(worldIn, pos.getX(), pos.getY(), pos.getZ(), 150, 0.1D, 1.0D, EnumParticleTypes.FLAME);
			  thewizardmod.Util.ParticleSpread.spread(worldIn, pos.getX(), pos.getY(), pos.getZ(), 150, 0.1D, 1.0D, EnumParticleTypes.SMOKE_NORMAL);
			  pedestral.clearItemDelete();
		  }
	  }
	  super.randomDisplayTick(stateIn, worldIn, pos, rand);
  }
}

