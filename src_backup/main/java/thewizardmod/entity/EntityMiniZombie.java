package thewizardmod.entity;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStrongholdPieces.ChestCorridor;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.FarmCrops.TileEntityFarmCrops;
import thewizardmod.FarmFeeder.TileEntityFarmFeeder;
import thewizardmod.Util.CapabilityUtils;
import thewizardmod.books.GuiAltar;
import thewizardmod.fluids.TileEntityTank;
import thewizardmod.items.StartupCommon;

public class EntityMiniZombie extends EntityMob{

    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityWeirdZombie.class, DataSerializers.BOOLEAN);

    public String name = "unknown";
    private double maxDistance = 10;
    private int idleTime = 0;
    
    private BlockPos destinationPos;
    public BlockPos inventoryPos;
    public BlockPos sourcePos; 
    private boolean isMoving = false;
    public boolean isPicker = false;		// picks up items from ground
    public boolean isTransporter = false;	// transports items from one chest to another

    public EntityMiniZombie(World worldIn) {
        super(worldIn);
    	this.setDropItemsWhenDead(false);
    	this.setDropChance(EntityEquipmentSlot.CHEST, 0);	// prevent of dropping the chestplate
    	this.getDataManager().register(ARMS_RAISED, Boolean.valueOf(false));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @SideOnly(Side.CLIENT)
    public boolean isArmsRaised() {
        return this.getDataManager().get(ARMS_RAISED).booleanValue();
    }

    public void setArmsRaised(boolean armsRaised) {
        this.getDataManager().set(ARMS_RAISED, Boolean.valueOf(armsRaised));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10F);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        }


    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
//        this.tasks.addTask(5, new EntityAIWander(this, 0.3F));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));        
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));

        this.applyEntityAI();
    }

    public void applyEntityAI() {
    }

    public void writeEntityToNBT(NBTTagCompound nbt)
    {
    	super.writeEntityToNBT(nbt);
    	
    	nbt.setBoolean("isPicker", isPicker);
    	nbt.setBoolean("isTransporter", isTransporter);
    	if(inventoryPos != null)
    	{
    		int x = inventoryPos.getX();
    		int y = inventoryPos.getY();
    		int z = inventoryPos.getZ();
       		nbt.setInteger("inventoryX", x);
       		nbt.setInteger("inventoryY", y);
       		nbt.setInteger("inventoryZ", z);
    	}
    	
    	if(sourcePos != null)
    	{
    		int x = sourcePos.getX();
    		int y = sourcePos.getY();
    		int z = sourcePos.getZ();
       		nbt.setInteger("sourceX", x);
       		nbt.setInteger("sourceY", y);
       		nbt.setInteger("sourceZ", z);
    	}
    	
    }
    
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
    	super.readEntityFromNBT(nbt);
    	
    	this.isPicker = nbt.getBoolean("isPicker");
    	if(isPicker)
    	{
    		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(StartupCommon.itemPickerChestplate));
    	}
    	this.isTransporter = nbt.getBoolean("isTransporter");
    	if(isTransporter)
    	{
    		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(StartupCommon.itemTransporterChestplate));
    	}
    	if(nbt.hasKey("inventoryX") && nbt.hasKey("inventoryY") && nbt.hasKey("inventoryZ"))
    	{
    		int x = nbt.getInteger("inventoryX");
    		int y = nbt.getInteger("inventoryY");
    		int z = nbt.getInteger("inventoryZ");
    		inventoryPos = new BlockPos(x, y, z);
    	}
    	if(nbt.hasKey("sourceX") && nbt.hasKey("sourceY") && nbt.hasKey("sourceZ"))
    	{
    		int x = nbt.getInteger("sourceX");
    		int y = nbt.getInteger("sourceY");
    		int z = nbt.getInteger("sourceZ");
    		sourcePos = new BlockPos(x, y, z);
    	}
    }   
 
    
    @SideOnly(Side.CLIENT)
    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player,	Vec3d vec, @Nullable ItemStack stack, EnumHand hand) {
    	GuiMiniZombie.setup(this);
    	Minecraft.getMinecraft().displayGuiScreen(new GuiMiniZombie());
    	return super.applyPlayerInteraction(player, vec, stack, hand);
    }

    public void onUpdate() {
    	
    	idleTime++;
    	
    	tryToPickup();
			
		if(this.isMoving && checkDestinationPosition())
		{
			this.isMoving = false;
		}
			
		if (this.inventoryPos != null)
		{
			// Test if the not to far away from home chest
			if(inventoryPos.distanceSq(posX, posY, posZ) > maxDistance)
			{
				moveZombieTo(inventoryPos);
			}
			
			checkInventory();
			checkMachines();
			// If he is a picker, he picks up items from ground
			if(this.isPicker)
			{
				checkGround();
			}
			// If he is a transporter, he picks up items from source chest
			if(this.isTransporter && this.sourcePos != null && this.getHeldItemMainhand() == null)
			{
				checkSourceChest();
			}
			
			// Not more then 15 seconds standing around
			if(idleTime > 300)
			{
				idleTime = 0;
				if(this.getHeldItemMainhand() != null)
				this.dropItem(this.getHeldItemMainhand().getItem(), this.getHeldItemMainhand().stackSize);
				this.setHeldItem(EnumHand.MAIN_HAND, null);
				moveZombieTo(inventoryPos);
			}
		}
		
		if(this.getHeldItemMainhand() != null)
		{
			this.setArmsRaised(true);
		}
		else
		{
			this.setArmsRaised(false);
		}
		
    	super.onUpdate();
    }

	private IFluidHandler getFluidHandler(IBlockAccess world, BlockPos pos) {
		return CapabilityUtils.getCapability(world.getTileEntity(pos), CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
	}
	
	public void checkSourceChest()
	{
		TileEntity tileEntity = worldObj.getTileEntity(sourcePos);
		int slot = -1;
		
		if(tileEntity != null)
		{
			if(tileEntity instanceof TileEntityChest)
			{
				TileEntityChest chest = (TileEntityChest) tileEntity;
				for(int i = 0; i < chest.getSizeInventory(); i++)
				{
					if(chest.getStackInSlot(i) != null)
					{
						slot = i;
						break;
					}
				}
			}
		}

		BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
		if(!this.sourcePos.equals(pos))
		{
			if(slot != -1)
			{
				moveZombieTo(this.sourcePos);
			}
		}
		else
		{
			if(slot != - 1)
			{
				if(this.getHeldItemMainhand() == null)
				{
					TileEntityChest chest = (TileEntityChest) tileEntity;
					this.setHeldItem(EnumHand.MAIN_HAND, chest.getStackInSlot(slot));
					chest.setInventorySlotContents(slot, null);
					slot = -1;
					moveZombieTo(inventoryPos);
				}
				else
				{
					moveZombieTo(inventoryPos);
				}
			}
		}
	}

	public void checkMachines()
	{
    	BlockPos pos = new BlockPos(this.posX, this.posY - 1, this.posZ);
		if(this.inventoryPos.equals(pos))
		{
			if(this.getHeldItemMainhand() != null)
			{
				IFluidHandler fluidHandler = getFluidHandler(worldObj, this.inventoryPos);
				TileEntity tileEntity = worldObj.getTileEntity(inventoryPos);
				
				ItemStack heldStack = this.getHeldItemMainhand();
				Item heldItem = heldStack.getItem();
				
				if(tileEntity != null)
				{
					if(tileEntity instanceof TileEntityFarmCrops)
					{
						TileEntityFarmCrops te = (TileEntityFarmCrops) tileEntity;
						if (heldStack.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0)
						{
							// The fluid in bucket is liquid magic
							if (FluidUtil.getFluidContained(heldStack).containsFluid(new FluidStack(thewizardmod.fluids.StartupCommon.fluidMagic, 1000)))
							{
								if(te.getStackInSlot(0) == null)
								{
									te.setInventorySlotContents(0, heldStack);
									this.setHeldItem(EnumHand.MAIN_HAND, null);
									te.markDirty();
								}
							}
						}
						else if(heldItem == Items.BUCKET)
						{
							if(te.getStackInSlot(0) == null)
							{
								te.setInventorySlotContents(0, heldStack);
								this.setHeldItem(EnumHand.MAIN_HAND, null);
								te.markDirty();
							}
							else if(te.getStackInSlot(0).stackSize < 16)
							{
								int size = te.getStackInSlot(0).stackSize + heldStack.stackSize;
								te.setInventorySlotContents(0, new ItemStack(heldItem, size));
								this.setHeldItem(EnumHand.MAIN_HAND, null);
								te.markDirty();
							}
						}
					}
					if(tileEntity instanceof TileEntityFarmFeeder)
					{
						TileEntityFarmFeeder te = (TileEntityFarmFeeder) tileEntity;
						if (heldStack.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0)
						{
							// The fluid in bucket is liquid magic
							if (FluidUtil.getFluidContained(heldStack).containsFluid(new FluidStack(thewizardmod.fluids.StartupCommon.fluidMagic, 1000)))
							{
								if(te.getStackInSlot(0) == null)
								{
									te.setInventorySlotContents(0, heldStack);
									this.setHeldItem(EnumHand.MAIN_HAND, null);
									te.markDirty();
								}
							}
						}
						else if(heldItem == Items.BUCKET)
						{
							if(te.getStackInSlot(0) == null)
							{
								te.setInventorySlotContents(0, heldStack);
								this.setHeldItem(EnumHand.MAIN_HAND, null);
								te.markDirty();
							}
							else if(te.getStackInSlot(0).stackSize < 16)
							{
								int size = te.getStackInSlot(0).stackSize + heldStack.stackSize;
								te.setInventorySlotContents(0, new ItemStack(heldItem, size));
								this.setHeldItem(EnumHand.MAIN_HAND, null);
								te.markDirty();
							}
						}
					}
					if(tileEntity instanceof TileEntityTank)
					{
						TileEntityTank te = (TileEntityTank) tileEntity;
						if (heldStack.getUnlocalizedName().compareTo("item.forge.bucketFilled") == 0)
						{
							// The fluid in bucket is liquid magic
							if (FluidUtil.getFluidContained(heldStack).containsFluid(new FluidStack(thewizardmod.fluids.StartupCommon.fluidMagic, 1000)))
							{
								if(te.getStackInSlot(0) == null)
								{
									te.setInventorySlotContents(0, heldStack);
									this.setHeldItem(EnumHand.MAIN_HAND, null);
									te.markDirty();
								}
							}
						}
						else if(heldItem == Items.BUCKET)
						{
							if(te.getStackInSlot(0) == null)
							{
								te.setInventorySlotContents(0, heldStack);
								this.setHeldItem(EnumHand.MAIN_HAND, null);
								te.markDirty();
							}
							else if(te.getStackInSlot(0).stackSize < 16)
							{
								int size = te.getStackInSlot(0).stackSize + heldStack.stackSize;
								te.setInventorySlotContents(0, new ItemStack(heldItem, size));
								this.setHeldItem(EnumHand.MAIN_HAND, null);
								te.markDirty();
							}
						}
					}
				}
			}
		}
	}
	
    public void checkInventory()
    {
    	BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
		if(this.inventoryPos.equals(pos))
		{
			if(this.getHeldItem(EnumHand.MAIN_HAND) != null)
			{
				TileEntity tileentiy = worldObj.getTileEntity(inventoryPos);
				
				// Tileentity with inventory is a standard minecraft chest
				if(tileentiy != null && tileentiy instanceof TileEntityChest)
				{
					TileEntityChest chest = (TileEntityChest) tileentiy;
					ItemStack itemStack = this.getHeldItem(EnumHand.MAIN_HAND);
					Item heldItem = itemStack.getItem();
					
					
					int itemsLeft = itemStack.stackSize;
					
					for(int i = 0; i < chest.getSizeInventory(); i++)
					{
						// check if there is something in the chest slot
						ItemStack chestStack = chest.getStackInSlot(i);
						if(chestStack != null && itemStack != null)
						{
							// check if in the chest slot is same item as in hand
							Item chestItem = chestStack.getItem();
							if(heldItem == chestItem)
							{
								// Get space in chest slot
								int maxItems = chest.getInventoryStackLimit() - chestStack.stackSize;
								if(maxItems >= itemsLeft)
								{
									chestStack.stackSize += itemsLeft;
									chest.setInventorySlotContents(i, new ItemStack(itemStack.getItem(), chestStack.stackSize));
									this.setHeldItem(EnumHand.MAIN_HAND, null);	
									break;
								}
								
								else
								{
									chestStack.stackSize = chest.getInventoryStackLimit();
									chest.setInventorySlotContents(i, new ItemStack(itemStack.getItem(), chestStack.stackSize));
									itemsLeft -= maxItems;
									if(itemsLeft > 0)
									{
										this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(heldItem, itemsLeft));
									}
									else
									{
										this.setHeldItem(EnumHand.MAIN_HAND, null);	
										break;
									}
									
								}
								
							}
						}
						else if(chestStack == null && itemStack != null)
						{
							chest.setInventorySlotContents(i, new ItemStack(itemStack.getItem(), itemsLeft));
							this.setHeldItem(EnumHand.MAIN_HAND, null);	
							break;
						}
					}
				}

			}
		}
		else if(!this.isMoving)
		{
			moveZombieTo(this.inventoryPos);
		}
    }
    
    public void tryToPickup()
    {
		if (!worldObj.isRemote)
		{
			for (Object obj : worldObj.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(posX - 1, posY - 1, posZ - 1, posX + 1, posY + 1, posZ + 1))) 
			{
				if (obj instanceof EntityItem) 
				{
					EntityItem item = (EntityItem) obj;
					ItemStack pickupItemStack = item.getEntityItem();
					Item pickupItem = pickupItemStack.getItem();
					
					if(this.getHeldItem(EnumHand.MAIN_HAND) == null)
					{
						this.setHeldItem(EnumHand.MAIN_HAND, pickupItemStack);
						item.setDead();
					}
				}
			}
		}
    	
    }

    public void checkGround()
    {
		if (!worldObj.isRemote)
		{
			for (Object obj : worldObj.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(posX - maxDistance, posY - maxDistance, posZ - maxDistance, posX + maxDistance, posY + maxDistance, posZ + maxDistance))) 
			{
				if (obj instanceof EntityItem) 
				{
					EntityItem item = (EntityItem) obj;
					if(this.getHeldItem(EnumHand.MAIN_HAND) == null)
					{
						moveZombieTo(item.getPosition());
					}
					else
					{
						moveZombieTo(item.getPosition());
						idleTime = 0;
						moveZombieTo(this.inventoryPos);
					}
				}
			}
		}
    	
    }

	public void clearTasks()
	{
		if(!this.targetTasks.taskEntries.isEmpty())
		{
			for (EntityAITaskEntry t : this.targetTasks.taskEntries) 
			{
				this.targetTasks.removeTask(t.action);
			}
		}
	}
	
	public void clearProfession()
	{
		this.isPicker = false;
		this.isTransporter = false;
	}
	
	public void setPicker()
	{
		this.isPicker = true;
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(StartupCommon.itemPickerChestplate));
	}
	
	public void setTransporter()
	{
		this.isTransporter = true;
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(StartupCommon.itemTransporterChestplate));
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setInventoryPos(BlockPos pos)
	{
		this.inventoryPos = pos;
		idleTime = 0;
		moveZombieTo(inventoryPos);
	}

	public void setSourcePos(BlockPos pos)
	{
		this.sourcePos = pos;
		idleTime = 0;
		moveZombieTo(inventoryPos);
	}

	public void moveZombieTo(BlockPos pos) 
	{
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();

		this.destinationPos = pos;
		this.isMoving = true;
		// first clear the task list
		clearTasks();
		// add task
		this.targetTasks.addTask(0, new EntityAIMiniZombieMoveTo(this, 1.0D, 10, posX, posY, posZ));
	}

	private boolean checkDestinationPosition()
	{
		if(!worldObj.isRemote)
		{
			BlockPos pos = new BlockPos(this.posX, this.posY - 1, this.posZ);
			if(pos.equals(this.destinationPos))
			{
				return true;
			}
		}
		return false;
	}
}