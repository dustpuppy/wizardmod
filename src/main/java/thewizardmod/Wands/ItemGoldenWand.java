package thewizardmod.Wands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.TheWizardMod;
import thewizardmod.dimensions.StartupCommon;
import thewizardmod.wandHandling.WandHandling;
import thewizardmod.wandHandling.WandTimer;
import thewizardmod.wandHandling.wandFocusFlamethrowerEffect;
import thewizardmod.wandHandling.wandFocusGrowth;
import thewizardmod.wandHandling.wandFocusLight;
import thewizardmod.wandHandling.wandFocusLightningBolt;

public class ItemGoldenWand extends Item 
{
  // This parameters are differend for every wand
  static public final int DISTANCE_POWER = 80;
  static public final int MAX_FOCUS = 4;
  static public final int CHARGE_UP_INITIAL_PAUSE_TICKS = 3;
  static public final int CHARGE_UP_DURATION_TICKS = 8;
  static public final int MAX_DAMAGE = 300; 
  

	  
  public ItemGoldenWand() {
    this.setMaxDamage(MAX_DAMAGE);
    this.setHasSubtypes(false);
    this.setMaxStackSize(1);
    this.setCreativeTab(CreativeTabs.MISC);

    // IItemPropertyGetter does not exist on a dedicated server
    if (!TheWizardMod.proxy.isDedicatedServer()) {
      this.addPropertyOverride(new ResourceLocation("chargefraction"), new WandTimer());
    }
  }
  
  // Injecting magic will make the wand active
  // This method is called by the injector
  public static void injectMagic(ItemStack itemStackIn){
	  WandHandling.injectMagic(itemStackIn, MAX_FOCUS, DISTANCE_POWER);

  }
  
  // if the wand has magic, give it an glint "effect"
  @Override
  public boolean hasEffect(ItemStack stack) {
    NBTTagCompound nbtTagCompound = stack.getTagCompound();
    if (nbtTagCompound == null) return false;
    return nbtTagCompound.hasKey("hasMagic");
  }

  // what animation to use when the player holds the "use" button
  @Override
  public EnumAction getItemUseAction(ItemStack stack) {
    return EnumAction.BLOCK;
  }

  // how long the player needs to hold down the right button in order to activate the wand, in ticks
  @Override
  public int getMaxItemUseDuration(ItemStack stack) {
    return CHARGE_UP_DURATION_TICKS + CHARGE_UP_INITIAL_PAUSE_TICKS;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
  {
	  if(WandHandling.onRightClick(itemStackIn, worldIn, playerIn, hand)){
		  return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	  }
	  else
	  {
		  return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
	  }
  }

  // called when the player has held down the right click for the full charge-up duration
  @Override
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
  {
	  return WandHandling.useFinish(stack, worldIn, entityLiving, DISTANCE_POWER);
  }

  // adds 'tooltip' text
  @SideOnly(Side.CLIENT)
  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
    NBTTagCompound nbtTagCompound = stack.getTagCompound();
    if (nbtTagCompound != null && nbtTagCompound.hasKey("hasMagic") && nbtTagCompound.getBoolean("hasMagic") == true ) {
        tooltip.add("Hold down right click to do some magic.");
        tooltip.add("Sneak and right click to select type of magic.");
    }
    else
    {
      tooltip.add("Put into magic injector to");
      tooltip.add(" activate the wand.");
    }
  }
  /**
   * Called when a Block is right-clicked with this Item
   * We use it to activate the portal
   */
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
  {
	  if(worldIn.getBlockState(pos).getBlock() != StartupCommon.portalFrame)
	  {
		  return EnumActionResult.FAIL;
	  }
		  pos = pos.offset(facing);

		  if (!playerIn.canPlayerEdit(pos, facing, stack))
		  {
			  return EnumActionResult.FAIL;
		  }
		  else
		  {
			  if (worldIn.isAirBlock(pos))
			  {
				  worldIn.playSound(playerIn, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				  worldIn.setBlockState(pos, StartupCommon.magicFire.getDefaultState(), 11);
			  }

			  stack.damageItem(1, playerIn);
			  return EnumActionResult.SUCCESS;
		  }
  }


}