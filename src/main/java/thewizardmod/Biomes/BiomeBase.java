package thewizardmod.Biomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager.BiomeType;

public abstract class BiomeBase extends Biome{

	public BiomeBase(BiomeProperties properties, String name) {
		super(properties);
		setRegistryName(name);
	}

	public BiomeBase(String name){
		this(new BiomeProperties(name), name);
	}
	
	public BiomeType getBiomeType(){
		return BiomeType.WARM;
	}
}
