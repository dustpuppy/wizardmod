package thewizardmod.magicInjector;

import thewizardmod.dimensions.StartupCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RendererMagicInjector extends TileEntitySpecialRenderer<TileEntityMagicInjector>{

	public EntityItem ITEM;
	
	@Override
	public void renderTileEntityAt(TileEntityMagicInjector te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		TileEntityMagicInjector tileEntity = (TileEntityMagicInjector)te;
		Item item = null;
		if(tileEntity.burnTimeRemaining[0] > 0)
		{
			item = StartupCommon.itemFire;
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
				int facing =((TileEntityMagicInjector) tileEntity).getFacing();
//				System.out.println(facing);
				if(facing == 0)
				{
					GlStateManager.translate(0.3F, 0.0F, 0.3F);
				}
				if(facing == 1)
				{
					GlStateManager.translate(0.0F, 0.0F, 0.0F);
				}
				if(facing == 2)
				{
					GlStateManager.translate(0.0F, 0.0F, 0.0F);
				}
				if(facing == 3)
				{
					GlStateManager.translate(0.0F, 0.0F, 0.0F);
				}
				GlStateManager.scale(0.5F, 1.5F, 0.5F);
				tileEntity.lastChangeTime +=5;
				if (tileEntity.lastChangeTime > 360)
				{
					tileEntity.lastChangeTime = 1;
				}
				GlStateManager.rotate(tileEntity.lastChangeTime, 0.0F, 1.0F, 0.0F);
				Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, pt, false);
				GlStateManager.rotate(tileEntity.lastChangeTime, 0.0F, 1.3F, 0.0F);
				Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, pt, false);
				GlStateManager.rotate(tileEntity.lastChangeTime, 0.0F, 1.6F, 0.0F);
				Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, pt, false);
			}
			GlStateManager.popMatrix();

		}
		}
	}
	
}
