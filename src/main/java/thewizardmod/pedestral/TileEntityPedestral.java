package thewizardmod.pedestral;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPedestral extends TileEntity{

	public int counter = 0;
	
	public Item item;
	
	public long lastChangeTime;
	
	public boolean itemDeleted = false;
	
	public void setItem(Item i)
	{
		item = i;
	}
	
	public Item getItem()
	{
		return item;
	}
	
	public boolean addItem()
	{
		if(counter == 0 && item != null)
		{
			this.counter = 1;
			this.itemDeleted = false;
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
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F, new ItemStack(item)));
				this.counter --;
				this.itemDeleted = false;
				markDirty();
				IBlockState state = worldObj.getBlockState(pos);
				worldObj.notifyBlockUpdate(pos, state, state, 3);
			}
	}
	
	public void deleteItem()
	{
			if(counter > 0 && item != null)
			{
				this.counter --;
				this.itemDeleted = true;
				item = null;
				markDirty();
				IBlockState state = worldObj.getBlockState(pos);
				worldObj.notifyBlockUpdate(pos, state, state, 3);
			}
	}

	public void clearItemDelete()
	{
		this.itemDeleted = false;
		markDirty();
		IBlockState state = worldObj.getBlockState(pos);
		worldObj.notifyBlockUpdate(pos, state, state, 3);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("counter", counter);
		compound.setLong("lastChangeTime", lastChangeTime);
		compound.setBoolean("itemDeleted", itemDeleted);
		if(counter > 0)
		{
			NBTTagCompound stack = new NBTTagCompound();
			ItemStack itemStack = new ItemStack(item);
			itemStack.writeToNBT(stack);
			compound.setTag("item", stack);
		}
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.counter = compound.getInteger("counter");
		this.lastChangeTime = compound.getLong("lastChangeTime");
		this.itemDeleted = compound.getBoolean("itemDeleted");
		ItemStack itemStack;
		itemStack = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("item"));
		if(itemStack != null)
			item = itemStack.getItem();
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		this.counter = tag.getInteger("counter");
		this.lastChangeTime = tag.getLong("lastChangeTime");
		this.itemDeleted = tag.getBoolean("itemDeleted");
		ItemStack itemStack;
		itemStack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("item"));
		if(itemStack != null)
			item = itemStack.getItem();
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("counter", counter);
		tag.setLong("lastChangeTime", lastChangeTime);
		tag.setBoolean("itemDeleted", itemDeleted);
		if(counter > 0)
		{
			NBTTagCompound stack = new NBTTagCompound();
			ItemStack itemStack = new ItemStack(item);
			itemStack.writeToNBT(stack);
			tag.setTag("item", stack);
		}
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tag);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		tag.setInteger("counter", counter);
		tag.setLong("lastChangeTime", lastChangeTime);
		tag.setBoolean("itemDeleted", itemDeleted);
		if(counter > 0)
		{
			NBTTagCompound stack = new NBTTagCompound();
			ItemStack itemStack = new ItemStack(item);
			itemStack.writeToNBT(stack);
			tag.setTag("item", stack);
		}
		return tag;
	}

	
	
}
