package thewizardmod;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TheWizardMod.MODID, version = TheWizardMod.VERSION, guiFactory = TheWizardMod.GUIFACTORY)
public class TheWizardMod
{
  // you also need to update the modid and version in two other places as well:
  //  build.gradle file (the version, group, and archivesBaseName parameters)
  //  resources/mcmod.info (the name, description, and version parameters)
   public static final String MODID = "thewizardmod";
    public static final String VERSION = "1.0a";

    public static final String GUIFACTORY = "thewizardmod.configuration.ModGuiFactory";

    // The instance of your mod that Forge uses.  Optional.
    @Instance(MODID)
    public static TheWizardMod instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="thewizardmod.ClientOnlyProxy", serverSide="thewizardmod.DedicatedServerProxy")
    public static CommonProxy proxy;

	public static final int GUI_ID_SMALL_BACKPACK = 0;
	public static final int GUI_ID_NORMAL_BACKPACK = 1;
	public static final int GUI_ID_BIG_BACKPACK = 2;
	public static final int GUI_ID_MAGIC_CHEST = 3;
	public static final int GUI_ID_MAGICINJECTOR = 4;


	static {
	    FluidRegistry.enableUniversalBucket();
	}
	
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

      proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
      proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
      proxy.postInit();
    }

    public static String prependModID(String name) {return MODID + ":" + name;}
}
