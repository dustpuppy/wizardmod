package thewizardmod.Jar_undead_heart;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import thewizardmod.items.StartupCommon;

public class TileEntityJar_undead_heart extends TileEntity{

	public int counter = 0;
	
	public Item item = StartupCommon.heart;
	
	public static long lastChangeTime;
	public int facing;
	
	public boolean addItem()
	{
		if(counter == 0 && item != null)
		{
			counter = 1;
			markDirty();
			IBlockState state = worldObj.getBlockState(pos);
			worldObj.notifyBlockUpdate(pos, state, state, 3);
			return true;
		}
		return false;
	}
	
	public int getFacing()
	{
		return facing;
	}
	
	public void setFacing(int facing)
	{
		this.facing = facing;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("counter", counter);
		compound.setLong("lastChangeTime", lastChangeTime);
		compound.setInteger("facing", facing);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.counter = compound.getInteger("counter");
		this.lastChangeTime = compound.getLong("lastChangeTime");
		this.facing = compound.getInteger("facing");
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		this.counter = tag.getInteger("counter");
		this.lastChangeTime = tag.getLong("lastChangeTime");
		this.facing = tag.getInteger("facing");
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("counter", counter);
		tag.setLong("lastChangeTime", lastChangeTime);
		tag.setInteger("facing", facing);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tag);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		tag.setInteger("counter", counter);
		tag.setLong("lastChangeTime", lastChangeTime);
		tag.setInteger("facing", facing);
		return tag;
	}

	
	
}
