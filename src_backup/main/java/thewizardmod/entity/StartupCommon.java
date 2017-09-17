package thewizardmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import thewizardmod.TheWizardMod;


public class StartupCommon
{

	public static void preInitCommon()
	{
        int id = 1;
        EntityRegistry.registerModEntity(EntityWeirdZombie.class, "WeirdZombie", id++, TheWizardMod.instance, 64, 3, true, 0x996600, 0xff00ff);
        // We want our mob to spawn in Plains and ice plains biomes. If you don't add this then it will not spawn automatically
        // but you can of course still make it spawn manually
//        EntityRegistry.addSpawn(EntityWeirdZombie.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.ICE_PLAINS);

        // This is the loot table for our mob
        LootTableList.register(EntityWeirdZombie.deathLootTable);

	
        id = 2;
        EntityRegistry.registerModEntity(EntityMiniZombie.class, "MiniZombie", id++, TheWizardMod.instance, 64, 3, true, 0x996600, 0xffffff);
        // We want our mob to spawn in Plains and ice plains biomes. If you don't add this then it will not spawn automatically
        // but you can of course still make it spawn manually
//        EntityRegistry.addSpawn(EntityWeirdZombie.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.ICE_PLAINS);

        // This is the loot table for our mob
        LootTableList.register(EntityMiniZombie.deathLootTable);
}

	public static void initCommon()
	{
}

	public static void postInitCommon()
	{
	}
	

}
