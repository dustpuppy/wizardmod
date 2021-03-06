package thewizardmod.altar;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAltar extends Block implements ITileEntityProvider
{
	
  public BlockAltar()
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
  public TileEntity createNewTileEntity(World worldIn, int meta) {
	return new TileEntityAltar();
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
	  Random random = new Random();
	  int chance = 930;
	  int instability = random.nextInt(10 + Altar.checkForTorches(worldIn, pos, state, playerIn));
	  if(!worldIn.isRemote)
	  {
		  TileEntity tileEntity = worldIn.getTileEntity(pos);
		  if(tileEntity instanceof TileEntityAltar)
		  {
			  TileEntityAltar pedestral = (TileEntityAltar) tileEntity;
			  if(heldItem != null && pedestral.counter == 0)
			  {
				  if(heldItem.getItem() == thewizardmod.Wands.StartupCommon.goldenWand
						  || heldItem.getItem() == thewizardmod.Wands.StartupCommon.blazeWand)
				  {
//					  System.out.println(Altar.checkForRunes(worldIn, pos, state, playerIn) + " Runes found");
//					  System.out.println(Altar.checkForTorches(worldIn, pos, state, playerIn) + " Torches found");
//					  System.out.println("Has 4 Pedestrals " + Altar.checkForPedestrals(worldIn, pos, state, playerIn));
//					  System.out.println(" Item = " + Altar.returnAltarResult());

					  if(Altar.checkForPedestrals(worldIn, pos, state, playerIn))
					  {
							  chance -= Altar.checkForRunes(worldIn, pos, state, playerIn);
						  // as more torches and runes around the altar, as bigger the chance to keep items on pedestrals
							ItemStack boots = playerIn.inventory.armorInventory[0];
							ItemStack pants = playerIn.inventory.armorInventory[1];
							ItemStack chest = playerIn.inventory.armorInventory[2];
							ItemStack head = playerIn.inventory.armorInventory[3];
							if(boots != null)
							{
								if(boots.getItem() == thewizardmod.items.StartupCommon.ironBoots)
								{
									chance -= 2;
									instability += 2;
								}
								if(boots.getItem() == thewizardmod.items.StartupCommon.robeBoots)
								{
									chance -= 4;
									instability += 4;
								}
							}

							if(pants != null)
							{
								if(pants.getItem() == thewizardmod.items.StartupCommon.ironLeggings)
								{
									chance -= 3;
									instability += 3;
								}
								if(pants.getItem() == thewizardmod.items.StartupCommon.robeLeggings)
								{
									chance -= 6;
									instability += 6;
								}
							}

							if(chest != null)
							{
								if(chest.getItem() == thewizardmod.items.StartupCommon.ironChestplate)
								{
									chance -= 5;
									instability += 5;
								}
								if(chest.getItem() == thewizardmod.items.StartupCommon.robeChestplate)
								{
									chance -= 10;
									instability += 10;
								}
							}

							if(head != null)
							{
								if(head.getItem() == thewizardmod.items.StartupCommon.ironHelmet)
								{
									chance -= 2;
									instability += 2;
								}
								if(head.getItem() == thewizardmod.items.StartupCommon.robeHelmet)
								{
									chance -= 4;
									instability += 4;
								}
							}

							// Every chalice near altar will be good for chances, but more then one will be bad for instability
							int chalice = Altar.checkForChalice(worldIn, pos, state, playerIn);
							if(chalice > 0)
							{
								chance -= chalice*2;
							}
							if(chalice > 1)
							{
								instability -= chalice*2;
							}
							
						  if(chance < 10)
						  {
							  chance = 10;
						  }
						  pedestral.setMagicDone();
						  pedestral.setItem(Altar.returnAltarResult(worldIn, chance));
						  if(instability > 0)
						  {
							  pedestral.addItem();
						  }
						  worldIn.scheduleBlockUpdate(pos, state.getBlock(), 0, 0);
						  return true;
					  }
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
	  if(tileEntity instanceof TileEntityAltar)
	  {
		  TileEntityAltar altar = (TileEntityAltar) tileEntity;
		  if(altar.magicDone)
		  {
			  thewizardmod.Util.ParticleSpread.spread(worldIn, pos.getX(), pos.getY(), pos.getZ(), 150, 0.1D, 1.0D, EnumParticleTypes.FLAME);
			  thewizardmod.Util.ParticleSpread.spread(worldIn, pos.getX(), pos.getY(), pos.getZ(), 150, 0.1D, 1.0D, EnumParticleTypes.SMOKE_NORMAL);
		  } 
		  else
		  {
			  thewizardmod.Util.ParticleSpread.spread(worldIn, pos.getX(), pos.getY(), pos.getZ(), 1, 0.0D, 0.0D, EnumParticleTypes.DRAGON_BREATH);
		  }
		  altar.clearMagicDone();
	  }
	  super.randomDisplayTick(stateIn, worldIn, pos, rand);
  }
}

