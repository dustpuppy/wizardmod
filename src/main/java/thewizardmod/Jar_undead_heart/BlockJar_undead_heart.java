package thewizardmod.Jar_undead_heart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
import thewizardmod.items.StartupCommon;

import com.google.common.collect.Lists;

public class BlockJar_undead_heart extends Block implements ITileEntityProvider
{
	private static final AxisAlignedBB BOUNCING_BOX = new AxisAlignedBB(0.0625 * 3, 0, 0.0625 * 3, 0.0625 * 13, 0.0625 * 15, 0.0625 * 13);
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB(0.0625 * 4, 0, 0.0625 * 4, 0.0625 * 12, 0.0625 * 14, 0.0625 * 12);
	
	public static int blockRange = 20;
	
  public BlockJar_undead_heart()
  {
    super(Material.GLASS);
    this.setCreativeTab(CreativeTabs.MISC);   // the block will appear on the Blocks tab in creative
    this.setTickRandomly(true);
    this.setLightLevel(0.3F);

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
	return new TileEntityJar_undead_heart();
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
	  if(!worldIn.isRemote)
	  {
		  TileEntity tileEntity = worldIn.getTileEntity(pos);
		  if(tileEntity instanceof TileEntityJar_undead_heart)
		  {
			  TileEntityJar_undead_heart jar = (TileEntityJar_undead_heart) tileEntity;
			  if(heldItem != null)
			  {
				  if(heldItem.getItem() == StartupCommon.heart)
				  {
					  if(jar.addItem())
					  {
						  heldItem.stackSize--;
						  /** values are 0, 1, 2, or 3 depending which direction player is facing */
						  int facing = MathHelper.floor_double((double) ((playerIn.rotationYaw * 4F) / 360F) + 0.5D) & 3;
						  TileEntityJar_undead_heart facedte = (TileEntityJar_undead_heart) tileEntity;
						  facedte.setFacing(facing);
						  worldIn.notifyBlockUpdate(pos, state, state, 3);
						  worldIn.scheduleBlockUpdate(pos, state.getBlock(), 1, 0);
						  return true;
					  }
				  }
			  }
		  }
	  }
	  return true;
  }

  /*
  @Override
  public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state,	EntityLivingBase entityLiving, ItemStack stack) {
	  int facing = MathHelper.floor_double((double) ((entityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
	  TileEntity te = world.getTileEntity(pos);
	  if (te != null && te instanceof TileEntityJar_undead_heart) {
		  TileEntityJar_undead_heart facedte = (TileEntityJar_undead_heart) te;
		  facedte.setFacing(facing);
		  world.notifyBlockUpdate(pos, state, state, 3);
	  }
	  super.onBlockPlacedBy(world, pos, state, entityLiving, stack);
  }
*/
  @Override
  public final ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
  	int meta = state.getBlock().getMetaFromState(state);
  	ItemStack ret = new ItemStack(this, 1, meta);
  	NBTTagCompound compound = new NBTTagCompound();
  	TileEntityJar_undead_heart tileEntity = (TileEntityJar_undead_heart) world.getTileEntity(pos);
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

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
	  TileEntityJar_undead_heart tileEntity = (TileEntityJar_undead_heart) worldIn.getTileEntity(pos);
	  tileEntity.lastChangeTime++;
	  List<EntityMob> mob = worldIn.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX() - blockRange, pos.getY() - blockRange, pos.getZ() - blockRange, pos.getX() + blockRange, pos.getY() + blockRange, pos.getZ() + blockRange));
	  int tickRate = (int)((float)20/mob.size());
	  if(tickRate > 20)
	  {
		  tickRate = 20;
	  }
	  if(tickRate < 3)
	  {
		  tickRate = 3;
	  }
	  worldIn.scheduleBlockUpdate(pos, state.getBlock(), tickRate, 0);
	super.updateTick(worldIn, pos, state, rand);
  }
}

