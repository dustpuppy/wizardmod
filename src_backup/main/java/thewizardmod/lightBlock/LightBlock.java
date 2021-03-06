package thewizardmod.lightBlock;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import thewizardmod.pedestral.TileEntityPedestral;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LightBlock extends Block
{
    protected static final AxisAlignedBB MY_AABB = new AxisAlignedBB(0.425D, 0.425D, 0.425D, 0.575D, 0.500D, 0.575D);

  public LightBlock()
  {
    
    super(Material.CIRCUITS); 
    this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);   // the block will appear on the Blocks tab in creative
    this.setHardness(0.0F); 
    this.setLightLevel(1F); 
    this.setTickRandomly(true);
     
  }

  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer()
  {
//    return BlockRenderLayer.SOLID;
//    return BlockRenderLayer.TRANSLUCENT;
    return BlockRenderLayer.CUTOUT;
  }

  @Override
  public boolean isOpaqueCube(IBlockState iBlockState) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState iBlockState) {
    return false;
  }

  
//  @Override
  public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
//	    return EnumBlockRenderType.MODEL;
	    return EnumBlockRenderType.INVISIBLE;
  }
  
  
  // by returning a null collision bounding box we stop the player from colliding with it
  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos)
  {
    return MY_AABB;
  }

  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
  {
      return MY_AABB;
  }
  
  @Override
public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
	  super.onBlockAdded(worldIn, pos, state);
}

  
  @Override
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	  worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX()+0.5D, pos.getY()+0.6D, pos.getZ()+0.5D, 0, 0, 0);
	  worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX()+0.5D, pos.getY()+0.6D, pos.getZ()+0.5D, 0.1D, 0, 0);
	  super.randomDisplayTick(stateIn, worldIn, pos, rand);
  }

}
