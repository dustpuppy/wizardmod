package thewizardmod;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class Materials {

	public static ToolMaterial magicIronToolMaterial = EnumHelper.addToolMaterial("MagicIron", 2, 500, 8.0F, 8.0F, 14);
	
	public static ArmorMaterial magicIronArmorMaterial = EnumHelper.addArmorMaterial("MagicIron", "", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static ArmorMaterial magicRobeMaterial = EnumHelper.addArmorMaterial("MagicRobe", "", 5, new int[]{1, 3, 3, 1}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
}
