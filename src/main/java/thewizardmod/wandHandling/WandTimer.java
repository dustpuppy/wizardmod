package thewizardmod.wandHandling;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import thewizardmod.Wands.ItemWoodenWand;

public class WandTimer implements IItemPropertyGetter {

  @Override
    public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
    {
      final float IDLE_FRAME_INDEX = 0.0F;
      final float FULLY_CHARGED_INDEX = 1.0F;

      if (worldIn == null && entityIn != null)  {
        worldIn = entityIn.worldObj;
      }

      if (entityIn == null || worldIn == null) return IDLE_FRAME_INDEX;
      if (!entityIn.isHandActive()) {  // player isn't holding down the right mouse button, i.e. not charging
        animationHasStarted = false;
        return IDLE_FRAME_INDEX;
      }

      long worldTicks = worldIn.getTotalWorldTime();
      if (!animationHasStarted) {
        startingTick = worldTicks;
        animationHasStarted = true;
      }
      final long ticksInUse = worldTicks - startingTick;
      if (ticksInUse <= ItemWoodenWand.CHARGE_UP_INITIAL_PAUSE_TICKS) {
        return IDLE_FRAME_INDEX;
      }

      final long chargeTicksSoFar = ticksInUse - ItemWoodenWand.CHARGE_UP_INITIAL_PAUSE_TICKS;
      final double fractionCharged = chargeTicksSoFar/(double)ItemWoodenWand.CHARGE_UP_DURATION_TICKS;
      if (fractionCharged < 0.0) return IDLE_FRAME_INDEX;
      if (fractionCharged > FULLY_CHARGED_INDEX) return FULLY_CHARGED_INDEX;

      return (float)fractionCharged*FULLY_CHARGED_INDEX;
  }

  private long startingTick = -1;
  private boolean animationHasStarted = false;
}
