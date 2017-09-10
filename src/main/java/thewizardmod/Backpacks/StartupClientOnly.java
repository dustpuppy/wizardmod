package thewizardmod.Backpacks;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import thewizardmod.Backpacks.StartupCommon;

public class StartupClientOnly
{
	public static void preInitClientOnly()
	{
	    final int DEFAULT_ITEM_SUBTYPE = 0;
	    ModelResourceLocation small_backpack_itemModelResourceLocation = new ModelResourceLocation("thewizardmod:small_backpack", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.smallBackpack, DEFAULT_ITEM_SUBTYPE, small_backpack_itemModelResourceLocation);

	    ModelResourceLocation normal_backpack_itemModelResourceLocation = new ModelResourceLocation("thewizardmod:normal_backpack", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.normalBackpack, DEFAULT_ITEM_SUBTYPE, normal_backpack_itemModelResourceLocation);

	    ModelResourceLocation big_backpack_itemModelResourceLocation = new ModelResourceLocation("thewizardmod:big_backpack", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.bigBackpack, DEFAULT_ITEM_SUBTYPE, big_backpack_itemModelResourceLocation);
	}

	public static void initClientOnly()
	{

	}

	public static void postInitClientOnly()
	{
	}
}
