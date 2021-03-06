package thewizardmod.runeSlab;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RendererRuneSlab extends
		TileEntitySpecialRenderer<TileEntityRuneSlab> {

	private ResourceLocation texture;

	@Override
	public void renderTileEntityAt(TileEntityRuneSlab te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		TileEntityRuneSlab tileEntity = (TileEntityRuneSlab) te;

		if (tileEntity.getRuneType() > 0) 
		{
			if (tileEntity instanceof TileEntityRuneSlab) {

//				if (texture == null)
					texture = new ResourceLocation("thewizardmod:textures/runes/" + tileEntity.getRuneType() + ".png");
					if(texture != null)
					{
				GlStateManager.pushMatrix();

				GlStateManager.translate(x, y, z);
				GlStateManager.disableLighting();
				GlStateManager.disableCull();

				bindTexture(texture);
				GlStateManager.translate(0.15, 0.131, 0.15);
				GlStateManager.rotate(90, 1, 0, 0);
/*
				GlStateManager.translate(0.5, 0.5, 0);
				tileEntity.lastChangeTime++;
				if (tileEntity.lastChangeTime > 360) {
					tileEntity.lastChangeTime = 1;
				}
				GlStateManager.rotate(tileEntity.lastChangeTime, 0.0F, 0.0F, 0.1F);
				GlStateManager.translate(-0.5, -0.5, 0);
*/
				GlStateManager.scale(0.7F, 0.7F, 0.7F);

				Tessellator tessellator = Tessellator.getInstance();
				VertexBuffer vertexBuffer = tessellator.getBuffer();
				vertexBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
				vertexBuffer.pos(0, 0, 0).tex(0, 0).endVertex();
				vertexBuffer.pos(0, 1, 0).tex(0, 1).endVertex();
				vertexBuffer.pos(1, 1, 0).tex(1, 1).endVertex();
				vertexBuffer.pos(1, 0, 0).tex(1, 0).endVertex();
				tessellator.draw();

				GlStateManager.popMatrix();
					}
			}
		}
	}

}
