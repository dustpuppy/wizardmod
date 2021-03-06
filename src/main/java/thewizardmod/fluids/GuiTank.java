package thewizardmod.fluids;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thewizardmod.Gui.ProgressBar;
import thewizardmod.Gui.ProgressBar.ProgressBarDirection;
import thewizardmod.Util.CapabilityUtils;

/**
 * User: brandon3055
 * Date: 06/01/2015
 *
 * GuiInventoryBasic is a simple gui that does nothing but draw a background image and a line of text on the screen
 * everything else is handled by the vanilla container code
 */
@SideOnly(Side.CLIENT)
public class GuiTank extends GuiContainer {
	private ProgressBar progressBar;
	
	// This is the resource location for the background image for the GUI
	private static final ResourceLocation texture = new ResourceLocation("thewizardmod", "textures/gui/tank_bg.png");
	private TileEntityTank tileEntityTank;

	public GuiTank(InventoryPlayer invPlayer, TileEntityTank tile) {
		super(new ContainerTank(invPlayer, tile));
		tileEntityTank = tile;
		this.progressBar = new ProgressBar(texture, ProgressBarDirection.DOWN_TO_UP, 10, 62, 9, 9, 176, 14);
	}

	// draw the background for the GUI - rendered first
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
		// Bind the image texture of our custom container
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		// Draw the image
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	// draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
	// renders relative to the top left corner of the background
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString("Liquid Magic Tank", 50, 5, Color.darkGray.getRGB());
		final IFluidHandler fluidHandler = getFluidHandler();
		if (fluidHandler != null) {
			fontRendererObj.drawString("Capacity : " + Integer.toString(fluidHandler.getTankProperties()[0].getCapacity() - 1) + " mB", 50, 36, Color.darkGray.getRGB());
	        FluidStack fluid = fluidHandler.getTankProperties()[0].getContents();
	        if (fluid != null)
	        {
	        	fontRendererObj.drawString("Amount   : " + Integer.toString(fluid.amount - 1) + " mB", 50, 58, Color.darkGray.getRGB());
	    		this.progressBar.setMin(fluid.amount - 1).setMax(fluidHandler.getTankProperties()[0].getCapacity() - 1);
	    		this.progressBar.draw(this.mc);
	        }
		}
	}
	
	private IFluidHandler getFluidHandler() {
		return CapabilityUtils.getCapability(tileEntityTank, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
	}
	
	

}
