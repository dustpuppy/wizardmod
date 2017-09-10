package thewizardmod.Tree;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;

public enum EnumWood implements IStringSerializable {

	CHERRY;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	public static EnumWood byMetadata(int meta) {
		return values()[meta];
	}
}