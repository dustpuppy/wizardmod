package thewizardmod.dimensions;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import thewizardmod.entity.EntityWeirdZombie;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ChunkGenerator implements IChunkGenerator {

	private final World worldObj;
	private Random random;
	private Biome[] biomesForGeneration;

	private List<Biome.SpawnListEntry> mobs = Lists.newArrayList(new Biome.SpawnListEntry(EntityZombie.class, 10, 2, 2), new Biome.SpawnListEntry(EntityBat.class, 10, 2, 2), new Biome.SpawnListEntry(EntityCaveSpider.class, 10, 2, 2), new Biome.SpawnListEntry(EntityCreeper.class, 10, 2, 2), new Biome.SpawnListEntry(EntityGiantZombie.class, 10, 2, 2), new Biome.SpawnListEntry(EntitySkeleton.class, 10, 2, 2), new Biome.SpawnListEntry(EntityWitch.class, 10, 2, 2), new Biome.SpawnListEntry(EntityWeirdZombie.class, 10, 2, 5));

	private MapGenBase caveGenerator = new MapGenCaves();
	private NormalTerrainGenerator terraingen = new NormalTerrainGenerator();


	public ChunkGenerator(World worldObj) {
		this.worldObj = worldObj;
		long seed = worldObj.getSeed();
		this.random = new Random((seed + 516) * 314);
		terraingen.setup(worldObj, random);
		caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);

	}

	@Override
	public Chunk provideChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();

		// Setup biomes for terraingen
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		terraingen.setBiomesForGeneration(biomesForGeneration);
		terraingen.generate(x, z, chunkprimer);

		// Setup biomes again for actual biome decoration
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		// This will replace stone with the biome specific stones
		terraingen.replaceBiomeBlocks(x, z, chunkprimer, this, biomesForGeneration);

		// Generate caves
		this.caveGenerator.generate(this.worldObj, x, z, chunkprimer);

		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);

		byte[] biomeArray = chunk.getBiomeArray();
		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		int i = x * 16;
		int j = z * 16;
		BlockPos blockpos = new BlockPos(i, 0, j);
		Biome biome = this.worldObj.getBiome(blockpos.add(16, 0, 16));

		// Add biome decorations (like flowers, grass, trees, ...)
		biome.decorate(this.worldObj, this.random, blockpos);

		// Make sure animals appropriate to the biome spawn here when the chunk is generated
		WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biome, i + 8, j + 8, 16, 16, this.random);
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		// If you want normal creatures appropriate for this biome then uncomment the
		// following two lines:
		//Biome biome = this.worldObj.getBiome(pos);
		//return biome.getSpawnableList(creatureType);

		if (creatureType == EnumCreatureType.MONSTER){
			return mobs;
		}
		return ImmutableList.of();
	}

	@Nullable
	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
	}
	
}
