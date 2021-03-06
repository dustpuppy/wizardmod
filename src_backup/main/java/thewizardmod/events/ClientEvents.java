package thewizardmod.events;


import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import thewizardmod.KeyBindings.StartupClientOnly;
import thewizardmod.Wands.StartupCommon;


public class ClientEvents {

	@SubscribeEvent
	public void OnRenderGameOverlay(RenderGameOverlayEvent event){
		if(!event.isCancelable() && event.getType() == ElementType.EXPERIENCE){
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.thePlayer.getHeldItemMainhand() != null)
			{
				ItemStack stack = mc.thePlayer.getHeldItemMainhand();
				Item heldItem = stack.getItem();
				if(!mc.thePlayer.capabilities.isCreativeMode)
				{
//					System.out.println();
						if(heldItem == StartupCommon.woodenWand
						|| heldItem == StartupCommon.ironWand
						|| heldItem == StartupCommon.goldenWand
						|| heldItem == StartupCommon.blazeWand)
					{

						int posX = 7;
						int posY = event.getResolution().getScaledHeight() - 130;
						mc.renderEngine.bindTexture(new ResourceLocation("thewizardmod:textures/gui/hud_overlay.png"));
				
						int value = (int)(100.0F/(float)heldItem.getMaxDamage(stack)*(float)heldItem.getDamage(stack));
						mc.ingameGUI.drawTexturedModalRect(posX, posY, 0, 0, 8, 102);
						mc.ingameGUI.drawTexturedModalRect(posX + 1, posY + 1, 9, 0, 8, value);
						
					    NBTTagCompound nbtTagCompound = stack.getTagCompound();
					    int numFocus = 0;
					    if (nbtTagCompound != null && nbtTagCompound.hasKey("typeOfMagic")) {
					      numFocus = nbtTagCompound.getInteger("typeOfMagic");
					    }
						switch(numFocus)
						{
						case 1:
							mc.ingameGUI.drawTexturedModalRect(posX - 3, posY + 107, 17, 0, 16, 16);
							break;
						case 2:
							mc.ingameGUI.drawTexturedModalRect(posX - 3, posY + 107, 33, 0, 16, 16);
							break;
						case 3:
							mc.ingameGUI.drawTexturedModalRect(posX - 2, posY + 107, 49, 0, 16, 16);
							break;
						case 4:
							mc.ingameGUI.drawTexturedModalRect(posX - 3, posY + 107, 65, 0, 16, 16);
							break;
						case 5:
							mc.ingameGUI.drawTexturedModalRect(posX - 3, posY + 107, 81, 0, 16, 16);
							break;
						case 6:
							mc.ingameGUI.drawTexturedModalRect(posX - 3, posY + 107, 97, 0, 16, 16);
							break;
						case 7:
							mc.ingameGUI.drawTexturedModalRect(posX - 3, posY + 107, 115, 0, 16, 16);
							break;
						case 8:
							mc.ingameGUI.drawTexturedModalRect(posX - 3, posY + 107, 131, 0, 16, 16);
							break;
						case 9:
							mc.ingameGUI.drawTexturedModalRect(posX - 3, posY + 107, 147, 0, 16, 16);
							break;
						}
						
					}
				}
			}
		}
	}
	@SubscribeEvent
	public void onKeyboardClick(ClientTickEvent event){
		if(StartupClientOnly.MY_KEYBINDING.isKeyDown()){
			System.out.println(StartupClientOnly.MY_KEYBINDING.getKeyDescription() + " pressed");
		}
	}
}
