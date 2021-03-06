package thewizardmod.Food;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
    final int DEFAULT_ITEM_SUBTYPE = 0;
    
    ModelResourceLocation magicBreadModelResourceLocation = new ModelResourceLocation("thewizardmod:magic_bread", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.magicBread, DEFAULT_ITEM_SUBTYPE, magicBreadModelResourceLocation);
    
    ModelResourceLocation magicCookieModelResourceLocation = new ModelResourceLocation("thewizardmod:magic_cookie", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.magicCookie, DEFAULT_ITEM_SUBTYPE, magicCookieModelResourceLocation);
    
  }

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
