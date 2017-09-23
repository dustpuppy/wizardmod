package thewizardmod.books;

import thewizardmod.TheWizardMod;
import thewizardmod.books.BookWizardsGuide;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon
{
	  public static BookWizardsGuide wizardGuide;
	  public static BookRuneMagic runeMagic;
	  public static BookAltar altar;
	  public static BookWand bookWands;
	  public static BookMachines injector;

  public static void preInitCommon()
  {
	  TheWizardMod.logger.info("Inserting books");

	  
	  wizardGuide = (BookWizardsGuide)(new BookWizardsGuide().setUnlocalizedName("wizard_guide_twm"));
	  wizardGuide.setRegistryName("wizard_guide_registry_name_twm");
	  GameRegistry.register(wizardGuide);
  
	  runeMagic = (BookRuneMagic)(new BookRuneMagic().setUnlocalizedName("rune_magic_twm"));
	  runeMagic.setRegistryName("rune_magic_registry_name_twm");
	  GameRegistry.register(runeMagic);
  
	  altar = (BookAltar)(new BookAltar().setUnlocalizedName("altar_magic_twm"));
	  altar.setRegistryName("altar_magic_registry_name_twm");
	  GameRegistry.register(altar);
  
	  bookWands = (BookWand)(new BookWand().setUnlocalizedName("wand_magic_twm"));
	  bookWands.setRegistryName("wand_magic_registry_name_twm");
	  GameRegistry.register(bookWands);
  
	  injector = (BookMachines)(new BookMachines().setUnlocalizedName("injector_magic_twm"));
	  injector.setRegistryName("injector_magic_registry_name_twm");
	  GameRegistry.register(injector);
  
  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }
}
