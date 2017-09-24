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

public class GuiRuneMagic extends GuiScreen {
	private final int bookImageHeight = 192;
	private final int bookImageWidth = 192;
	private int currPage = 0;
	private static final int bookTotalPages = 29;
	private static ResourceLocation[] bookPageTextures = new ResourceLocation[bookTotalPages];
	private static String[] stringPageText = new String[bookTotalPages];
	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;

	public GuiRuneMagic() {

		bookPageTextures[0] = new ResourceLocation("thewizardmod:textures/gui/books/rune_magic_cover.png");
		bookPageTextures[1] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[2] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[3] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[4] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[5] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[6] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[7] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[8] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[9] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[10] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[11] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[12] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[13] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[14] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[15] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[16] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[17] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[18] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[19] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[20] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[21] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[22] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[23] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[24] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[25] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[26] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[27] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[28] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");

		stringPageText[0] = "   Rune Magic Guide";

		stringPageText[1] = "Magic of Runes is a secrect, that was given from father to son over ages. It could bring you more then you could expect, but it also can be very dangerous.";

		stringPageText[2] = "You can apply Runes to Rune Stones around an altar, or just for other use by right clicking a rune stone with the rune.\n\nAll runes are made on the altar.";
		stringPageText[3] = "Blank Rune\n\nIncrements\n 1 Stone\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust";
		stringPageText[4] = "All Other Runes will be made with\n 1 Shadow Dust\n 1 Blank Rune\n 1 Magic Gem\n\nPlus one extra increment, like listet on the next sides.";
		stringPageText[5] = "Rune of Luck\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Gold Ingot";
		stringPageText[6] = "Rune of Power\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Blaze Powder";
		stringPageText[7] = "Rune of Fire\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Fire Charger";
		stringPageText[8] = "Rune of knowledge\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Book";
		stringPageText[9] = "Rune of growth\n\nLet Plants around growth\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Cherry Tree Sappling";
		stringPageText[10] = "Rune of Beast\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Spider Eye";
		stringPageText[11] = "Rune of Keeping\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Chest";
		stringPageText[12] = "Rune of Sign\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Banner";
		stringPageText[13] = "Rune of Attack\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Arrow";
		stringPageText[14] = "Rune of Earth\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Coal";
		stringPageText[15] = "Rune of Ice\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Ice";
		stringPageText[16] = "Rune of Plants\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Magic Seed";
		stringPageText[17] = "Rune of Cloth\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Magic String";
		stringPageText[18] = "Rune of Sun\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Rune of Fire";
		stringPageText[19] = "Rune of Defence\n\nTeleports Mobs around 20 Blocks up\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Shild";
		stringPageText[20] = "Rune of Weather\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Nether Star";
		stringPageText[21] = "Rune of the Rich\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Rune of Luck";
		stringPageText[22] = "Rune of Food\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Apple";
		stringPageText[23] = "Rune of Nature\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Rune of Knowledge";
		stringPageText[24] = "Rune of Poison\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Poison Potato";
		stringPageText[25] = "Rune of Health\n\nStand on it for Regeneration\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Ender Eye";
		stringPageText[26] = "Rune of Air\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Feather";
		stringPageText[27] = "Rune of Rest\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Bed";
		stringPageText[28] = "Rune of Energy\n\nIncrements\n 1 Blank Rune\n 1 Shadow Dust\n 1 Magic Gem\n 1 Redstone Dust\n 1 Flint and Steel";
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
