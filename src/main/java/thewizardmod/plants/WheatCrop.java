package thewizardmod.plants;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;


public class WheatCrop extends BlockCrops{
	
	public WheatCrop(){
	}
	@Override
	protected Item getSeed() {
		return StartupCommon.wheatSeeds;
	}
	
	@Override
	protected Item getCrop() {
		return StartupCommon.wheat;
	}
}
