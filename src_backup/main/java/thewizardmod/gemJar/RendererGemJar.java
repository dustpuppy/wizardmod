package thewizardmod.gemJar;

import thewizardmod.items.StartupCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RendererGemJar extends TileEntitySpecialRenderer<TileEntityGemJar>{

	public EntityItem ITEM = new EntityItem(Minecraft.getMinecraft().theWorld, 0, 0, 0, new ItemStack(StartupCommon.magicGem));
	
	@Override
	public void renderTileEntityAt(TileEntityGemJar te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		TileEntityGemJar tileEntity = (TileEntityGemJar)te;
		if(tileEntity.item != null)
			if(ITEM == null || ITEM.getEntityItem().getItem() != tileEntity.item)
				ITEM = new EntityItem(tileEntity.getWorld(), x, y, z, new ItemStack(tileEntity.item));
		
//		System.out.println(ITEM + " " + tileEntity.item);
		
		if(ITEM != null)
		{
		
			// For items that hover around
			ITEM.hoverStart = 0F;
			float pt = 0F;
//			float pt = partialTicks;
		
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(x, y, z);
				GlStateManager.rotate(90F, 1, 0, 0);
				GlStateManager.translate(0.5F, 0.1F, -0.1F);
				GlStateManager.scale(0.7F, 0.7F, 0.7F);
				for(int i = 0; i < te.counter; i++)
				{
					Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, pt, false);
					GlStateManager.translate(0, 0, -0.0625F);
				}
			}
			GlStateManager.popMatrix();
		}
	}
	
}
