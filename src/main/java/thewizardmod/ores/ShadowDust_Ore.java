package thewizardmod.ores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.items.StartupCommon;

public class ShadowDust_Ore extends Block
{
	public static boolean GenerateOverWorld = true;
	public static int OverWorldMin = 1;
	public static int OverWorldMax = 70;
	public static int OverWorldChance = 20;
	public static int OverWorldMinSize = 5;
	public static int OverWorldMaxSize = 10;
	
	public static boolean GenerateNether = thewizardmod.configuration.ModConfiguration.GenerateNether;
	public static int NetherWorldMin = 1;
	public static int NetherWorldMax = 128;
	public static int NetherWorldChance = 20;
	public static int NetherWorldMinSize = 5;
	public static int NetherWorldMaxSize = 10;
	
	public static boolean GenerateEnd = thewizardmod.configuration.ModConfiguration.GenerateEnd;
	public static int EndWorldMin = 1;
	public static int EndWorldMax = 128;
	public static int EndWorldChance = 20;
	public static int EndWorldMinSize = 5;
	public static int EndWorldMaxSize = 10;

  public ShadowDust_Ore()
  {
    super(Material.ROCK);
    this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);   // the block will appear on the Blocks tab in creative
    
    setHardness(3.0F);
    setResistance(5.0F);
    
  }

  // the block will render in the SOLID layer.  See http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html for more information.
  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer()
  {
    return BlockRenderLayer.SOLID;
  }

  // used by the renderer to control lighting and visibility of other blocks.
  // set to true because this block is opaque and occupies the entire 1x1x1 space
  // not strictly required because the default (super method) is true
  @Override
  public boolean isOpaqueCube(IBlockState iBlockState) {
    return true;
  }

  // used by the renderer to control lighting and visibility of other blocks, also by
  // (eg) wall or fence to control whether the fence joins itself to this block
  // set to true because this block occupies the entire 1x1x1 space
  // not strictly required because the default (super method) is true
  @Override
  public boolean isFullCube(IBlockState iBlockState) {
    return true;
  }

  // render using a BakedModel (mbe01_block_simple.json --> mbe01_block_simple_model.json)
  // not strictly required because the default (super method) is MODEL.
  @Override
  public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
    return EnumBlockRenderType.MODEL;
  }
  
  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
	  ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
	  Random rand = new Random();
	  drops.add(new ItemStack(StartupCommon.shadowDust, rand.nextInt(2) + 1 + fortune));
	  return drops;
  }
}
