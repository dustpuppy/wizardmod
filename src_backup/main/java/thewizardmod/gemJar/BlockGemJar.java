package thewizardmod.gemJar;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import thewizardmod.items.StartupCommon;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Lists;

public class BlockGemJar extends Block implements ITileEntityProvider
{
	private static final AxisAlignedBB BOUNCING_BOX = new AxisAlignedBB(0.0625 * 3, 0, 0.0625 * 3, 0.0625 * 13, 0.0625 * 15, 0.0625 * 13);
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB(0.0625 * 4, 0, 0.0625 * 4, 0.0625 * 12, 0.0625 * 14, 0.0625 * 12);
	
  public BlockGemJar()
  {
    super(Material.GLASS);
    this.setCreativeTab(CreativeTabs.MISC);   // the block will appear on the Blocks tab in creative
    

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
	return new TileEntityGemJar();
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
	  if(!worldIn.isRemote)
	  {
		  TileEntity tileEntity = worldIn.getTileEntity(pos);
		  if(tileEntity instanceof TileEntityGemJar)
		  {
			  TileEntityGemJar jar = (TileEntityGemJar) tileEntity;
			  if(heldItem != null)
			  {
				  if(heldItem.getItem() == StartupCommon.magicGem)
				  {
//					  jar.setItemType(heldItem.getItem());
					  if(jar.addItem())
					  {
						  heldItem.stackSize--;
						  return true;
					  }
				  }
			  }
			  jar.removeItem();
		  }
	  }
	  return true;
  }
	
  @Override
  public final ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
  	int meta = state.getBlock().getMetaFromState(state);
  	ItemStack ret = new ItemStack(this, 1, meta);
  	NBTTagCompound compound = new NBTTagCompound();
  	TileEntityGemJar tileEntity = (TileEntityGemJar) world.getTileEntity(pos);
  	NBTTagCompound tileEntityTag = new NBTTagCompound();
  	tileEntity.writeToNBT(tileEntityTag);
  	compound.setTag("BlockEntityTag", tileEntityTag);	// Key has to be BlockEntityTag. No custom name, or it will not work
  	ret.setTagCompound(compound);
  	return Lists.newArrayList(ret);
  }

  @Override
  public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player,	boolean willHarvest) {
  	if (willHarvest) return true; 
  	return super.removedByPlayer(state, world, pos, player, willHarvest);
  }

  @Override
  public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity, ItemStack tool) {
  	super.harvestBlock(world, player, pos, state, tileEntity, tool);
  	world.setBlockToAir(pos);
  }
}

