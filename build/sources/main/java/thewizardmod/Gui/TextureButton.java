package thewizardmod.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class TextureButton extends GuiButton{

private String location;

public TextureButton(int buttonId, int x, int y, int widthIn, int heightIn, String location)
    {

    super(buttonId, x, y, widthIn, heightIn, "");
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.location = location;
        this.displayString = "";

    }

public void setTooltip(int buttonID, String tooltip){
	if(buttonID == this.id){
		this.displayString = tooltip;
	}
}

@Override
public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(new ResourceLocation("thewizardmod", location));
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawModalRectWithCustomSizedTexture(this.xPosition, this.yPosition, 0, (k-1) * this.height, this.width, this.height, this.width, this.height * 2);
            this.mouseDragged(mc, mouseX, mouseY);
            
            int l = 14737632;

            if (packedFGColour != 0)
            {
                l = packedFGColour;
            }
            else if (!this.enabled)
            {
                l = 10526880;
            }
            else if (this.hovered)
            {
                l = 16777120;
            }

            if (k == 2 && this.displayString != ""){
            	drawString(fontrenderer, this.displayString, mouseX, this.height + mouseY, l);
            }
        
        }
    }
}
