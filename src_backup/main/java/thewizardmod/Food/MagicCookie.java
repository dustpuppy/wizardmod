package thewizardmod.Food;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class MagicCookie extends ItemFood
{

	public MagicCookie(int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		
	    this.setCreativeTab(CreativeTabs.FOOD);
	    
	    setAlwaysEdible();
	}


	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 300));
	}
}
