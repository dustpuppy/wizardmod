package thewizardmod.books;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
	    final int DEFAULT_ITEM_SUBTYPE = 0;

	    ModelResourceLocation bookResourceLocation = new ModelResourceLocation("thewizardmod:wizard_guide_book", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.wizardGuide, DEFAULT_ITEM_SUBTYPE, bookResourceLocation);
  
	    bookResourceLocation = new ModelResourceLocation("thewizardmod:rune_magic_book", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.runeMagic, DEFAULT_ITEM_SUBTYPE, bookResourceLocation);

	    bookResourceLocation = new ModelResourceLocation("thewizardmod:altar_magic_book", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.altar, DEFAULT_ITEM_SUBTYPE, bookResourceLocation);

	    bookResourceLocation = new ModelResourceLocation("thewizardmod:wand_magic_book", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.bookWands, DEFAULT_ITEM_SUBTYPE, bookResourceLocation);

	    bookResourceLocation = new ModelResourceLocation("thewizardmod:injector_magic_book", "inventory");
	    ModelLoader.setCustomModelResourceLocation(StartupCommon.injector, DEFAULT_ITEM_SUBTYPE, bookResourceLocation);
}

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
