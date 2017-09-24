package thewizardmod.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;

	    ModelResourceLocation shadowdustResourceLocation = new ModelResourceLocation("thewizardmod:shadowdust", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.shadowDust, DEFAULT_ITEM_SUBTYPE, shadowdustResourceLocation);

	    ModelResourceLocation heartResourceLocation = new ModelResourceLocation("thewizardmod:heart_of_undead", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.heart, DEFAULT_ITEM_SUBTYPE, heartResourceLocation);

	    ModelResourceLocation gemResourceLocation = new ModelResourceLocation("thewizardmod:magic_gem", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.magicGem, DEFAULT_ITEM_SUBTYPE, gemResourceLocation);

	    ModelResourceLocation ironResourceLocation = new ModelResourceLocation("thewizardmod:magic_iron", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.magicIron, DEFAULT_ITEM_SUBTYPE, ironResourceLocation);

	    ModelResourceLocation ironSwordResourceLocation = new ModelResourceLocation("thewizardmod:iron_sword", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironSword, DEFAULT_ITEM_SUBTYPE, ironSwordResourceLocation);
  
	    ModelResourceLocation ironPickaxeResourceLocation = new ModelResourceLocation("thewizardmod:iron_pickaxe", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironPickaxe, DEFAULT_ITEM_SUBTYPE, ironPickaxeResourceLocation);
  
	    ModelResourceLocation ironAxeResourceLocation = new ModelResourceLocation("thewizardmod:iron_axe", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironAxe, DEFAULT_ITEM_SUBTYPE, ironAxeResourceLocation);
  
	    ModelResourceLocation ironShovelResourceLocation = new ModelResourceLocation("thewizardmod:iron_shovel", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironShovel, DEFAULT_ITEM_SUBTYPE, ironShovelResourceLocation);
	    
	    ModelResourceLocation ironHoeResourceLocation = new ModelResourceLocation("thewizardmod:iron_hoe", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironHoe, DEFAULT_ITEM_SUBTYPE, ironHoeResourceLocation);
  
	    
	    ModelResourceLocation ironHelmetResourceLocation = new ModelResourceLocation("thewizardmod:iron_helmet", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironHelmet, DEFAULT_ITEM_SUBTYPE, ironHelmetResourceLocation);
  
	    
	    ModelResourceLocation ironChestplateResourceLocation = new ModelResourceLocation("thewizardmod:iron_chestplate", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironChestplate, DEFAULT_ITEM_SUBTYPE, ironChestplateResourceLocation);
  
	    
	    ModelResourceLocation ironLeggingsResourceLocation = new ModelResourceLocation("thewizardmod:iron_leggings", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironLeggings, DEFAULT_ITEM_SUBTYPE, ironLeggingsResourceLocation);
  
	    
	    ModelResourceLocation ironBootsResourceLocation = new ModelResourceLocation("thewizardmod:iron_boots", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ironBoots, DEFAULT_ITEM_SUBTYPE, ironBootsResourceLocation);
  
	    ModelResourceLocation magicStringResourceLocation = new ModelResourceLocation("thewizardmod:magicString", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.magicString, DEFAULT_ITEM_SUBTYPE, magicStringResourceLocation);
  
	    ModelResourceLocation robeHelmetResourceLocation = new ModelResourceLocation("thewizardmod:robe_helmet", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.robeHelmet, DEFAULT_ITEM_SUBTYPE, robeHelmetResourceLocation);
  
	    
	    ModelResourceLocation robeChestplateResourceLocation = new ModelResourceLocation("thewizardmod:robe_chestplate", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.robeChestplate, DEFAULT_ITEM_SUBTYPE, robeChestplateResourceLocation);
  
	    
	    ModelResourceLocation robeLeggingsResourceLocation = new ModelResourceLocation("thewizardmod:robe_leggings", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.robeLeggings, DEFAULT_ITEM_SUBTYPE, robeLeggingsResourceLocation);
  
	    
	    ModelResourceLocation robeBootsResourceLocation = new ModelResourceLocation("thewizardmod:robe_boots", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.robeBoots, DEFAULT_ITEM_SUBTYPE, robeBootsResourceLocation);
  
	    ModelResourceLocation boneResourceLocation = new ModelResourceLocation("thewizardmod:bone", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.bone, DEFAULT_ITEM_SUBTYPE, boneResourceLocation);

	    ModelResourceLocation zombieResourceLocation = new ModelResourceLocation("thewizardmod:zombie", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.zombie, DEFAULT_ITEM_SUBTYPE, zombieResourceLocation);

  }

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
