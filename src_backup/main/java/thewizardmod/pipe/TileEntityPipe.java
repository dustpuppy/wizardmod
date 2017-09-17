package thewizardmod.pipe;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.capability.TileFluidHandler;

public class TileEntityPipe extends TileFluidHandler{
	
	// 100mB a pipe can hold/transport
	public static final int CAPACITY = 100;

	public TileEntityPipe() {
		tank = new FluidTankWithTile(this, CAPACITY);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

}