package thewizardmod.wandHandling;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class WandHandling {

	static public final int MAX_MAGIC_TYPES = 10;

	public static int getFocus(ItemStack itemStack) {
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
		int typeOfMagic = 0;
		if (nbtTagCompound != null && nbtTagCompound.hasKey("typeOfMagic")) {
			typeOfMagic = nbtTagCompound.getInteger("typeOfMagic");
		}
		return typeOfMagic;
	}

	// Will add a focus to the wand, if it's allowed
	public static void addFocus(ItemStack itemStackIn, int focusType) {
		NBTTagCompound nbtTagCompound = itemStackIn.getTagCompound();

		if (nbtTagCompound == null) {
			nbtTagCompound = new NBTTagCompound();
			itemStackIn.setTagCompound(nbtTagCompound);
		}

		int numFocus = 0;
		if (nbtTagCompound != null && nbtTagCompound.hasKey("numFocus")) {
			numFocus = nbtTagCompound.getInteger("numFocus");
		}

		int firstFocus = 0;
		if (nbtTagCompound != null && nbtTagCompound.hasKey("firstFocus")) {
			firstFocus = nbtTagCompound.getInteger("firstFocus");
		}

		int MAX_FOCUS = 0;
		if (nbtTagCompound != null && nbtTagCompound.hasKey("maxFocus")) {
			MAX_FOCUS = nbtTagCompound.getInteger("maxFocus");
		}

		if (numFocus < MAX_FOCUS) {
			switch (focusType) {
			case 1:
				nbtTagCompound.setBoolean("hasFlamethrower", true);
				break;
			case 2:
				nbtTagCompound.setBoolean("hasLightning", true);
				break;
			case 3:
				nbtTagCompound.setBoolean("hasLight", true);
				break;
			case 4:
				nbtTagCompound.setBoolean("hasGrowth", true);
				break;
			case 5:
				nbtTagCompound.setBoolean("hasMining", true);
				break;
			case 6:
				nbtTagCompound.setBoolean("hasVines", true);
				break;
			case 7:
				nbtTagCompound.setBoolean("hasWeb", true);
				break;
			case 8:
				nbtTagCompound.setBoolean("hasIce", true);
				break;
			case 9:
				nbtTagCompound.setBoolean("hasTeleport", true);
				break;
			}
			numFocus += 1;
			nbtTagCompound.setInteger("numFocus", numFocus);

		}
		if (firstFocus == 0 || firstFocus > focusType) {
			firstFocus = focusType;
			nbtTagCompound.setInteger("firstFocus", firstFocus);
			nbtTagCompound.setInteger("typeOfMagic", firstFocus);
		}

	}

	// Injecting magic will make the wand active
	public static void injectMagic(ItemStack itemStackIn, int MAX_FOCUS,
			int DISTANCE_POWER) {
		NBTTagCompound nbtTagCompound = itemStackIn.getTagCompound();

		if (nbtTagCompound == null) {
			nbtTagCompound = new NBTTagCompound();
			itemStackIn.setTagCompound(nbtTagCompound);
		}
		boolean hasMagic = false;
		if (nbtTagCompound != null && nbtTagCompound.hasKey("hasMagic")) {
			hasMagic = nbtTagCompound.getBoolean("hasMagic");
		}

		if (!hasMagic) {
			nbtTagCompound.setBoolean("hasMagic", true);
			nbtTagCompound.setInteger("typeOfMagic", 0);
			nbtTagCompound.setInteger("firstFocus", 0);
			nbtTagCompound.setInteger("numFocus", 0);
			nbtTagCompound.setBoolean("hasFlamethrower", false);
			nbtTagCompound.setBoolean("hasLightning", false);
			nbtTagCompound.setBoolean("hasLight", false);
			nbtTagCompound.setBoolean("hasGrowth", false);
			nbtTagCompound.setBoolean("hasMining", false);
			nbtTagCompound.setBoolean("hasVines", false);
			nbtTagCompound.setBoolean("hasWeb", false);
			nbtTagCompound.setBoolean("hasIce", false);
			nbtTagCompound.setBoolean("hasTeleport", false);
			nbtTagCompound.setInteger("maxFocus", MAX_FOCUS);
			nbtTagCompound.setInteger("distancePower", DISTANCE_POWER);
			itemStackIn.setItemDamage(0);
		} else {
			itemStackIn.setItemDamage(0);
		}
	}

	public static ItemStack useFinish(ItemStack stack, World worldIn,
			EntityLivingBase entityLiving, int DISTANCE_POWER) {

		// We raise the magic power(distance) and the damgae amount on the wand
		// depending on what armor player is wearing
		int POWER = DISTANCE_POWER;
		int damage = 10;

		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			ItemStack boots = player.inventory.armorInventory[0];
			ItemStack pants = player.inventory.armorInventory[1];
			ItemStack chest = player.inventory.armorInventory[2];
			ItemStack head = player.inventory.armorInventory[3];
			if (boots != null) {
				if (boots.getItem() == thewizardmod.items.StartupCommon.ironBoots) {
					POWER += 1;
					damage -= 1;
				}
				if (boots.getItem() == thewizardmod.items.StartupCommon.robeBoots) {
					POWER += 3;
					damage -= 1;
				}
			}

			if (pants != null) {
				if (pants.getItem() == thewizardmod.items.StartupCommon.ironLeggings) {
					POWER += 1;
					damage -= 1;
				}
				if (pants.getItem() == thewizardmod.items.StartupCommon.robeLeggings) {
					POWER += 3;
					damage -= 1;
				}
			}

			if (chest != null) {
				if (chest.getItem() == thewizardmod.items.StartupCommon.ironChestplate) {
					POWER += 5;
					damage -= 3;
				}
				if (chest.getItem() == thewizardmod.items.StartupCommon.robeChestplate) {
					POWER += 8;
					damage -= 3;
				}
			}

			if (head != null) {
				if (head.getItem() == thewizardmod.items.StartupCommon.ironHelmet) {
					POWER += 3;
					damage -= 3;
				}
				if (head.getItem() == thewizardmod.items.StartupCommon.robeHelmet) {
					POWER += 5;
					damage -= 3;
				}
			}
		}
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if (nbtTagCompound == null || !nbtTagCompound.hasKey("hasMagic")
				|| nbtTagCompound.getBoolean("hasMagic") != true) {
			return stack;
		}
		// System.out.println(stack.getMaxDamage() + " " +
		// stack.getItemDamage());
		if (stack.getItemDamage() + damage > stack.getMaxDamage()) {
			return stack;
		}
		int typeOfMagic = nbtTagCompound.getInteger("typeOfMagic");

		switch (typeOfMagic) {
		case 1:
			new wandFocusFlamethrowerEffect(worldIn, entityLiving, POWER);
			break;
		case 2:
			new wandFocusLightningBolt(worldIn, entityLiving, POWER);
			break;
		case 3:
			new wandFocusLight(worldIn, entityLiving, POWER / 4);
			break;
		case 4:
			new wandFocusGrowth(worldIn, entityLiving);
			break;
		case 5:
			new wandFocusMining(worldIn, entityLiving);
			break;
		case 6:
			new wandFocusVines(worldIn, entityLiving);
			break;
		case 7:
			new wandFocusWeb(worldIn, entityLiving, POWER / 2);
			break;
		case 8:
			new wandFocusIce(worldIn, entityLiving, POWER / 2);
			break;
		case 9:
			new wandFocusTeleport(worldIn, entityLiving, POWER);
			break;
		}
		stack.setItemDamage(stack.getItemDamage() + damage);
		return stack;

	}

	public static boolean onRightClick(ItemStack itemStackIn, World worldIn,
			EntityPlayer playerIn, EnumHand hand) {
		NBTTagCompound nbtTagCompound = itemStackIn.getTagCompound();

		boolean hasMagic = false;
		if (nbtTagCompound != null && nbtTagCompound.hasKey("hasMagic")) {
			hasMagic = nbtTagCompound.getBoolean("hasMagic");
		}
		int typeOfMagic = 0;
		if (nbtTagCompound != null && nbtTagCompound.hasKey("typeOfMagic")) {
			typeOfMagic = nbtTagCompound.getInteger("typeOfMagic");
		}
		int firstFocus = 0;
		if (nbtTagCompound != null && nbtTagCompound.hasKey("firstFocus")) {
			firstFocus = nbtTagCompound.getInteger("firstFocus");
		}

		if (playerIn.isSneaking() && hasMagic) { // shift pressed
			if (nbtTagCompound == null) {
				nbtTagCompound = new NBTTagCompound();
				itemStackIn.setTagCompound(nbtTagCompound);
			}
			boolean nextFound = false;
			for (int i = 1; i < MAX_MAGIC_TYPES + 2; i++) {
				switch (i) {
				case 1:
					if (nbtTagCompound != null
							&& nbtTagCompound.hasKey("hasFlamethrower")
							&& nbtTagCompound.getBoolean("hasFlamethrower") == true) {
						if (typeOfMagic < i) {
							typeOfMagic = i;
							nextFound = true;
							break;
						}
					}
					break;
				case 2:
					if (nbtTagCompound != null
							&& nbtTagCompound.hasKey("hasLightning")
							&& nbtTagCompound.getBoolean("hasLightning") == true) {
						if (typeOfMagic < i) {
							typeOfMagic = i;
							nextFound = true;
							break;
						}
					}
					break;
				case 3:
					if (nbtTagCompound != null
							&& nbtTagCompound.hasKey("hasLight")
							&& nbtTagCompound.getBoolean("hasLight") == true) {
						if (typeOfMagic < i) {
							typeOfMagic = i;
							nextFound = true;
							break;
						}
					}
					break;
				case 4:
					if (nbtTagCompound != null
							&& nbtTagCompound.hasKey("hasGrowth")
							&& nbtTagCompound.getBoolean("hasGrowth") == true) {
						if (typeOfMagic < i) {
							typeOfMagic = i;
							nextFound = true;
							break;
						}
					}
					break;
				case 5:
					if (nbtTagCompound != null
							&& nbtTagCompound.hasKey("hasMining")
							&& nbtTagCompound.getBoolean("hasMining") == true) {
						if (typeOfMagic < i) {
							typeOfMagic = i;
							nextFound = true;
							break;
						}
					}
					break;
				case 6:
					if (nbtTagCompound != null
							&& nbtTagCompound.hasKey("hasVines")
							&& nbtTagCompound.getBoolean("hasVines") == true) {
						if (typeOfMagic < i) {
							typeOfMagic = i;
							nextFound = true;
							break;
						}
					}
					break;
				case 7:
					if (nbtTagCompound != null
							&& nbtTagCompound.hasKey("hasWeb")
							&& nbtTagCompound.getBoolean("hasWeb") == true) {
						if (typeOfMagic < i) {
							typeOfMagic = i;
							nextFound = true;
							break;
						}
					}
					break;
				case 8:
					if (nbtTagCompound != null
							&& nbtTagCompound.hasKey("hasIce")
							&& nbtTagCompound.getBoolean("hasIce") == true) {
						if (typeOfMagic < i) {
							typeOfMagic = i;
							nextFound = true;
							break;
						}
					}
					break;
				case 9:
					if (nbtTagCompound != null
							&& nbtTagCompound.hasKey("hasTeleport")
							&& nbtTagCompound.getBoolean("hasTeleport") == true) {
						if (typeOfMagic < i) {
							typeOfMagic = i;
							nextFound = true;
							break;
						}
					}
					break;
				default:
					typeOfMagic = firstFocus;
					nextFound = true;
					break;
				}

				if (nextFound)
					break;
			}

			nbtTagCompound.setInteger("typeOfMagic", typeOfMagic);
			/*
			 * if (worldIn.isRemote) { switch(typeOfMagic){ case 1:
			 * playerIn.addChatComponentMessage(new
			 * TextComponentString("Selected magic Flame thrower")); break; case
			 * 2: playerIn.addChatComponentMessage(new
			 * TextComponentString("Selected magic Lightning stroke")); break;
			 * case 3: playerIn.addChatComponentMessage(new
			 * TextComponentString("Selected magic Light")); break; case 4:
			 * playerIn.addChatComponentMessage(new
			 * TextComponentString("Selected magic Growth")); break; } }
			 */

			return true;
		}

		if (typeOfMagic == 0 && hasMagic) {
			if (worldIn.isRemote) {
				playerIn.addChatComponentMessage(new TextComponentString(
						"Sneak and right click to select type of magic."));
			}
			return false;
		}

		if (hasMagic) {
			playerIn.setActiveHand(hand); // start the charge up sequence
			return true;
		} else {
			if (worldIn.isRemote) { // only on the client side, else you will
									// get two messages..
				playerIn.addChatComponentMessage(new TextComponentString(
						"No magic without magic!"));
			}
			return false;
		}
	}

}
