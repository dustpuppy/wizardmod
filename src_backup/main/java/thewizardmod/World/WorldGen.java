package thewizardmod.World;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import thewizardmod.ores.ShadowDust_Ore;
import thewizardmod.ores.StartupCommon;

public class WorldGen implements IWorldGenerator{

	static final int cherryTree = 0;
	
	
	public void nether(Random random, int x, int z, World world) {
		generateOre(StartupCommon.shadowdustOre.getDefaultState(), Blocks.NETHERRACK, random, x, z, world, ShadowDust_Ore.NetherWorldChance, ShadowDust_Ore.NetherWorldMin, ShadowDust_Ore.NetherWorldMax, ShadowDust_Ore.NetherWorldMinSize, ShadowDust_Ore.NetherWorldMaxSize);
	}
	
	public void overworld(Random random, int x, int z, World world) {
		generateOre(StartupCommon.shadowdustOre.getDefaultState(), Blocks.STONE, random, x, z, world, ShadowDust_Ore.OverWorldChance, ShadowDust_Ore.OverWorldMin, ShadowDust_Ore.OverWorldMax, ShadowDust_Ore.OverWorldMinSize, ShadowDust_Ore.OverWorldMaxSize);
//		generateDungeon(world, x, z, random);
//		generateCircle(world, x, z, random);
		generateTree(cherryTree, x, z, random, world, thewizardmod.configuration.ModConfiguration.chance_of_cherry_trees, thewizardmod.configuration.ModConfiguration.num_of_cherry_trees);
		
	}
	
	public void end(Random random, int x, int z, World world) {
		generateOre(StartupCommon.shadowdustOre.getDefaultState(), Blocks.END_STONE, random, x, z, world, ShadowDust_Ore.EndWorldChance, ShadowDust_Ore.EndWorldMin, ShadowDust_Ore.EndWorldMax, ShadowDust_Ore.EndWorldMinSize, ShadowDust_Ore.EndWorldMaxSize);
	}
	
	public void customDimension(Random random, int x, int z, World world) {
		generateOre(StartupCommon.shadowdustOre.getDefaultState(), Blocks.STONE, random, x, z, world, ShadowDust_Ore.OverWorldChance * 5, ShadowDust_Ore.OverWorldMin, 250, ShadowDust_Ore.OverWorldMinSize * 2, ShadowDust_Ore.OverWorldMaxSize * 3);
		generateDungeon(world, x, z, random);
		generatePoison(thewizardmod.fluids.StartupCommon.blockFluidPoison, random, x, 100, z, world);
		generateTree(cherryTree, x, z, random, world, thewizardmod.configuration.ModConfiguration.chance_of_cherry_trees, thewizardmod.configuration.ModConfiguration.num_of_cherry_trees * 3);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
		int x = chunkX * 16;
		int z = chunkZ * 16;


		switch(world.provider.getDimension()) {
		case -1:		// Nether
				nether(random, x, z, world);
			break;
		case 0:			// Overworld
			overworld(random, x, z, world);
			break;
		case 1:			// End
				end(random, x, z, world);
			break;
			
		}
		if (world.provider.getDimension() == thewizardmod.configuration.ModConfiguration.dimID)
		{
			customDimension(random, x, z, world);
		}
	}

	public static void generatePoison(Block blockin, Random random, int x, int y, int z, World world){
		
			int posX = x + random.nextInt(16);
			int posY = random.nextInt(y) + 1;
			int posZ = z + random.nextInt(16);
			BlockPos blockpos = new BlockPos(posX, posY, posZ);
			if(world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock() == Blocks.AIR)
				new WorldGenLakes(blockin).generate(world, random, new BlockPos(posX, posY, posZ));
	}
	
	public static void generateOre(IBlockState state, Block blockin, Random random, int x, int z, World world, int chance, int minY, int maxY, int minVienSize, int maxVienSize){
		int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
		int hightRange = maxY - minY;
		
		for(int i=0; i < chance; i ++) {
			int posX = x + random.nextInt(16);
			int posY = random.nextInt(hightRange) + minY;
			int posZ = z + random.nextInt(16);
			BlockPos blockpos = new BlockPos(posX, posY, posZ);
			new WorldGenMinable(state, vienSize, BlockMatcher.forBlock(blockin)).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}
	
	public void generateTree(int type, int x, int z, Random random, World world, int chance, int amount){

		int c = random.nextInt(chance);
		if(c == 0)
		{
			for(int i = 0; i < amount; i++)
			{
				int posX = x + random.nextInt(16);
				int posZ = z + random.nextInt(16);
				int posY = getWorldHeightAt(world, posX, posZ)+1;
				BlockPos pos = new BlockPos(posX, posY, posZ);
				IBlockState state = world.getBlockState(pos).getBlock().getDefaultState();
				if(world.getBlockState(new BlockPos(posX, posY-1, posZ)).getBlock() == Blocks.GRASS){
					switch(type){
					case cherryTree:
						thewizardmod.Tree.CherryTree generator = new thewizardmod.Tree.CherryTree();
//						world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

						if (!generator.generate(world, random, pos)) {
//							System.out.println("Generating cherry tree at" + posX + " " + posY + " " + posZ);
							world.setBlockState(pos, state, 4);
						}
						break;
					}
				}
			}
		}
	}
	
	public void generateDungeon(World world, int x, int z, Random random){
		if(random.nextInt(250) == 0){

			int y = getWorldHeightAt(world, x, z);
			BlockPos pos = new BlockPos(x, y, z);
			IBlockState state = world.getBlockState(pos).getBlock().getDefaultState();
			if(world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.GRASS)
			{
				StructureDungeon.generate(world, x, y - 9, z);
			}
		}
	}
	
	public void generateCircle(World world, int x, int z, Random random){
		if(random.nextInt(30) == 0){

			int y = getWorldHeightAt(world, x, z);
			BlockPos pos = new BlockPos(x, y, z);
			IBlockState state = world.getBlockState(pos).getBlock().getDefaultState();
			if(world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.GRASS)
			{
				StructureCircle.generate(world, x, y, z);
			}
		}
	}
	
	private static int getWorldHeightAt(World world, int x, int z){
		int height = 0;
		for(int i = 0; i < 255; i++){
			BlockPos blockPos = new BlockPos(x, i, z);
			if(world.getBlockState(blockPos).isFullBlock()){
				height = i;
			}
		}
		return height;
	}
	
}

