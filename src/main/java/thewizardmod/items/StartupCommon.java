package thewizardmod.items;

import thewizardmod.Food.Cherry;
import thewizardmod.books.BookWizardsGuide;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
	  public static ShadowDust shadowDust;
	  public static Heart_of_undead heart;
	  public static MagicGem magicGem;
	  public static MagicIronIngot magicIron;
	  
	  public static MagicIronSword ironSword;
	  public static MagicIronPickaxe ironPickaxe;
	  public static MagicIronAxe ironAxe;
	  public static MagicIronShovel ironShovel;
	  public static MagicIronHoe ironHoe;
	  
	  public static MagicIronArmor ironHelmet;
	  public static MagicIronArmor ironChestplate;
	  public static MagicIronArmor ironLeggings;
	  public static MagicIronArmor ironBoots;

	  public static MagicString magicString;
	  
	  public static MagicRobe robeHelmet;
	  public static MagicRobe robeChestplate;
	  public static MagicRobe robeLeggings;
	  public static MagicRobe robeBoots;
	  
	  public static Bone bone;
	  public static ItemPickerArmor itemPickerChestplate;
	  public static ItemTransporterArmor itemTransporterChestplate;
	  public static Zombie zombie;

  public static void preInitCommon()
  {
		shadowDust = (ShadowDust)(new ShadowDust().setUnlocalizedName("twm_shadowdust"));
		shadowDust.setRegistryName("shadowdust_registry_name");
	    GameRegistry.register(shadowDust);

	    heart = (Heart_of_undead)(new Heart_of_undead().setUnlocalizedName("twm_heart_of_undead"));
	    heart.setRegistryName("heart_of_undead_registry_name");
	    GameRegistry.register(heart);

	    magicGem = (MagicGem)(new MagicGem().setUnlocalizedName("twm_magic_gem"));
	    magicGem.setRegistryName("magic_gem_registry_name");
	    GameRegistry.register(magicGem);

	    magicIron = (MagicIronIngot)(new MagicIronIngot().setUnlocalizedName("twm_magic_iron"));
	    magicIron.setRegistryName("magic_iron_registry_name");
	    GameRegistry.register(magicIron);

	    ironSword = (MagicIronSword)(new MagicIronSword().setUnlocalizedName("twm_magic_iron_sword"));
	    ironSword.setRegistryName("magic_iron_sword_registry_name");
	    GameRegistry.register(ironSword);

	    ironPickaxe = (MagicIronPickaxe)(new MagicIronPickaxe().setUnlocalizedName("twm_magic_iron_pickaxe"));
	    ironPickaxe.setRegistryName("magic_iron_pickaxe_registry_name");
	    GameRegistry.register(ironPickaxe);

	    ironAxe = (MagicIronAxe)(new MagicIronAxe().setUnlocalizedName("twm_magic_iron_axe"));
	    ironAxe.setRegistryName("magic_iron_axe_registry_name");
	    GameRegistry.register(ironAxe);

	    ironShovel = (MagicIronShovel)(new MagicIronShovel().setUnlocalizedName("twm_magic_iron_shovel"));
	    ironShovel.setRegistryName("magic_iron_shovel_registry_name");
	    GameRegistry.register(ironShovel);

	    ironHoe = (MagicIronHoe)(new MagicIronHoe().setUnlocalizedName("twm_magic_iron_hoe"));
	    ironHoe.setRegistryName("magic_iron_hoe_registry_name");
	    GameRegistry.register(ironHoe);

	    ironHelmet = (MagicIronArmor)(new MagicIronArmor(thewizardmod.Materials.magicIronArmorMaterial, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("twm_magic_iron_helmet"));
	    ironHelmet.setRegistryName("magic_iron_helmet_registry_name");
	    GameRegistry.register(ironHelmet);

	    ironChestplate = (MagicIronArmor)(new MagicIronArmor(thewizardmod.Materials.magicIronArmorMaterial, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("twm_magic_iron_chestplate"));
	    ironChestplate.setRegistryName("magic_iron_chestplate_registry_name");
	    GameRegistry.register(ironChestplate);

	    ironLeggings = (MagicIronArmor)(new MagicIronArmor(thewizardmod.Materials.magicIronArmorMaterial, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("twm_magic_iron_leggings"));
	    ironLeggings.setRegistryName("magic_iron_leggings_registry_name");
	    GameRegistry.register(ironLeggings);

	    ironBoots = (MagicIronArmor)(new MagicIronArmor(thewizardmod.Materials.magicIronArmorMaterial, 3, EntityEquipmentSlot.FEET).setUnlocalizedName("twm_magic_iron_boots"));
	    ironBoots.setRegistryName("magic_iron_boots_registry_name");
	    GameRegistry.register(ironBoots);

	    magicString = (MagicString)(new MagicString().setUnlocalizedName("twm_magic_string"));
	    magicString.setRegistryName("magic_string_registry_name");
	    GameRegistry.register(magicString);

	    robeHelmet = (MagicRobe)(new MagicRobe(thewizardmod.Materials.magicRobeMaterial, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("twm_magic_robe_helmet"));
	    robeHelmet.setRegistryName("magic_iron_robe_registry_name");
	    GameRegistry.register(robeHelmet);

	    robeChestplate = (MagicRobe)(new MagicRobe(thewizardmod.Materials.magicRobeMaterial, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("twm_magic_robe_chestplate"));
	    robeChestplate.setRegistryName("magic_robe_chestplate_registry_name");
	    GameRegistry.register(robeChestplate);

	    robeLeggings = (MagicRobe)(new MagicRobe(thewizardmod.Materials.magicRobeMaterial, 2, EntityEquipmentSlot.LEGS).setUnlocalizedName("twm_magic_robe_leggings"));
	    robeLeggings.setRegistryName("magic_robe_leggings_registry_name");
	    GameRegistry.register(robeLeggings);

	    robeBoots = (MagicRobe)(new MagicRobe(thewizardmod.Materials.magicRobeMaterial, 3, EntityEquipmentSlot.FEET).setUnlocalizedName("twm_magic_robe_boots"));
	    robeBoots.setRegistryName("magic_robe_boots_registry_name");
	    GameRegistry.register(robeBoots);


	    
	    
	    bone = (Bone)(new Bone().setUnlocalizedName("twm_bone"));
	    bone.setRegistryName("bone_registry_name");
	    GameRegistry.register(bone);
  
	    itemPickerChestplate = (ItemPickerArmor)(new ItemPickerArmor(thewizardmod.Materials.magicIronArmorMaterial, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("twm_item_picker_chestplate"));
	    itemTransporterChestplate = (ItemTransporterArmor)(new ItemTransporterArmor(thewizardmod.Materials.magicIronArmorMaterial, 1, EntityEquipmentSlot.CHEST).setUnlocalizedName("twm_item_transporter_chestplate"));

	    zombie = (Zombie)(new Zombie().setUnlocalizedName("twm_zombie"));
	    zombie.setRegistryName("zombie_registry_name");
	    GameRegistry.register(zombie);

  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }
}
