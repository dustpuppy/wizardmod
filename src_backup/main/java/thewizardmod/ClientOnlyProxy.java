package thewizardmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * ClientProxy is used to set up the mod and start it running on normal minecraft.  It contains all the code that should run on the
 *   client side only.
 *   For more background information see here http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
 */
public class ClientOnlyProxy extends CommonProxy
{

  /**
   * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
   */
  public void preInit()
  {
    super.preInit();
    thewizardmod.configuration.StartupClientOnly.preInitClientOnly();
    

//    thewizardmod.potions.StartupClientOnly.preInitClientOnly();
    thewizardmod.teleporter.StartupClientOnly.preInitClientOnly();
    thewizardmod.chest.StartupClientOnly.preInitClientOnly();

    thewizardmod.Wands.StartupClientOnly.preInitClientOnly();
    thewizardmod.lightBlock.StartupClientOnly.preInitClientOnly();
    thewizardmod.torch.StartupClientOnly.preInitClientOnly();

    thewizardmod.Tree.StartupClientOnly.preInitClientOnly();
    thewizardmod.plants.StartupClientOnly.preInitClientOnly();

    thewizardmod.ores.StartupClientOnly.preInitClientOnly();
    thewizardmod.items.StartupClientOnly.preInitClientOnly();
    thewizardmod.fluids.StartupClientOnly.preInitClientOnly();
    
    thewizardmod.items.StartupClientOnly.preInitClientOnly();
    thewizardmod.camouflageBlock.StartupClientOnly.preInitClientOnly();

    thewizardmod.magicInjector.StartupClientOnly.preInitClientOnly();
    thewizardmod.extractor.StartupClientOnly.preInitClientOnly();
    thewizardmod.FarmCrops.StartupClientOnly.preInitClientOnly();
    thewizardmod.FarmFeeder.StartupClientOnly.preInitClientOnly();
//    thewizardmod.pipe.StartupClientOnly.preInitClientOnly();

    thewizardmod.Backpacks.StartupClientOnly.preInitClientOnly();
    thewizardmod.Food.StartupClientOnly.preInitClientOnly();
    thewizardmod.Jar_undead_heart.StartupClientOnly.preInitClientOnly();
    thewizardmod.fireChalice.StartupClientOnly.preInitClientOnly();
    thewizardmod.books.StartupClientOnly.preInitClientOnly();
    thewizardmod.mirror.StartupClientOnly.preInitClientOnly();

    thewizardmod.altar.StartupClientOnly.preInitClientOnly();
    thewizardmod.pedestral.StartupClientOnly.preInitClientOnly();
    thewizardmod.runeSlab.StartupClientOnly.preInitClientOnly();
    thewizardmod.runes.StartupClientOnly.preInitClientOnly();

    thewizardmod.recipes.StartupClientOnly.preInitClientOnly();
    
    thewizardmod.Biomes.StartupClientOnly.preInitClientOnly();
    thewizardmod.World.StartupClientOnly.preInitClientOnly();

    thewizardmod.creative_tab.StartupClientOnly.preInitClientOnly();
    thewizardmod.events.StartupClientOnly.preInitClientOnly();
    thewizardmod.Gui.StartupClientOnly.preInitClientOnly();

    thewizardmod.entity.StartupClientOnly.preInitClientOnly();

    thewizardmod.dimensions.StartupClientOnly.preInitClientOnly();
}

  /**
   * Do your mod setup. Build whatever data structures you care about. Register recipes,
   * send FMLInterModComms messages to other mods.
   */
  public void init()
  {
    super.init();
    thewizardmod.configuration.StartupClientOnly.initClientOnly();
    

//    thewizardmod.potions.StartupClientOnly.initClientOnly();
    thewizardmod.teleporter.StartupClientOnly.initClientOnly();
    thewizardmod.chest.StartupClientOnly.initClientOnly();

    thewizardmod.Wands.StartupClientOnly.initClientOnly();
    thewizardmod.lightBlock.StartupClientOnly.initClientOnly();
    thewizardmod.torch.StartupClientOnly.initClientOnly();

    thewizardmod.Tree.StartupClientOnly.initClientOnly();
    thewizardmod.plants.StartupClientOnly.initClientOnly();

    thewizardmod.ores.StartupClientOnly.initClientOnly();
    thewizardmod.items.StartupClientOnly.initClientOnly();
    thewizardmod.fluids.StartupClientOnly.initClientOnly();
    
    thewizardmod.camouflageBlock.StartupClientOnly.initClientOnly();

    thewizardmod.magicInjector.StartupClientOnly.initClientOnly();
    thewizardmod.extractor.StartupClientOnly.initClientOnly();
    thewizardmod.FarmCrops.StartupClientOnly.initClientOnly();
    thewizardmod.FarmFeeder.StartupClientOnly.initClientOnly();
//    thewizardmod.pipe.StartupClientOnly.initClientOnly();

    thewizardmod.Backpacks.StartupClientOnly.initClientOnly();
    thewizardmod.Food.StartupClientOnly.initClientOnly();
    thewizardmod.Jar_undead_heart.StartupClientOnly.initClientOnly();
    thewizardmod.fireChalice.StartupClientOnly.initClientOnly();
    thewizardmod.books.StartupClientOnly.initClientOnly();
    thewizardmod.mirror.StartupClientOnly.initClientOnly();
    
    thewizardmod.altar.StartupClientOnly.initClientOnly();
    thewizardmod.pedestral.StartupClientOnly.initClientOnly();
    thewizardmod.runeSlab.StartupClientOnly.initClientOnly();
    thewizardmod.runes.StartupClientOnly.initClientOnly();
    
    thewizardmod.recipes.StartupClientOnly.initClientOnly();

    thewizardmod.Biomes.StartupClientOnly.initClientOnly();
    thewizardmod.World.StartupClientOnly.initClientOnly();

    thewizardmod.creative_tab.StartupClientOnly.initClientOnly();
    thewizardmod.events.StartupClientOnly.initClientOnly();
    thewizardmod.Gui.StartupClientOnly.initClientOnly();

    thewizardmod.entity.StartupClientOnly.initClientOnly();
  
    thewizardmod.dimensions.StartupClientOnly.initClientOnly();
}

  /**
   * Handle interaction with other mods, complete your setup based on this.
   */
  public void postInit()
  {
    super.postInit();
    thewizardmod.configuration.StartupClientOnly.postInitClientOnly();


//    thewizardmod.potions.StartupClientOnly.postInitClientOnly();
    thewizardmod.teleporter.StartupClientOnly.postInitClientOnly();
    thewizardmod.chest.StartupClientOnly.postInitClientOnly();

    thewizardmod.Wands.StartupClientOnly.postInitClientOnly();
    thewizardmod.lightBlock.StartupClientOnly.postInitClientOnly();
    thewizardmod.torch.StartupClientOnly.postInitClientOnly();

    thewizardmod.Tree.StartupClientOnly.postInitClientOnly();
    thewizardmod.plants.StartupClientOnly.postInitClientOnly();

    thewizardmod.ores.StartupClientOnly.postInitClientOnly();
    thewizardmod.items.StartupClientOnly.postInitClientOnly();
    thewizardmod.fluids.StartupClientOnly.postInitClientOnly();
    
    thewizardmod.camouflageBlock.StartupClientOnly.postInitClientOnly();

    thewizardmod.magicInjector.StartupClientOnly.postInitClientOnly();
    thewizardmod.extractor.StartupClientOnly.postInitClientOnly();
    thewizardmod.FarmCrops.StartupClientOnly.postInitClientOnly();
    thewizardmod.FarmFeeder.StartupClientOnly.postInitClientOnly();
//    thewizardmod.pipe.StartupClientOnly.postInitClientOnly();

    thewizardmod.Backpacks.StartupClientOnly.postInitClientOnly();
    thewizardmod.Food.StartupClientOnly.postInitClientOnly();
    thewizardmod.Jar_undead_heart.StartupClientOnly.postInitClientOnly();
    thewizardmod.fireChalice.StartupClientOnly.postInitClientOnly();
    thewizardmod.books.StartupClientOnly.postInitClientOnly();
    thewizardmod.mirror.StartupClientOnly.postInitClientOnly();

    thewizardmod.altar.StartupClientOnly.postInitClientOnly();
    thewizardmod.pedestral.StartupClientOnly.postInitClientOnly();
    thewizardmod.runeSlab.StartupClientOnly.postInitClientOnly();
    thewizardmod.runes.StartupClientOnly.postInitClientOnly();
    
    thewizardmod.recipes.StartupClientOnly.postInitClientOnly();

    thewizardmod.Biomes.StartupClientOnly.postInitClientOnly();
    thewizardmod.World.StartupClientOnly.postInitClientOnly();

    thewizardmod.creative_tab.StartupClientOnly.postInitClientOnly();
    thewizardmod.events.StartupClientOnly.postInitClientOnly();
    thewizardmod.Gui.StartupClientOnly.postInitClientOnly();

    thewizardmod.entity.StartupClientOnly.postInitClientOnly();

    thewizardmod.dimensions.StartupClientOnly.postInitClientOnly();
}

  @Override
  public boolean playerIsInCreativeMode(EntityPlayer player) {
    if (player instanceof EntityPlayerMP) {
      EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
      return entityPlayerMP.interactionManager.isCreative();
    } else if (player instanceof EntityPlayerSP) {
      return Minecraft.getMinecraft().playerController.isInCreativeMode();
    }
    return false;
  }

  @Override
  public boolean isDedicatedServer() {return false;}

}
