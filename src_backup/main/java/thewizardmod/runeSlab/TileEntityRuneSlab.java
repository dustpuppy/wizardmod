package thewizardmod.runeSlab;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRuneSlab extends TileEntity{

	public int runeType;
	public int magicChance;
	
	public long lastChangeTime;

	
	public void setRuneType(int type)
	{
		runeType = type;
	}
	
	public int getRuneType()
	{
		return runeType;
	}

	public int getMagicChance()
	{
		return magicChance;
	}
	
	public void setMagicChance(int chance)
	{
		this.magicChance = chance;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("runeType", runeType);
		compound.setInteger("magicChance", magicChance);
		compound.setLong("lastChangeTime", lastChangeTime);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.runeType = compound.getInteger("runeType");
		this.lastChangeTime = compound.getLong("lastChangeTime");
		this.magicChance = compound.getInteger("magicChance");
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		this.runeType = tag.getInteger("runeType");
		this.lastChangeTime = tag.getLong("lastChangeTime");
		this.magicChance = tag.getInteger("magicChance");
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("runeType", runeType);
		tag.setLong("lastChangeTime", lastChangeTime);
		tag.setInteger("magicChance", magicChance);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tag);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		tag.setInteger("runeType", runeType);
		tag.setLong("lastChangeTime", lastChangeTime);
		tag.setInteger("magicChance", magicChance);
		return tag;
	}
	
}
