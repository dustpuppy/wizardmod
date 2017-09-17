package thewizardmod.dimensions;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkGenerator;
import thewizardmod.TheWizardMod;

public class OverworldProvider extends WorldProvider
{ 
	public void registerWorldChunkManager()
	{
		this.biomeProvider = new BiomeProviderSingle(Biomes.VOID);
		this.setDimension(thewizardmod.configuration.ModConfiguration.dimID);
		this.setAllowedSpawnTypes(false, false);
		this.hasNoSky = false;
	}

	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGenerator(this.worldObj);
	}

	public Biome getBiomeGenForCoords(BlockPos pos)
	{
		return Biomes.VOID;
	}

	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		return thewizardmod.configuration.ModConfiguration.dimID;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Don't get lost here, or you will never find home";
	}

	@Override
	public DimensionType getDimensionType() 
	{
		return ModDimensions.TEST_DIMENSION;
	}
}