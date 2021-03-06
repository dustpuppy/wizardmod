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

public class GuiAltar extends GuiScreen {
	private final int bookImageHeight = 192;
	private final int bookImageWidth = 192;
	private int currPage = 0;
	private static final int bookTotalPages = 16;
	private static ResourceLocation[] bookPageTextures = new ResourceLocation[bookTotalPages];
	private static String[] stringPageText = new String[bookTotalPages];
	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;

	public GuiAltar() {

		bookPageTextures[0] = new ResourceLocation("thewizardmod:textures/gui/books/altar_magic_cover.png");
		bookPageTextures[1] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[2] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[3] = new ResourceLocation("thewizardmod:textures/gui/books/altar1.png");
		bookPageTextures[4] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[5] = new ResourceLocation("thewizardmod:textures/gui/books/altar2.png");
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

		stringPageText[0] = "    The Magic Altar";

		stringPageText[1] = "The Altar is a wizards crafting table. Some of the magic artifacts need more then just a simple crafting grid, because to inject magic into the material a lot of magical power is needed.";
				

		stringPageText[2] = "To build an altar you need the altar block in the middle and 4 pedetrals around with a cap of 2 blocks in between.";
		stringPageText[3] = "A simple altar";
		stringPageText[4] = "You can upgrade the altar by putting rune stones, magic torches and other magical artifacts around it. Be carefull! Some will help you, but others can be dangerous for your magic.";
		stringPageText[5] = "2 examples of altars with rune stones and torches.";
		stringPageText[6] = "To craft with an altar put the 4 increments onto the 4 pedestrals and click with a Golden Wand or a Blaze Wand the altar block. Your chances to get back some of the increments will raise, if you are wearing a Magic Iron Armor or better a Magic Robe.";
		stringPageText[7] = "You also can raise the chances with the rune stones, torches or other magical stuff. But allways be beware, that magic can be dangerous. Not every rune is good for it and to much magical artifacts can also raise the chance, that you loose all material.";
		stringPageText[8] = "To paint a rune on one of the rune stones, you need to craft the rune with the altar and right click the rune stone with it. To clean a rune stone simply use a blank rune. Recipes for rune you find in the book of runes.";
		stringPageText[9] = "On the next sides you will find some recipes for altar magic.";
		stringPageText[10] = "     Magic Chest\n\nIncrements:\n 1 Shadow Dust\n 1 Magic Gem\n 1 Minecraft Clock\n 1 Minecraft Chest";
		stringPageText[11] = "     Blank Rune\n\nIncrements:\n 1 Stone\n 1 Redstone\n 1 Shadow Dust\n 1 Magic Gem";
		stringPageText[12] = "     Magic Mirror\n\nIncrements\n 1 Mirror Glass\n 1 Gold Ingot\n 1 Ender Pearl\n 1 Shadow Dust";
		stringPageText[13] = "     Magic Tank\n\nIncrements\n 1 Glass Block\n 1 Iron Ingot\n 1 Bucket\n 1 Shadow Dust";
		stringPageText[14] = "     Necromancer Bone\n\nIncrements\n 1 Gold Nugget\n 1 Bone\n 1 Heart of an Undead\n 1 Shadow Dust";
		stringPageText[15] = "     Golem Zombie\n\nIncrements\n 1 Rotten Flesh\n 1 Bone\n 1 Heart of an Undead\n 1 Magic Gem";
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
