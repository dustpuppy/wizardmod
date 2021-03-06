package thewizardmod.Util;

import java.util.Random;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ParticleSpread {

	public static Random rand = new Random();
	public static void spread(World world, int x, int y, int z, int amount, double width, double height, EnumParticleTypes type)
	{
		  for(int i = 0; i < amount; i++)
		  {
		    double motionX = rand.nextGaussian() * 0.04D;
		    double motionY = rand.nextGaussian() * 0.04D;
		    double motionZ = rand.nextGaussian() * 0.04D;
		    world.spawnParticle(type, x + 0.5D + rand.nextFloat() * width * 2.0F - width, y + 0.5D + rand.nextFloat() * height, z + 0.5D + rand.nextFloat() * width * 2.0F - width, motionX, motionY, motionZ);
		  }
		
	}
}
