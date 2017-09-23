package thewizardmod;

import net.minecraft.entity.player.EntityPlayer;

/**
 * CommonProxy is used to set up the mod and start it running.  It contains all the code that should run on both the
 *   Standalone client and the dedicated server.
 *   For more background information see here http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
 */
public abstract class CommonProxy {

  /**
   * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
   */
  public void preInit()
  {
	   //read config first
	thewizardmod.configuration.StartupCommon.preInitCommon();


//	thewizardmod.potions.StartupCommon.preInitCommon();
	thewizardmod.teleporter.StartupCommon.preInitCommon();
	thewizardmod.chest.StartupCommon.preInitCommon();

	thewizardmod.Wands.StartupCommon.preInitCommon();
	thewizardmod.lightBlock.StartupCommon.preInitCommon();
	thewizardmod.torch.StartupCommon.preInitCommon();

	thewizardmod.Tree.StartupCommon.preInitCommon();
	thewizardmod.Trees.StartupCommon.preInitCommon();
	thewizardmod.plants.StartupCommon.preInitCommon();

	thewizardmod.ores.StartupCommon.preInitCommon();
    thewizardmod.items.StartupCommon.preInitCommon();
    thewizardmod.fluids.StartupCommon.preInitCommon();

    thewizardmod.camouflageBlock.StartupCommon.preInitCommon();

    thewizardmod.magicInjector.StartupCommon.preInitCommon();
    thewizardmod.extractor.StartupCommon.preInitCommon();
    thewizardmod.FarmCrops.StartupCommon.preInitCommon();
    thewizardmod.FarmFeeder.StartupCommon.preInitCommon();
//    thewizardmod.pipe.StartupCommon.preInitCommon();

    thewizardmod.Backpacks.StartupCommon.preInitCommon();
    thewizardmod.Food.StartupCommon.preInitCommon();
    thewizardmod.Jar_undead_heart.StartupCommon.preInitCommon();
    thewizardmod.fireChalice.StartupCommon.preInitCommon();
    thewizardmod.books.StartupCommon.preInitCommon();
    thewizardmod.mirror.StartupCommon.preInitCommon();
    
    thewizardmod.altar.StartupCommon.preInitCommon();
    thewizardmod.pedestral.StartupCommon.preInitCommon();
    thewizardmod.runeSlab.StartupCommon.preInitCommon();
    thewizardmod.runes.StartupCommon.preInitCommon();

    thewizardmod.recipes.StartupCommon.preInitCommon();

    thewizardmod.Biomes.StartupCommon.preInitCommon();
    thewizardmod.World.StartupCommon.preInitCommon();

	thewizardmod.creative_tab.StartupCommon.preInitCommon();
	thewizardmod.events.StartupCommon.preInitCommon();		// useless
	thewizardmod.Gui.StartupCommon.preInitCommon();

	thewizardmod.entity.StartupCommon.preInitCommon();

	thewizardmod.dimensions.StartupCommon.preInitCommon();

}

  /**
   * Do your mod setup. Build whatever data structures you care about. Register recipes,
   * send FMLInterModComms messages to other mods.
   */
  public void init()
  {
	thewizardmod.configuration.StartupCommon.initCommon();
	  

//	thewizardmod.potions.StartupCommon.initCommon();
	thewizardmod.teleporter.StartupCommon.initCommon();
	thewizardmod.chest.StartupCommon.initCommon();

	thewizardmod.Wands.StartupCommon.initCommon();
	thewizardmod.lightBlock.StartupCommon.initCommon();
	thewizardmod.torch.StartupCommon.initCommon();

	thewizardmod.Tree.StartupCommon.initCommon();
	thewizardmod.Trees.StartupCommon.initCommon();
	thewizardmod.plants.StartupCommon.initCommon();

	thewizardmod.ores.StartupCommon.initCommon();
    thewizardmod.items.StartupCommon.initCommon();
    thewizardmod.fluids.StartupCommon.initCommon();

    thewizardmod.camouflageBlock.StartupCommon.initCommon();

    thewizardmod.magicInjector.StartupCommon.initCommon();
    thewizardmod.extractor.StartupCommon.initCommon();
    thewizardmod.FarmCrops.StartupCommon.initCommon();
    thewizardmod.FarmFeeder.StartupCommon.initCommon();
//    thewizardmod.pipe.StartupCommon.initCommon();

    thewizardmod.Backpacks.StartupCommon.initCommon();
    thewizardmod.Food.StartupCommon.initCommon();
    thewizardmod.Jar_undead_heart.StartupCommon.initCommon();
    thewizardmod.fireChalice.StartupCommon.initCommon();
    thewizardmod.books.StartupCommon.initCommon();
    thewizardmod.mirror.StartupCommon.initCommon();

    thewizardmod.altar.StartupCommon.initCommon();
    thewizardmod.pedestral.StartupCommon.initCommon();
    thewizardmod.runeSlab.StartupCommon.initCommon();
    thewizardmod.runes.StartupCommon.initCommon();
    
    thewizardmod.recipes.StartupCommon.initCommon();

    thewizardmod.Biomes.StartupCommon.initCommon();
    thewizardmod.World.StartupCommon.initCommon();

	thewizardmod.creative_tab.StartupCommon.initCommon();
	thewizardmod.events.StartupCommon.initCommon();		// useless
	thewizardmod.Gui.StartupCommon.initCommon();

	thewizardmod.entity.StartupCommon.initCommon();

	thewizardmod.dimensions.StartupCommon.initCommon();
}

  /**
   * Handle interaction with other mods, complete your setup based on this.
   */
  public void postInit()
  {
	thewizardmod.configuration.StartupCommon.postInitCommon();
	  

//	thewizardmod.potions.StartupCommon.postInitCommon();
	thewizardmod.teleporter.StartupCommon.postInitCommon();
	thewizardmod.chest.StartupCommon.postInitCommon();

	thewizardmod.Wands.StartupCommon.postInitCommon();
	thewizardmod.lightBlock.StartupCommon.postInitCommon();
	thewizardmod.torch.StartupCommon.postInitCommon();

	thewizardmod.Tree.StartupCommon.postInitCommon();
	thewizardmod.Trees.StartupCommon.postInitCommon();
	thewizardmod.plants.StartupCommon.postInitCommon();

	thewizardmod.ores.StartupCommon.postInitCommon();
	thewizardmod.items.StartupCommon.postInitCommon();
	thewizardmod.fluids.StartupCommon.postInitCommon();

	thewizardmod.camouflageBlock.StartupCommon.postInitCommon();

	thewizardmod.magicInjector.StartupCommon.postInitCommon();
	thewizardmod.extractor.StartupCommon.postInitCommon();
	thewizardmod.FarmCrops.StartupCommon.postInitCommon();
	thewizardmod.FarmFeeder.StartupCommon.postInitCommon();
//	thewizardmod.pipe.StartupCommon.postInitCommon();

	thewizardmod.Backpacks.StartupCommon.postInitCommon();
	thewizardmod.Food.StartupCommon.postInitCommon();
	thewizardmod.Jar_undead_heart.StartupCommon.postInitCommon();
	thewizardmod.fireChalice.StartupCommon.postInitCommon();
	thewizardmod.books.StartupCommon.postInitCommon();
	thewizardmod.mirror.StartupCommon.postInitCommon();

	thewizardmod.altar.StartupCommon.postInitCommon();
	thewizardmod.pedestral.StartupCommon.postInitCommon();
	thewizardmod.runeSlab.StartupCommon.postInitCommon();
	thewizardmod.runes.StartupCommon.postInitCommon();

	thewizardmod.recipes.StartupCommon.postInitCommon();

    thewizardmod.Biomes.StartupCommon.postInitCommon();
    thewizardmod.World.StartupCommon.postInitCommon();

    thewizardmod.creative_tab.StartupCommon.postInitCommon();
    thewizardmod.events.StartupCommon.postInitCommon();	// useless
    thewizardmod.Gui.StartupCommon.postInitCommon();

    thewizardmod.entity.StartupCommon.postInitCommon();
  
	thewizardmod.dimensions.StartupCommon.postInitCommon();
}

  // helper to determine whether the given player is in creative mode
  //  not necessary for most examples
  abstract public boolean playerIsInCreativeMode(EntityPlayer player);

  /**
   * is this a dedicated server?
   * @return true if this is a dedicated server, false otherwise
   */
  abstract public boolean isDedicatedServer();
}
