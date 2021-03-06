package thewizardmod.gemJar;

import javax.annotation.Nullable;

import thewizardmod.items.StartupCommon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGemJar extends TileEntity{

	public int counter = 0;
	private int MAX = 14;
	
	public Item item = StartupCommon.magicGem;
	
	public boolean addItem()
	{
		if(counter < MAX && item != null)
		{
//			System.out.println(item);
			counter++;
			markDirty();
			IBlockState state = worldObj.getBlockState(pos);
			worldObj.notifyBlockUpdate(pos, state, state, 3);
			return true;
		}
		return false;
	}
	
	public void removeItem()
	{
			if(counter > 0 && item != null)
			{
//				System.out.println(item);
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F, new ItemStack(item)));
				counter --;
				markDirty();
				IBlockState state = worldObj.getBlockState(pos);
				worldObj.notifyBlockUpdate(pos, state, state, 3);
			}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("counter", counter);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.counter = compound.getInteger("counter");
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		this.counter = tag.getInteger("counter");
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("counter", counter);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tag);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		tag.setInteger("counter", counter);
		return tag;
	}
	
}
