package thewizardmod.fluids;

import java.awt.Color;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FluidMagic extends Fluid{

	public FluidMagic() {
		super("fluid_magic", new ResourceLocation("blocks/water_still"), new ResourceLocation("blocks/water_flow"));
	}

	@Override
	public int getColor() {
		return new Color(255, 0, 100).getRGB();
	}
	
	
}
