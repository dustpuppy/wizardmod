package thewizardmod.dimensions;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thewizardmod.TheWizardMod;

public class StartupCommon
{
	  public static PortalBlock portalBlock;
	  public static ItemBlock itemPortalBlock;

	  public static MagicFire magicFire;
	  public static ItemBlock itemFire;

//	  public static MagicFlint magicFlint;

	  public static PortalFrame portalFrame;
	  public static ItemBlock itemPortalFrame;
	  
  public static void preInitCommon()
  {
	  TheWizardMod.logger.info("Setting up Magic Dimension");

	  
	  portalBlock = (PortalBlock)(new PortalBlock().setUnlocalizedName("twm_portal"));
	  portalBlock.setRegistryName("portal");
	  GameRegistry.register(portalBlock);

	  magicFire = (MagicFire)(new MagicFire().setUnlocalizedName("twm_fire"));
	  magicFire.setRegistryName("fire");
	  GameRegistry.register(magicFire);

	  itemFire = new ItemBlock(magicFire);
	  itemFire.setRegistryName(magicFire.getRegistryName());
	  GameRegistry.register(itemFire);

//	  magicFlint = (MagicFlint)(new MagicFlint().setUnlocalizedName("twm_flint_and_steel_unlocalised_name"));
//	  magicFlint.setRegistryName("flint_and_steel_registry_name");
//	  GameRegistry.register(magicFlint);

	  portalFrame = (PortalFrame)(new PortalFrame().setUnlocalizedName("twm_portal_frame"));
	  portalFrame.setRegistryName("portalFrame");
	  GameRegistry.register(portalFrame);

	  itemPortalFrame = new ItemBlock(portalFrame);
	  itemPortalFrame.setRegistryName(portalFrame.getRegistryName());
	  GameRegistry.register(itemPortalFrame);

	  

	  GameRegistry.registerTileEntity(TileEntityDim.class, "entityDim");
	  ModDimensions.init();
	    
  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
