package thewizardmod.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import scala.tools.nsc.MainClass;
import thewizardmod.TheWizardMod;

public class ModDimensions {

    public static DimensionType testDimensionType;
    public static final DimensionType TEST_DIMENSION = DimensionType.register("Magic World", "_test", thewizardmod.configuration.ModConfiguration.dimID, OverworldProvider.class, false);

    public static void init() {
        registerDimensions();
    }

    private static void registerDimensions() {
      DimensionManager.registerDimension(thewizardmod.configuration.ModConfiguration.dimID, TEST_DIMENSION);
    }
    
}