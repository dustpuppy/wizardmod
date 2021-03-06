package thewizardmod.runeSlab;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import thewizardmod.Jar_undead_heart.TileEntityJar_undead_heart;
import thewizardmod.items.StartupCommon;
import thewizardmod.runes.Fehu;

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

public class BlockRuneSlab extends Block implements ITileEntityProvider
{
	private static final AxisAlignedBB BOUNCING_BOX = new AxisAlignedBB(0, 0, 0, 0.0625 * 16, 0.0625 * 2, 0.0625 * 16);
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB(0, 0, 0, 0.0625 * 16, 0.0625 * 2, 0.0625 * 16);
	
  public BlockRuneSlab()
  {
    super(Material.ROCK);
    this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    

    setHardness(0.3F);
    
  }

  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer()
  {
    return BlockRenderLayer.SOLID;
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
	return new TileEntityRuneSlab();
  }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		  if(!worldIn.isRemote)
		  {
			  TileEntity tileEntity = worldIn.getTileEntity(pos);
			  if(tileEntity instanceof TileEntityRuneSlab)
			  {
				  TileEntityRuneSlab runeSlab = (TileEntityRuneSlab) tileEntity;
				  if(heldItem != null)
				  {
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.fehu)
					  {
						  runeSlab.setRuneType(1);
						  runeSlab.setMagicChance(20);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.uruz)
					  {
						  runeSlab.setRuneType(2);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.thurisaz)
					  {
						  runeSlab.setRuneType(3);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.ansuz)
					  {
						  runeSlab.setRuneType(4);
						  runeSlab.setMagicChance(5);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.raido)
					  {
						  runeSlab.setRuneType(5);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.kaunan)
					  {
						  runeSlab.setRuneType(6);
						  runeSlab.setMagicChance(10);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.gibo)
					  {
						  runeSlab.setRuneType(7);
						  runeSlab.setMagicChance(10);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.wunjo)
					  {
						  runeSlab.setRuneType(8);
						  runeSlab.setMagicChance(10);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.haglaz)
					  {
						  runeSlab.setRuneType(9);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.naudiz)
					  {
						  runeSlab.setRuneType(10);
						  runeSlab.setMagicChance(5);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.eisa)
					  {
						  runeSlab.setRuneType(11);
						  runeSlab.setMagicChance(1);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.jeran)
					  {
						  runeSlab.setRuneType(12);
						  runeSlab.setMagicChance(15);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.ihwaz)
					  {
						  runeSlab.setRuneType(13);
						  runeSlab.setMagicChance(4);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.pertho)
					  {
						  runeSlab.setRuneType(14);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.algiz)
					  {
						  runeSlab.setRuneType(15);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.sowulo)
					  {
						  runeSlab.setRuneType(16);
						  runeSlab.setMagicChance(10);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.teiwaz)
					  {
						  runeSlab.setRuneType(17);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.berko)
					  {
						  runeSlab.setRuneType(18);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.ehwaz)
					  {
						  runeSlab.setRuneType(19);
						  runeSlab.setMagicChance(4);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.mann)
					  {
						  runeSlab.setRuneType(20);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.laguz)
					  {
						  runeSlab.setRuneType(21);
						  runeSlab.setMagicChance(2);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.ingwaz)
					  {
						  runeSlab.setRuneType(22);
						  runeSlab.setMagicChance(4);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.othalan)
					  {
						  runeSlab.setRuneType(23);
						  runeSlab.setMagicChance(10);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.dagaz)
					  {
						  runeSlab.setRuneType(24);
						  runeSlab.setMagicChance(5);
						  heldItem.stackSize--;
					  }
					  if(heldItem.getItem() == thewizardmod.runes.StartupCommon.blank)
					  {
						  runeSlab.setRuneType(0);
						  runeSlab.setMagicChance(0);
						  heldItem.stackSize--;
					  }
					  worldIn.notifyBlockUpdate(pos, state, state, 3);
				  }
			  }
		  }
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
}

