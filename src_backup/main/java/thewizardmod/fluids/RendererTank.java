package thewizardmod.fluids;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import org.lwjgl.opengl.GL11;

import thewizardmod.Util.CapabilityUtils;

public class RendererTank extends TileEntitySpecialRenderer<TileEntityTank>{

	@Override
	public void renderTileEntityAt(TileEntityTank te, double x, double y, double z, float partialTicks, int destroyStage) {
		World world = Minecraft.getMinecraft().theWorld;
		
        final IFluidHandler fluidHandler = getFluidHandler(world, new BlockPos(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ()));
        if(fluidHandler != null)
        {
        int capacity = fluidHandler.getTankProperties()[0].getCapacity();
        FluidStack fluid = fluidHandler.getTankProperties()[0].getContents();
        if (fluid != null && fluid.amount > 1)
        {
            GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			GlStateManager.disableCull();
            Tessellator tess = Tessellator.getInstance();
            VertexBuffer buffer = tess.getBuffer();

            buffer.setTranslation(x, y, z);

            bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            TextureAtlasSprite still = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getStill().toString());
            TextureAtlasSprite flow =  Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getFlowing().toString());

            double posY = 0.0625F + (0.9 * ((float) fluid.amount / (float) capacity));
            float[] color = {1, 0, 0.3f, 0.3f};

            float low = 0.0625F;
            float high = 0.0625F * 15;
            
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            buffer.pos( low, 1F/16F, high).tex(still.getInterpolatedU(12), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, 1F/16F, high).tex(still.getInterpolatedU( 4), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, posY,   high).tex(still.getInterpolatedU( 4), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( low, posY,   high).tex(still.getInterpolatedU(12), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            tess.draw();

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            buffer.pos( low, 1F/16F,  low).tex(still.getInterpolatedU(12), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( low, posY,    low).tex(still.getInterpolatedU(12), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, posY,    low).tex(still.getInterpolatedU( 4), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, 1F/16F,  low).tex(still.getInterpolatedU( 4), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            tess.draw();

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            buffer.pos( low, posY,    low).tex(still.getInterpolatedU(12), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( low, posY,    high).tex(still.getInterpolatedU(12), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, posY,    high).tex(still.getInterpolatedU( 4), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, posY,    low).tex(still.getInterpolatedU( 4), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            tess.draw();

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            buffer.pos( low, 1F/16F,  low).tex(still.getInterpolatedU(12), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, 1F/16F,  low).tex(still.getInterpolatedU( 4), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, 1F/16F,  high).tex(still.getInterpolatedU( 4), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( low, 1F/16F,  high).tex(still.getInterpolatedU(12), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            tess.draw();

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            buffer.pos( high, 1F/16F,  low).tex(still.getInterpolatedU( 4), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, posY,    low).tex(still.getInterpolatedU( 4), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, posY,    high).tex(still.getInterpolatedU( 4), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( high, 1F/16F,  high).tex(still.getInterpolatedU( 4), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            tess.draw();

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            buffer.pos( low, 1F/16F,  low).tex(still.getInterpolatedU(12), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( low, 1F/16F,  high).tex(still.getInterpolatedU(12), still.getInterpolatedV(15)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( low, posY,    high).tex(still.getInterpolatedU(12), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            buffer.pos( low, posY,    low).tex(still.getInterpolatedU(12), still.getInterpolatedV( 1)).color(color[0], color[1], color[2], color[3]).endVertex();
            tess.draw();

            buffer.setTranslation(0, 0, 0);
            GlStateManager.popMatrix();
        }
        }
	
	}
	
	private IFluidHandler getFluidHandler(IBlockAccess world, BlockPos pos) {
		return CapabilityUtils.getCapability(world.getTileEntity(pos), CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
	}

}