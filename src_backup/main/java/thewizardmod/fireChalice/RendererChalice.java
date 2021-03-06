package thewizardmod.fireChalice;

import thewizardmod.dimensions.StartupCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RendererChalice extends TileEntitySpecialRenderer<TileEntityChalice>{

	public EntityItem ITEM;
	
	@Override
	public void renderTileEntityAt(TileEntityChalice te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		TileEntityChalice tileEntity = (TileEntityChalice)te;
		Item item = StartupCommon.itemFire;
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
				GlStateManager.translate(0.5F, 0.4F, 0.5F);
				GlStateManager.scale(1.0F, 2F, 1.0F);
				tileEntity.lastChangeTime++;
				if (tileEntity.lastChangeTime > 360)
				{
					tileEntity.lastChangeTime = 1;
				}
				if(te.counter == 1)
				{
					GlStateManager.rotate(tileEntity.lastChangeTime, 0.0F, 1.0F, 0.0F);
					Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, pt, false);

					GlStateManager.rotate(tileEntity.lastChangeTime, 0.0F, 1.25F, 0.0F);
					Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, pt, false);

				}
			}
			GlStateManager.popMatrix();

		}
	}
	
}
