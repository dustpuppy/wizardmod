package thewizardmod.entity;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import org.lwjgl.opengl.GL11;

public class GuiMiniZombie extends GuiScreen{

	public static EntityMiniZombie littleZombie;

	private final int imageHeight = 172;
	private final int imageWidth = 176;
	private GuiTextField zombieName;

	private static ResourceLocation backgroundTexture = new ResourceLocation("thewizardmod:textures/gui/entity/minizombie.png");

	@Override
	public void initGui() {
		int offsetFromScreenLeft = (width - imageWidth) / 2;
		buttonList.add(new GuiButton(0, offsetFromScreenLeft + 10, 120, 80, 20, "Item Picker"));
		buttonList.add(new GuiButton(1, offsetFromScreenLeft + 10, 140, 80, 20, "Transporter"));
		
		zombieName = new GuiTextField(1, fontRendererObj, offsetFromScreenLeft + 50, 27, 100, 16);
		zombieName.setMaxStringLength(20);
		zombieName.setFocused(true);
		zombieName.setCanLoseFocus(false);
		
		
	}

	public static void setup(EntityMiniZombie entity)
	{
		littleZombie = entity;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int offsetFromScreenLeft = (width - imageWidth) / 2;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(backgroundTexture);
		
		drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, imageWidth, imageHeight);
		
		fontRendererObj.drawString("Zombie Properties", offsetFromScreenLeft + 10, 10, 16777215);
		fontRendererObj.drawString("Name  :", offsetFromScreenLeft + 10, 30, 16777215);
		
		if(littleZombie != null)
		{
			zombieName.setText(littleZombie.getName());
			zombieName.drawTextBox();
			
			BlockPos pos = littleZombie.inventoryPos;
			String posString;
			if(pos != null)
			{
				posString = pos.getX() + " " + pos.getY() + " " + pos.getZ();
			}
			else
			{
				posString = "not set";
			}
			fontRendererObj.drawString("Home  : " + posString, offsetFromScreenLeft + 10, 50, 16777215);
			pos = littleZombie.sourcePos;

			if(pos != null)
			{	
				posString = pos.getX() + " " + pos.getY() + " " + pos.getZ();
			}
			else
			{
				posString = "not set";
			}	
			fontRendererObj.drawString("Soure : " + posString, offsetFromScreenLeft + 10, 60, 16777215);
			String jobString = "not set";
			if(littleZombie.isPicker)
			{
				jobString = "Item Picker";
			}
			else if(littleZombie.isTransporter)
			{
				jobString = "Transporter";
			}
			fontRendererObj.drawString("Job type : " + jobString, offsetFromScreenLeft + 10, 80, 16777215);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id)
		{
		case 0:
			if(littleZombie != null)
			{
				littleZombie.clearProfession();
				littleZombie.setPicker();
			}
			
			break;
		case 1:
			if(littleZombie != null)
			{
				littleZombie.clearProfession();
				littleZombie.setTransporter();
			}
			
			break;
		}
		super.actionPerformed(button);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		zombieName.textboxKeyTyped(typedChar, keyCode);
		if(keyCode == 28)		// return
		{
			littleZombie.setName(zombieName.getText());
		}
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	public void updateScreen() {
//		zombieName.updateCursorCounter();
		super.updateScreen();
	}
}
