package thewizardmod.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 *
 * The Startup classes for this example are called during startup, in the following order:
 *  preInitCommon
 *  preInitClientOnly
 *  initCommon
 *  initClientOnly
 *  postInitCommon
 *  postInitClientOnly
 *  See MinecraftByExample class for more information
 */
public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
    for (PotionBottles.EnumBottleFullness fullness : PotionBottles.EnumBottleFullness.values()) {
      String itemModelName = "potion_" + fullness.getName();
      ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("thewizardmod:" + itemModelName, "inventory");
      // --> eg minecraftbyexample:mbe11_item_variants_0pc, mbe11_item_variants_25pc, etc
      final int fullnessBits = fullness.getMetadata();
      for (PotionBottles.EnumBottleContents contents : PotionBottles.EnumBottleContents.values()) {
        final int contentsBits = contents.getMetadata();
        int metadata = contentsBits | (fullnessBits << 2);
        ModelLoader.setCustomModelResourceLocation(StartupCommon.itemVariants, metadata, itemModelResourceLocation);
      }
    }

    // if your item has subtypes but you want the registry to ignore metadata, use the method below instead
//    ModelLoader.setCustomMeshDefinition(itemVariants, new ItemMeshDefinition()
//    {
//      public ModelResourceLocation getModelLocation(ItemStack stack) {
//        return new ModelResourceLocation("spawn_egg", "inventory");
//      }
//    });
  }

 public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
    // the LiquidColour class is used to change the rendering colour of the liquid in the bottle

    Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new LiquidColour(), StartupCommon.itemVariants);
  }
}
