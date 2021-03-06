package thewizardmod.fluids;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.TheWizardMod;
import thewizardmod.Util.CapabilityUtils;

import com.google.common.collect.Lists;

public class BlockTank extends BlockContainer{
	public BlockTank() {
		super(Material.GLASS);
		this.setCreativeTab(CreativeTabs.MISC);
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
			else
			{
				playerIn.openGui(TheWizardMod.instance, thewizardmod.TheWizardMod.GUI_ID_TANK, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return false;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("BlockEntityTag")) {
			NBTTagCompound tag = stack.getTagCompound().getCompoundTag("BlockEntityTag");
			worldIn.getTileEntity(pos).markDirty();
		}
	}

	
	@Override
	public final ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		int meta = state.getBlock().getMetaFromState(state);
	  	ItemStack ret = new ItemStack(this, 1, meta);
	  	NBTTagCompound compound = new NBTTagCompound();
	  	TileEntityTank tileEntity = (TileEntityTank) world.getTileEntity(pos);
	  	NBTTagCompound tileEntityTag = new NBTTagCompound();
	  	tileEntity.writeToNBT(tileEntityTag);
	  	compound.setTag("BlockEntityTag", tileEntityTag);
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
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTank();
	}


}