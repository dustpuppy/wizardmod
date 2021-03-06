package thewizardmod.mirror;

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

public class TileEntityBlockMirror extends TileEntity{

	public int facing;
	public double posX, posY, posZ;
	
	public int getFacing()
	{
		return facing;
	}
	
	public void setFacing(int facing)
	{
		this.facing = facing;
		markDirty();
	}
	
	public void setPosition(double x, double y, double z)
	{
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		markDirty();
	}
	
	public double getX()
	{
		return posX;
	}
	
	public double getY()
	{
		return posY;
	}
	
	public double getZ()
	{
		return posZ;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("facing", facing);
		tag.setDouble("X", posX);
		tag.setDouble("Y", posY);
		tag.setDouble("Z", posZ);
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.facing = tag.getInteger("facing");
		this.posX = tag.getDouble("X");
		this.posY = tag.getDouble("Y");
		this.posZ = tag.getDouble("Z");
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		this.facing = tag.getInteger("facing");
		this.posX = tag.getDouble("X");
		this.posY = tag.getDouble("Y");
		this.posZ = tag.getDouble("Z");
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("facing", facing);
		tag.setDouble("X", posX);
		tag.setDouble("Y", posY);
		tag.setDouble("Z", posZ);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tag);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		tag.setInteger("facing", facing);
		tag.setDouble("X", posX);
		tag.setDouble("Y", posY);
		tag.setDouble("Z", posZ);
		return tag;
	}

	
	
}
