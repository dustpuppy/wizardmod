package thewizardmod.Biomes;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class StartupCommon
{
	  public static MagicForest magicForest;
  public static void preInitCommon()
  {
  }

  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
//	  magicForest = new MagicForest("MagicForest");
//	  registerBiome(magicForest, false);
  }

  public static void registerBiome(BiomeBase biome, boolean isSpawnBiome){
		Biome.REGISTRY.register(43, new ResourceLocation("testBiome"), biome);
		BiomeDictionary.registerBiomeType(biome, BiomeDictionary.Type.FOREST);
		  if(isSpawnBiome){
			  BiomeManager.addSpawnBiome(biome);
		  }
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biome, 10));
  }
}
