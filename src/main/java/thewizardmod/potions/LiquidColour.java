package thewizardmod.potions;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

import java.awt.*;

/**
 * Created by TGG on 17/08/2016.
 */
public class LiquidColour implements IItemColor {


  @Override
  public int getColorFromItemstack(ItemStack stack, int tintIndex) {
    // when rendering, choose the colour multiplier based on the contents
    // we want layer 0 (the bottle glass) to be unaffected (return white as the multiplier)
    // layer 1 will change colour depending on the contents.
    {
      switch (tintIndex) {
        case 0: return Color.WHITE.getRGB();
        case 1: {
          int metadata = stack.getMetadata();
          int contentsBits = metadata & 0x03;
          PotionBottles.EnumBottleContents contents = PotionBottles.EnumBottleContents.byMetadata(contentsBits);
          return contents.getRenderColour().getRGB();
        }
        default: {
          // oops! should never get here.
          return Color.BLACK.getRGB();
        }
      }
    }
  }
}
