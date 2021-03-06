package thewizardmod.altar;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAltar extends TileEntity{

	public int counter = 0;
	
	public Item item;
	
	public long lastChangeTime;
	
	public boolean magicDone = false;
	
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
			counter = 1;
			magicDone = false;
			markDirty();
			IBlockState state = worldObj.getBlockState(pos);
			worldObj.notifyBlockUpdate(pos, state, state, 3);
			return true;
		}
		return false;
	}
	
	public void setMagicDone()
	{
		this.magicDone = true;
	}
	
	public void clearMagicDone()
	{
		this.magicDone = false;
	}
	
	public void removeItem()
	{
			if(counter > 0 && item != null)
			{
//				System.out.println(item);
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F, new ItemStack(item)));
				this.counter --;
				this.magicDone = false;
				markDirty();
				IBlockState state = worldObj.getBlockState(pos);
				worldObj.notifyBlockUpdate(pos, state, state, 3);
			}
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("counter", counter);
		compound.setLong("lastChangeTime", lastChangeTime);
		compound.setBoolean("magicDone", magicDone);
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
		this.magicDone = compound.getBoolean("magicDone");
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
		this.magicDone = tag.getBoolean("magicDone");
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
		tag.setBoolean("magicDone", magicDone);
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
		tag.setBoolean("magicDone", magicDone);
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
