package thewizardmod.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MagicIronArmor extends ItemArmor{

	public MagicIronArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		if(slot == EntityEquipmentSlot.HEAD || slot == EntityEquipmentSlot.CHEST || slot == EntityEquipmentSlot.FEET)
		{
			return "thewizardmod:textures/models/armor/magic_iron_layer_1.png";
		}
		else if(slot == EntityEquipmentSlot.LEGS)
		{
			return "thewizardmod:textures/models/armor/magic_iron_layer_2.png";
		}
		return null;
	}

}
