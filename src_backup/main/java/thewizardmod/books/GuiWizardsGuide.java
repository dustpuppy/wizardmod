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

public class GuiWizardsGuide extends GuiScreen {
	private final int bookImageHeight = 192;
	private final int bookImageWidth = 192;
	private int currPage = 0;
	private static final int bookTotalPages = 32;
	private static ResourceLocation[] bookPageTextures = new ResourceLocation[bookTotalPages];
	private static String[] stringPageText = new String[bookTotalPages];
	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;

	public GuiWizardsGuide() {

		bookPageTextures[0] = new ResourceLocation("thewizardmod:textures/gui/books/wizards_guide_cover.png");
		bookPageTextures[1] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[2] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[3] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[4] = new ResourceLocation("thewizardmod:textures/gui/books/injector.png");
		bookPageTextures[5] = new ResourceLocation("thewizardmod:textures/gui/books/jar.png");
		bookPageTextures[6] = new ResourceLocation("thewizardmod:textures/gui/books/bread.png");
		bookPageTextures[7] = new ResourceLocation("thewizardmod:textures/gui/books/cookie.png");
		bookPageTextures[8] = new ResourceLocation("thewizardmod:textures/gui/books/smallbackpack.png");
		bookPageTextures[9] = new ResourceLocation("thewizardmod:textures/gui/books/normalbackpack.png");
		bookPageTextures[10] = new ResourceLocation("thewizardmod:textures/gui/books/bigbackpack.png");
		bookPageTextures[11] = new ResourceLocation("thewizardmod:textures/gui/books/chalice.png");
		bookPageTextures[12] = new ResourceLocation("thewizardmod:textures/gui/books/pedestral.png");
		bookPageTextures[13] = new ResourceLocation("thewizardmod:textures/gui/books/runeslab.png");
		bookPageTextures[14] = new ResourceLocation("thewizardmod:textures/gui/books/sword.png");
		bookPageTextures[15] = new ResourceLocation("thewizardmod:textures/gui/books/shovel.png");
		bookPageTextures[16] = new ResourceLocation("thewizardmod:textures/gui/books/pickaxe.png");
		bookPageTextures[17] = new ResourceLocation("thewizardmod:textures/gui/books/axe.png");
		bookPageTextures[18] = new ResourceLocation("thewizardmod:textures/gui/books/ironhelmet.png");
		bookPageTextures[19] = new ResourceLocation("thewizardmod:textures/gui/books/ironchestplate.png");
		bookPageTextures[20] = new ResourceLocation("thewizardmod:textures/gui/books/ironleggings.png");
		bookPageTextures[21] = new ResourceLocation("thewizardmod:textures/gui/books/ironboots.png");
		bookPageTextures[22] = new ResourceLocation("thewizardmod:textures/gui/books/robehelmet.png");
		bookPageTextures[23] = new ResourceLocation("thewizardmod:textures/gui/books/robechestplate.png");
		bookPageTextures[24] = new ResourceLocation("thewizardmod:textures/gui/books/robeleggings.png");
		bookPageTextures[25] = new ResourceLocation("thewizardmod:textures/gui/books/robeboots.png");
		bookPageTextures[26] = new ResourceLocation("thewizardmod:textures/gui/books/mirrorglas.png");
		bookPageTextures[27] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[28] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[29] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[30] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[31] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");

		stringPageText[0] = "   The Wizards Guide";

		stringPageText[1] = "Bla bla";

		stringPageText[2] = "Mod author a to lazy to write the book.";
		stringPageText[3] = "On the next sides you find Recipes for crafing.";
		stringPageText[4] = "";
		stringPageText[5] = "";
		stringPageText[6] = "";
		stringPageText[7] = "";
		stringPageText[8] = "";
		stringPageText[9] = "";
		stringPageText[10] = "";
		stringPageText[11] = "";
		stringPageText[12] = "";
		stringPageText[13] = "";
		stringPageText[14] = "";
		stringPageText[15] = "";
		stringPageText[16] = "";
		stringPageText[17] = "";
		stringPageText[18] = "";
		stringPageText[19] = "";
		stringPageText[20] = "";
		stringPageText[21] = "";
		stringPageText[22] = "";
		stringPageText[23] = "";
		stringPageText[24] = "";
		stringPageText[25] = "";
		stringPageText[26] = "";
		stringPageText[27] = "Wizards Guide\n 1 Book\n 1 Shadow Dust";
		stringPageText[28] = "Book of Machines\n 1 Book\n 1 Stone Pickaxe";
		stringPageText[29] = "Altar Guide\n 1 Book\n 1 Magic Iron";
		stringPageText[30] = "Book of Runes\n 1 Book\n 1 Stone";
		stringPageText[31] = "Book of Wands\n 1 Book\n 1 Stick";
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
