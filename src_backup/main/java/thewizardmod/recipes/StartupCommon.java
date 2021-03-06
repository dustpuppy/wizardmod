package thewizardmod.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class StartupCommon
{

  public static void preInitCommon() {
  }

  public static void initCommon()
  {
    // your recipes must go in initialisation phase, not preInit.

    //  see http://greyminecraftcoder.blogspot.com/2015/02/recipes.html for illustrations of these recipes

	/*
    // a) Shaped recipe without metadata - emerald surrounded by diamond makes ender eye
    GameRegistry.addRecipe(new ItemStack(Items.ENDER_EYE), new Object[]{
            ".D.",
            "DED",
            ".D.",
                  'D', Items.DIAMOND,
                  'E', Items.EMERALD     // note carefully - 'E' not "E" !
    });

    // note - smaller grids are also possible, you don't need to fill up the entire 3x3 space.

    // b) shaped recipe with metadata - cobblestone surrounded by red dye makes redstone
    final int RED_DYE_DAMAGE_VALUE = EnumDyeColor.RED.getDyeDamage();
    GameRegistry.addRecipe(new ItemStack(Items.REDSTONE), new Object[]{
            "RRR",
            "RCR",
            "RRR",
                  'C', Blocks.COBBLESTONE,
                  'R', new ItemStack(Items.DYE, 1, RED_DYE_DAMAGE_VALUE)
    });

    // c) shaped recipe for items which are damaged, or which have a metadata you want to ignore
    //      wooden sword (any damage value) in a cobblestone shell plus iron ingot makes iron sword
    GameRegistry.addRecipe(new ItemStack(Items.IRON_SWORD), new Object[]{
            "CIC",
            "CWC",
            "CCC",
                  'C', Blocks.COBBLESTONE,
                  'W', new ItemStack(Items.WOODEN_SWORD, 1, OreDictionary.WILDCARD_VALUE),
                  'I', Items.IRON_INGOT
    });

    // for comparison - this recipe only works with an undamaged wooden sword
    //   wooden sword (undamaged) in a cobblestone shell plus gold ingot makes gold sword
    GameRegistry.addRecipe(new ItemStack(Items.GOLDEN_SWORD), new Object[]{
            "CIC",
            "CWC",
            "CCC",
            'C', Blocks.COBBLESTONE,
            'W', Items.WOODEN_SWORD,
            'I', Items.GOLD_INGOT
    });

    // d) Shapeless recipe - blue dye plus yellow dye makes two green dye
    final int BLUE_DYE_DAMAGE_VALUE = EnumDyeColor.BLUE.getDyeDamage();
    final int YELLOW_DYE_DAMAGE_VALUE = EnumDyeColor.YELLOW.getDyeDamage();
    final int GREEN_DYE_DAMAGE_VALUE = EnumDyeColor.GREEN.getDyeDamage();
    final int NUMBER_OF_GREEN_DYE_PRODUCED = 2;
    GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, NUMBER_OF_GREEN_DYE_PRODUCED, GREEN_DYE_DAMAGE_VALUE),
            new Object[] {
                    new ItemStack(Items.DYE, 1, YELLOW_DYE_DAMAGE_VALUE),
                    new ItemStack(Items.DYE, 1, BLUE_DYE_DAMAGE_VALUE)
    });

    // g) Shaped Ore recipe - any type of tree leaves arranged around sticks makes a sapling
    //    Ores are a way for mods to add blocks & items which are equivalent to vanilla blocks for crafting
    //    For example - an ore recipe which uses "logWood" will accept a log of spruce, oak, birch, pine, etc.
    //    If your mod registers its balsawood log using  OreDictionary.registerOre("logWood", BalsaWood), then your
    //    BalsaWood log will also be accepted in the recipe.
    IRecipe saplingRecipe = new ShapedOreRecipe(new ItemStack(Blocks.SAPLING), new Object[] {
            "LLL",
            "LSL",
            ".S.",
            'S', Items.STICK,   // can use ordinary items, blocks, itemstacks in ShapedOreRecipe
            'L', "treeLeaves",  // look in OreDictionary for vanilla definitions
    });
    GameRegistry.addRecipe(saplingRecipe);

    // h) by default, recipes are automatically mirror-imaged, i.e. you can flip the recipe left<--> right and it will
    //    produce the same output.  This one isn't.  Only works for OreRecipes, but you can make ShapedOreRecipe from vanilla
    //    Items or Blocks too (see (g) above)
    IRecipe unmirroredRecipe = new ShapedOreRecipe(new ItemStack(Items.CAULDRON), new Object[] {
            false,
            "III",
            "I..",
            "III",
            'I', Items.IRON_INGOT
    });
    GameRegistry.addRecipe(unmirroredRecipe);

    //---------------- FURNACE RECIPES (smelting)

    
    // e) fuel - use wheat as fuel in a furnace
    //   We use an anonymous class... you can use an ordinary class instead if you prefer.
    IFuelHandler wheatFuelHandler = new IFuelHandler() {
      @Override
      public int getBurnTime(ItemStack fuel) {
        final int BURN_TIME_SECONDS = 5;
        final int TICKS_PER_SECOND = 20;
        if (fuel != null && fuel.getItem() == Items.WHEAT) {
          return BURN_TIME_SECONDS * TICKS_PER_SECOND;
        } else {
          return 0;
        }
      }
    };
    GameRegistry.registerFuelHandler(wheatFuelHandler);
*/

	  
	// The magic wheat to magic bread
    GameRegistry.addRecipe(new ItemStack(thewizardmod.Food.StartupCommon.magicBread), new Object[]{
            "...",
            "...",
            "WWW",
            'W', thewizardmod.plants.StartupCommon.wheat     
    });
	  
	// The magic cookie
    GameRegistry.addRecipe(new ItemStack(thewizardmod.Food.StartupCommon.magicCookie, 8), new Object[]{
            "...",
            "WDW",
            "...",
            'W', thewizardmod.plants.StartupCommon.wheat,
            'D', thewizardmod.items.StartupCommon.shadowDust
    });
	  
	// The injector
    GameRegistry.addRecipe(new ItemStack(thewizardmod.magicInjector.StartupCommon.itemBlockInventoryAdvanced), new Object[]{
        "III",
        "SCS",
        "IPI",
        'S', thewizardmod.items.StartupCommon.shadowDust,
        'C', Items.CAULDRON,
        'P', Blocks.PISTON,
        'I', Items.IRON_INGOT
    });
  
	// The extractor
    GameRegistry.addRecipe(new ItemStack(thewizardmod.extractor.StartupCommon.blockTank), new Object[]{
        "III",
        "SBS",
        "IHI",
        'S', thewizardmod.items.StartupCommon.shadowDust,
        'B', Items.BUCKET,
        'H', Blocks.HOPPER,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
	// Crop farm
    GameRegistry.addRecipe(new ItemStack(thewizardmod.FarmCrops.StartupCommon.blockMachine), new Object[]{
        "III",
        "SBS",
        "IHI",
        'S', thewizardmod.items.StartupCommon.shadowDust,
        'B', Items.BUCKET,
        'H', thewizardmod.items.StartupCommon.ironHoe,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
	// Auto feeder
    GameRegistry.addRecipe(new ItemStack(thewizardmod.FarmFeeder.StartupCommon.blockMachine), new Object[]{
        "III",
        "SBS",
        "ICI",
        'S', thewizardmod.items.StartupCommon.shadowDust,
        'B', Items.BUCKET,
        'C', Items.GOLDEN_CARROT,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Pedestral
    GameRegistry.addRecipe(new ItemStack(thewizardmod.pedestral.StartupCommon.pedestral, 4), new Object[]{
        "BBB",
        ".B.",
        "BBB",
        'B', thewizardmod.dimensions.StartupCommon.portalFrame
    });

    // Altar
    GameRegistry.addRecipe(new ItemStack(thewizardmod.altar.StartupCommon.altar), new Object[]{
        "BBB",
        "BGB",
        "BBB",
        'B', thewizardmod.dimensions.StartupCommon.portalFrame,
        'G', thewizardmod.items.StartupCommon.magicGem
    });

    // Rune slabs
    GameRegistry.addRecipe(new ItemStack(thewizardmod.runeSlab.StartupCommon.runeSlab, 6), new Object[]{
        "...",
        "...",
        "BBB",
        'B', thewizardmod.dimensions.StartupCommon.portalFrame
    });

    // Fire chalice
    GameRegistry.addRecipe(new ItemStack(thewizardmod.fireChalice.StartupCommon.fireChalice), new Object[]{
        "G.G",
        ".G.",
        "GGG",
        'G', Items.GOLD_INGOT
    });

    // Wooden wand
    GameRegistry.addRecipe(new ItemStack(thewizardmod.Wands.StartupCommon.woodenWand), new Object[]{
        "..C",
        ".C.",
        "C..",
        'C', thewizardmod.Tree.StartupCommon.itemCherryLog
    });
  
    // Golden wand
    GameRegistry.addRecipe(new ItemStack(thewizardmod.Wands.StartupCommon.goldenWand), new Object[]{
        "..G",
        ".G.",
        "G..",
        'G', Items.GOLD_INGOT
    });
  
    // Iron wand
    GameRegistry.addRecipe(new ItemStack(thewizardmod.Wands.StartupCommon.ironWand), new Object[]{
        "..I",
        ".I.",
        "I..",
        'I', Items.IRON_INGOT
    });
  
    // Small Backpack
    GameRegistry.addRecipe(new ItemStack(thewizardmod.Backpacks.StartupCommon.smallBackpack), new Object[]{
        "SLS",
        "LCL",
        ".L.",
        'S', Items.STRING,
        'L', Items.LEATHER,
        'C', Blocks.CHEST
    });
  
    // Normal Backpack
    GameRegistry.addRecipe(new ItemStack(thewizardmod.Backpacks.StartupCommon.normalBackpack), new Object[]{
        "SLS",
        "CLC",
        ".L.",
        'S', Items.STRING,
        'L', Items.LEATHER,
        'C', Blocks.CHEST
    });
  
    // Large Backpack
    GameRegistry.addRecipe(new ItemStack(thewizardmod.Backpacks.StartupCommon.bigBackpack), new Object[]{
        "SLS",
        "CCC",
        ".L.",
        'S', Items.STRING,
        'L', Items.LEATHER,
        'C', Blocks.CHEST
    });
  
    // Magic iron sword
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.ironSword), new Object[]{
        ".I.",
        ".I.",
        ".S.",
        'S', Items.STICK,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Magic iron pickaxe
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.ironPickaxe), new Object[]{
        "III",
        ".S.",
        ".S.",
        'S', Items.STICK,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Magic iron axe
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.ironAxe), new Object[]{
        "II.",
        "IS.",
        ".S.",
        'S', Items.STICK,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Magic iron shovel
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.ironShovel), new Object[]{
        ".I.",
        ".S.",
        ".S.",
        'S', Items.STICK,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Magic iron hoe
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.ironHoe), new Object[]{
        "II.",
        ".S.",
        ".S.",
        'S', Items.STICK,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Magic iron helmet
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.ironHelmet), new Object[]{
        "III",
        "IGI",
        "...",
        'G', thewizardmod.items.StartupCommon.magicGem,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Magic iron chestplate
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.ironChestplate), new Object[]{
        "I.I",
        "IGI",
        "III",
        'G', thewizardmod.items.StartupCommon.magicGem,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Magic iron leggings
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.ironLeggings), new Object[]{
        "III",
        "IGI",
        "I.I",
        'G', thewizardmod.items.StartupCommon.magicGem,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Magic iron boots
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.ironBoots), new Object[]{
        "...",
        "IGI",
        "I.I",
        'G', thewizardmod.items.StartupCommon.magicGem,
        'I', thewizardmod.items.StartupCommon.magicIron
    });
  
    // Magic  robe helmet
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.robeHelmet), new Object[]{
        "III",
        "IGI",
        "...",
        'G', thewizardmod.items.StartupCommon.magicGem,
        'I', thewizardmod.items.StartupCommon.magicString
    });
  
    // Magic robe chestplate
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.robeChestplate), new Object[]{
        "I.I",
        "IGI",
        "III",
        'G', thewizardmod.items.StartupCommon.magicGem,
        'I', thewizardmod.items.StartupCommon.magicString
    });
  
    // Magic robe leggings
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.robeLeggings), new Object[]{
        "III",
        "IGI",
        "I.I",
        'G', thewizardmod.items.StartupCommon.magicGem,
        'I', thewizardmod.items.StartupCommon.magicString
    });
  
    // Magic robe boots
    GameRegistry.addRecipe(new ItemStack(thewizardmod.items.StartupCommon.robeBoots), new Object[]{
        "...",
        "IGI",
        "I.I",
        'G', thewizardmod.items.StartupCommon.magicGem,
        'I', thewizardmod.items.StartupCommon.magicString
    });
  
    // Magic Jar (for the heart of an undead)
    IRecipe jarRecipe = new ShapedOreRecipe(new ItemStack(thewizardmod.Jar_undead_heart.StartupCommon.jar), new Object[]{
        ".S.",
        "G.G",
        "GGG",
        'G', Blocks.GLASS,
        'S', "slabWood"
    });
    GameRegistry.addRecipe(jarRecipe);

    // Mirror glass
    GameRegistry.addRecipe(new ItemStack(thewizardmod.mirror.StartupCommon.mirrorGlass), new Object[]{
        "...",
        "GGG",
        "III",
        'G', Blocks.GLASS_PANE,
        'I', Items.IRON_INGOT
    });

    
    // The wizards guide
    GameRegistry.addShapelessRecipe(new ItemStack(thewizardmod.books.StartupCommon.wizardGuide),
            new Object[] {
                    new ItemStack(Items.BOOK),
                    new ItemStack(thewizardmod.items.StartupCommon.shadowDust)
    });

    // The rune magic guide
    GameRegistry.addShapelessRecipe(new ItemStack(thewizardmod.books.StartupCommon.runeMagic),
            new Object[] {
                    new ItemStack(Items.BOOK),
                    new ItemStack(Blocks.STONE)
    });

    // The magic altar book
    GameRegistry.addShapelessRecipe(new ItemStack(thewizardmod.books.StartupCommon.altar),
            new Object[] {
                    new ItemStack(Items.BOOK),
                    new ItemStack(thewizardmod.items.StartupCommon.magicIron)
    });
    
    // The maic wands book
    GameRegistry.addShapelessRecipe(new ItemStack(thewizardmod.books.StartupCommon.bookWands),
            new Object[] {
                    new ItemStack(Items.BOOK),
                    new ItemStack(Items.STICK)
    });

    // The machine book
    GameRegistry.addShapelessRecipe(new ItemStack(thewizardmod.books.StartupCommon.injector),
            new Object[] {
                    new ItemStack(Items.BOOK),
                    new ItemStack(Items.STONE_PICKAXE)
    });

    

    /* Deactivated, becuase we changed to use the two high level wands for activating the portal
    // Flint and steel
    GameRegistry.addShapelessRecipe(new ItemStack(thewizardmod.dimensions.StartupCommon.magicFlint),
            new Object[] {
                    new ItemStack(Items.FLINT),
                    new ItemStack(thewizardmod.items.StartupCommon.magicIron)
    });
     */
    /*
    final int NUMBER_OF_ITEMS = 1;
    final int COPPERINGOT_METADATA_VALUE = 0;
    final float COPPER_SMELT_XP = 0.5F;
    GameRegistry.addSmelting(thewizardmod.ores.StartupCommon.itemCopperOre, new ItemStack(thewizardmod.ingots.StartupCommon.copperIngot, NUMBER_OF_ITEMS, COPPERINGOT_METADATA_VALUE), COPPER_SMELT_XP);
  */
  }

  public static void postInitCommon()
  {
  }

}
