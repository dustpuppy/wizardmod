package thewizardmod.runes;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;


	    ModelResourceLocation ResourceLocation = new ModelResourceLocation("thewizardmod:runes/fehu", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.fehu, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/uruz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.uruz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/thurisaz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.thurisaz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/ansuz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ansuz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/raido", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.raido, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/kaunan", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.kaunan, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/gibo", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.gibo, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/wunjo", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.wunjo, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/haglaz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.haglaz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/naudiz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.naudiz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/eisa", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.eisa, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/jeran", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.jeran, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/ihwaz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ihwaz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/pertho", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.pertho, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/algiz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.algiz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/sowulo", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.sowulo, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/teiwaz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.teiwaz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/berko", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.berko, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/ehwaz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ehwaz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/mann", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.mann, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/laguz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.laguz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/ingwaz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.ingwaz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/othalan", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.othalan, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/dagaz", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.dagaz, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

	    ResourceLocation = new ModelResourceLocation("thewizardmod:runes/blank", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.blank, DEFAULT_ITEM_SUBTYPE, ResourceLocation);

  }

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
