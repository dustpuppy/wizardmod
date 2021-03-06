package thewizardmod.plants;

import thewizardmod.teleporter.ItemNBTAnimate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
	public static WheatCrop wheatCrop;
	public static ItemSeeds wheatSeeds;
	
	public static Wheat wheat;

  public static void preInitCommon() {
	  wheatCrop = new WheatCrop();
	  wheatCrop.setUnlocalizedName("twm_wheat_crop");
	  wheatCrop.setRegistryName("wheatCrop");
	  GameRegistry.register(wheatCrop);
	  
	  wheatSeeds = new WheatSeeds(wheatCrop, null);
	  wheatSeeds.setUnlocalizedName("twm_weed_seeds");
	  wheatSeeds.setRegistryName("wheat_seeds");
	  GameRegistry.register(wheatSeeds);
	  
	  wheat = new Wheat();
	  wheat.setUnlocalizedName("twm_wheat");
	  wheat.setRegistryName("wheat");
	  GameRegistry.register(wheat);

	  MinecraftForge.addGrassSeed(new ItemStack(wheatSeeds), 5);
  }


  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
