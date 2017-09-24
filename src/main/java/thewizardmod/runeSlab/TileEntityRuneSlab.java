package thewizardmod.runeSlab;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityRuneSlab extends TileEntity implements ITickable{

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
	
	
	public void handleFertalizing() {
		BlockPos centerOfField = this.getPos();

		Random random = new Random();
		
		int x = centerOfField.getX() - 2;
		int y = centerOfField.getY();
		int z = centerOfField.getZ() - 2;

		for (int i = 0; i < 5; i++) 
		{
			for (int j = 0; j < 5; j++) 
			{
				BlockPos newpos = new BlockPos(x + i, y, z + j);
				Block block = worldObj.getBlockState(newpos).getBlock();
				if (block != null) 
				{
					int state = worldObj.getBlockState(newpos).getBlock().getMetaFromState(worldObj.getBlockState(newpos));
					int chance = random.nextInt(1500);
					if(chance == 0)
					{
						ItemDye.applyBonemeal(new ItemStack(Items.DYE, 15, 0), worldObj, newpos, null);
					}
				}
			}
		}
	}

	
	public void handleDefence() {
		BlockPos centerOfField = this.getPos();

		double posX = centerOfField.getX();
		double posY = centerOfField.getY();
		double posZ = centerOfField.getZ();
		if (!worldObj.isRemote) {
			for (Object obj : worldObj.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(posX - 5, posY - 1, posZ - 5, posX + 5, posY + 1, posZ + 5))) {
				if (obj instanceof EntityMob) 
				{
					EntityMob mob = (EntityMob) obj;
					mob.setPosition(mob.posX, mob.posY + 20, mob.posZ);
				}
				if (obj instanceof EntitySlime) 
				{
					EntitySlime mob = (EntitySlime) obj;
					mob.setPosition(mob.posX, mob.posY + 20, mob.posZ);
				}
			}
		}
	}

	public void handleHealth() {
		BlockPos centerOfField = this.getPos();

		double posX = centerOfField.getX();
		double posY = centerOfField.getY();
		double posZ = centerOfField.getZ();
		if (!worldObj.isRemote) {
			for (Object obj : worldObj.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(posX - 0.5D, posY, posZ - 0.5D, posX + 0.5D, posY + 1, posZ + 0.5D))) {
				if (obj instanceof EntityPlayer) 
				{
					EntityPlayer player = (EntityPlayer) obj;
					player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20));
					player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20));
				}
			}
		}
	}
	@Override
	public void update() {
//		System.out.println(runeType);
		if(runeType == 5)		// rune of growth
		{
			handleFertalizing();
		}
		if(runeType == 15)		// rune of defence
		{
			handleDefence();
		}
		if(runeType == 20)		// rune of health
		{
			handleHealth();
		}
	}

	
	
}
