package thewizardmod.extractor;

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

@SideOnly(Side.CLIENT)
public class GuiExtractor extends GuiContainer {
	private ProgressBar TankGauge;
	private ProgressBar progressBar;
	private ProgressBar burnBar;
	
	// This is the resource location for the background image for the GUI
	private static final ResourceLocation texture = new ResourceLocation("thewizardmod", "textures/gui/extractor_bg.png");
	private TileEntityExtractor tileEntityTank;

	public GuiExtractor(InventoryPlayer invPlayer, TileEntityExtractor tile) {
		super(new ContainerExtractor(invPlayer, tile));
		tileEntityTank = tile;
		this.TankGauge = new ProgressBar(texture, ProgressBarDirection.DOWN_TO_UP, 10, 62, 9, 9, 176, 14);
		this.progressBar = new ProgressBar(texture, ProgressBarDirection.LEFT_TO_RIGHT, 35, 17, 98, 18, 195, 0);
		this.burnBar = new ProgressBar(texture, ProgressBarDirection.DOWN_TO_UP, 14, 14, 81, 39, 195, 20);
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
		fontRendererObj.drawString("Liquid Magic Extractor", 50, 5, Color.darkGray.getRGB());
		final IFluidHandler fluidHandler = getFluidHandler();
		if (fluidHandler != null) {
	        FluidStack fluid = fluidHandler.getTankProperties()[0].getContents();
	        if (fluid != null)
	        {
	    		this.TankGauge.setMin(fluid.amount - 1).setMax(fluidHandler.getTankProperties()[0].getCapacity() - 1);
	    		this.TankGauge.draw(this.mc);
	    		
	    		this.progressBar.setMin(tileEntityTank.coolDown).setMax(40);
	    		this.progressBar.draw(this.mc);
	    		
	    		this.burnBar.setMin(tileEntityTank.burntimeMax - tileEntityTank.burntime).setMax(tileEntityTank.burntimeMax);
	    		this.burnBar.draw(this.mc);
	        }
		}
	}
	
	private IFluidHandler getFluidHandler() {
		return CapabilityUtils.getCapability(tileEntityTank, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
	}
	
	

}
