package thewizardmod.events;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thewizardmod.entity.EntityWeirdZombie;
import thewizardmod.items.StartupCommon;

public class MobDropEvents {
	
	 public static Random random;
	 public static int amount_of_drops;
	 public static int chance_of_drop;
	 
	 @SubscribeEvent
	 public void onEntityDrop(LivingDropsEvent event) {
		 random = new Random();
		 
		 
		 // Custom zombie will possible drop a heart of an undead
		 if(event.getEntityLiving() instanceof EntityWeirdZombie) {
			 amount_of_drops = 1;
			 chance_of_drop = 50;
			 if(random.nextInt(chance_of_drop) == 0)
			 {
				 event.getEntityLiving().entityDropItem(new ItemStack(StartupCommon.heart), amount_of_drops);
			 }
		 }
	 
	 }
	
}
