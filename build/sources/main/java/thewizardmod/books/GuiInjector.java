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

public class GuiInjector extends GuiScreen {
	private final int bookImageHeight = 192;
	private final int bookImageWidth = 192;
	private int currPage = 0;
	private static final int bookTotalPages = 20;
	private static ResourceLocation[] bookPageTextures = new ResourceLocation[bookTotalPages];
	private static String[] stringPageText = new String[bookTotalPages];
	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;

	public GuiInjector() {

		bookPageTextures[0] = new ResourceLocation("thewizardmod:textures/gui/books/injector_cover.png");
		bookPageTextures[1] = new ResourceLocation("thewizardmod:textures/gui/books/book.png");
		bookPageTextures[2] = new ResourceLocation("thewizardmod:textures/gui/books/injector.png");
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

		stringPageText[0] = " The Magic Injector";

		stringPageText[1] = "The magic Injector is needed to inject all kind of magic into all kind of artifacts. It has 2 Input slots. One is for the artifact you want to get the magic and one for the needed increment.";
				

		stringPageText[2] = "The Injector is crafted like this.";
		stringPageText[3] = "On the next sides you find the recipes for the magic injector.";
		stringPageText[4] = "Input Slot:\nA Wand\nFuel Slot:\nShadow Dust\n\nInjects Magic into a Wand. This needs to be done first after you've crafted a wand and every time to refill it.";
		stringPageText[5] = "Input Slot:\nA Fire Charge\nFuel Slot:\nShadow Dust\n\nInjects Flamethrower Magic into the Wand.";
		stringPageText[6] = "Input Slot:\nA Nether Star\nFuel Slot:\nShadow Dust\n\nInjects Lightning Bolt Magic into Wand";
		stringPageText[7] = "Input Slot:\nA Magic Torch\nFuel Slot:\nShadow Dust\n\nInjects Light into Wand";
		stringPageText[8] = "Input Slot:\nA Magic Seed\nFuel Slot:\nShadow Dust\n\nInjects Groth into Wand";
		stringPageText[9] = "Input Slot:\nA Vine\nFuel Slot:\nShadow Dust\n\nInjects Climbing into Wand";
		stringPageText[10] = "Input Slot:\nA Block of Ice\nFuel Slot:\nShadow Dust\n\nInjects Ice and Snow into Wand";
		stringPageText[11] = "Input Slot:\nA Cob Web\nFuel Slot:\nShadow Dust\n\nInjects Web throwing into Wand";
		stringPageText[12] = "Input Slot:\nA Magic Pickaxe\nFuel Slot:\nShadow Dust\n\nInjects Mining into Wand";
		stringPageText[13] = "Input Slot:\nA Ender Pearl\nFuel Slot:\nShadow Dust\n\nInjects Teleportation into Wand";
		stringPageText[14] = "Input Slot:\nA Torch\nFuel Slot:\nShadow Dust\n\nGives out a Magic Torch";
		stringPageText[15] = "Input Slot:\nA Diamond\nFuel Slot:\nShadow Dust\n\nGives out a Magic Gem";
		stringPageText[16] = "Input Slot:\nA Iron Ingot\nFuel Slot:\nShadow Dust\n\nGives out a Magic Iron Ingot";
		stringPageText[17] = "Input Slot:\nA Glass\nFuel Slot:\nShadow Dust\n\nGives out a Magic Wall";
		stringPageText[18] = "Input Slot:\nA String\nFuel Slot:\nShadow Dust\n\nGives out a Magic String";
		stringPageText[19] = "Input Slot:\nA Magic Wall\nFuel Slot:\nShadow Dust\n\nGives out a Magic Block";
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
