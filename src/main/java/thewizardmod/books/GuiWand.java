package thewizardmod.books;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiWand extends GuiScreen {
	private final int bookImageHeight = 192;
	private final int bookImageWidth = 192;
	private int currPage = 0;
	private static final int bookTotalPages = 7;
	private static ResourceLocation[] bookPageTextures = new ResourceLocation[bookTotalPages];
	private static String[] stringPageText = new String[bookTotalPages];
	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;

	public GuiWand() {

		bookPageTextures[0] = new ResourceLocation("thewizardmod:textures/gui/books/wand_magic_cover.png");
		bookPageTextures[1] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[2] = new ResourceLocation("thewizardmod:textures/gui/books/woodenwand.png");
		bookPageTextures[3] = new ResourceLocation("thewizardmod:textures/gui/books/ironwand.png");
		bookPageTextures[4] = new ResourceLocation("thewizardmod:textures/gui/books/goldenwand.png");
		bookPageTextures[5] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[6] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");

		stringPageText[0] = "    The Magic Wands";

		stringPageText[1] = "What is a wizard without a wand? A nobody. So one of the first artifacts you want to craft is a wand. There are different kinds of wands, you can have. As better the material you use, as more upgrades you can put into the wand and as more power it has.";

		stringPageText[2] = "Wooden Wand\nCan hold up to 1 magic and his power is very low.";
		stringPageText[3] = "Iron Wand\nCan hold up to 2 magics and his power is accurate.";
		stringPageText[4] = "Golden Wand\nCan hold up to 4 magics and his power is very good.";
		stringPageText[5] = "Blaze Wand\nThe best wand a wizard can have. It's that power full and can hold that much magic, that a simple crafting is not possible. It needs to be crafted with an altar and you already need a Golden Wand.";
		stringPageText[6] = "A Blaze Wand can be only crafted with an altar.\n\nIncrements are:\n 1 Shadow Dust\n 1 Magic Gem\n 1 Blaze Rod\n 1 Stick";
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);

		int offsetFromScreenLeft = (width - bookImageWidth) / 2;
		buttonList.add(buttonNextPage = new NextPageButton(1, offsetFromScreenLeft + 120, 156, true));
		buttonList.add(buttonPreviousPage = new NextPageButton(2, offsetFromScreenLeft + 38, 156, false));
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen() {
		buttonNextPage.visible = (currPage < bookTotalPages - 1);
		buttonPreviousPage.visible = currPage > 0;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int parWidth, int parHeight, float p_73863_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(bookPageTextures[currPage]);
		
		int offsetFromScreenLeft = (width - bookImageWidth) / 2;
		drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, bookImageWidth, bookImageHeight);
		
		int widthOfString;
		String stringPageIndicator = I18n.format("book.pageIndicator", new Object[] { Integer.valueOf(currPage + 1), bookTotalPages });

		if(currPage > 0)
		{
			widthOfString = fontRendererObj.getStringWidth(stringPageIndicator);
			fontRendererObj.drawString(stringPageIndicator, offsetFromScreenLeft - widthOfString + bookImageWidth - 44, 18, 0);
		}

		fontRendererObj.drawSplitString(stringPageText[currPage], offsetFromScreenLeft + 36, 34, 116, 0);

		super.drawScreen(parWidth, parHeight, p_73863_3_);

	}

	/**
	 * Called when a mouse button is pressed and the mouse is moved around.
	 * 
	 * Parameters are : mouseX, mouseY, lastButtonClicked &
	 * 
	 * timeSinceMouseClick.
	 */
	@Override
	protected void mouseClickMove(int parMouseX, int parMouseY,

	int parLastButtonClicked, long parTimeSinceMouseClick)

	{

	}

	@Override
	protected void actionPerformed(GuiButton parButton) {
		if (parButton == buttonNextPage) {
			if (currPage < bookTotalPages - 1) {
				++currPage;
			}
		} else if (parButton == buttonPreviousPage) {
			if (currPage > 0) {
				--currPage;
			}
		}
	}

	/**
	 * Called when the screen is unloaded. Used to disable keyboard repeat
	 * 
	 * events
	 */
	@Override
	public void onGuiClosed() {

	}

	/**
	 * Returns true if this GUI should pause the game when it is displayed in
	 * 
	 * single-player
	 */
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	static class NextPageButton extends GuiButton {
		private final boolean isNextButton;

		public NextPageButton(int parButtonId, int parPosX, int parPosY,

		boolean parIsNextButton) {
			super(parButtonId, parPosX, parPosY, 23, 13, "");
			isNextButton = parIsNextButton;
		}

		/**
		 * Draws this button to the screen.
		 */
		@Override
		public void drawButton(Minecraft mc, int parX, int parY) {
			if (visible) {
				boolean isButtonPressed = (parX >= xPosition

				&& parY >= yPosition

				&& parX < xPosition + width

				&& parY < yPosition + height);

				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(bookPageTextures[1]);
				int textureX = 0;
				int textureY = 192;

				if (isButtonPressed) {
					textureX += 23;
				}

				if (!isNextButton) {
					textureY += 13;
				}

				drawTexturedModalRect(xPosition, yPosition,

				textureX, textureY,

				23, 13);
			}
		}
	}
}
