package thewizardmod.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.ILootContainer;
import thewizardmod.TheWizardMod;

public class EntityMiniZombie extends EntityCreature implements INpc{

    public static final ResourceLocation deathLootTable = new ResourceLocation(TheWizardMod.MODID, "/loot_table/entities/mini_zombie.json");

    public EntityMiniZombie(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20F);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
    }


    public void setTarget(int x, int y, int z)
    {
        this.targetTasks.addTask(0, new EntityAIMiniZombieMoveTo(this, 1.0D, 10, x, y, z));
    }
    

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    public void applyEntityAI() {
//        this.targetTasks.addTask(0, new EntityAIMiniZombieMoveTo(this, 1.0D, 10, 2151, 3, 1900));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable() {
        return deathLootTable;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 5;
    }

    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player,	Vec3d vec, @Nullable ItemStack stack, EnumHand hand) {
    	System.out.println(stack + " " + hand);
    	return super.applyPlayerInteraction(player, vec, stack, hand);
    }

    
}