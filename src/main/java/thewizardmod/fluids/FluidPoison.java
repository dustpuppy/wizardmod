package thewizardmod.fluids;

import java.awt.Color;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FluidPoison extends Fluid{

	public FluidPoison() {
		super("fluid_poison", new ResourceLocation("blocks/water_still"), new ResourceLocation("blocks/water_flow"));
//		FluidRegistry.registerFluid(this);
		this.setDensity(2000);
		this.setLuminosity(10);
		this.setTemperature(600);
		this.setViscosity(2000);
	}

	@Override
	public int getColor() {
		return new Color(0, 255, 21).getRGB();
	}
	
	
}
