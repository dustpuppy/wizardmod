package thewizardmod.altar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RendererAltar extends TileEntitySpecialRenderer<TileEntityAltar>{

	public EntityItem ITEM;
	
	@Override
	public void renderTileEntityAt(TileEntityAltar te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		TileEntityAltar tileEntity = (TileEntityAltar)te;
		Item item = tileEntity.getItem();
		if(item != null)
		{
			if(ITEM == null || ITEM.getEntityItem().getItem() != item)
			{
				ITEM = new EntityItem(tileEntity.getWorld(), x, y, z, new ItemStack(item));
			}
		}
		
		if(ITEM != null)
		{
			ITEM.hoverStart = 0F;
			float pt = 0F;

			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(x, y, z);
				GlStateManager.translate(0.5F, 1.0F, 0.5F);
				GlStateManager.scale(1.2F, 1.2F, 1.2F);
				tileEntity.lastChangeTime++;
				if (tileEntity.lastChangeTime > 360)
				{
					tileEntity.lastChangeTime = 1;
				}
				GlStateManager.rotate(tileEntity.lastChangeTime, 0.0F, 1.0F, 0.0F);
				if(te.counter == 1)
				{
					Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, pt, false);
				}
			}
			GlStateManager.popMatrix();

		}
	}
	
}
