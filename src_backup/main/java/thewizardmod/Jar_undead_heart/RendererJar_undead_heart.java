package thewizardmod.Jar_undead_heart;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import thewizardmod.items.StartupCommon;

public class RendererJar_undead_heart extends TileEntitySpecialRenderer<TileEntityJar_undead_heart>{

	public EntityItem ITEM = new EntityItem(Minecraft.getMinecraft().theWorld, 0, 0, 0, new ItemStack(StartupCommon.heart));
	
	public static float start_size = 0.8F;
	public static float end_size = 0.85F;
	public static float act_size = 0.8F; 
	
	@Override
	public void renderTileEntityAt(TileEntityJar_undead_heart te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		TileEntityJar_undead_heart tileEntity = (TileEntityJar_undead_heart)te;
		if(tileEntity.item != null)
			if(ITEM == null || ITEM.getEntityItem().getItem() != tileEntity.item)
				ITEM = new EntityItem(tileEntity.getWorld(), x, y, z, new ItemStack(tileEntity.item));
		
		if(ITEM != null)
		{
		
			// For items that hover around
			ITEM.hoverStart = 0F;
			float pt = 0F;
//			float pt = partialTicks;

			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(x, y, z);
				GlStateManager.translate(0.5F, -0.1F, 0.5F);
				int d = tileEntity.getFacing() * 90;
				GlStateManager.rotate(d, 0.0F, 1.0F, 0.0F);
				GlStateManager.scale(act_size, act_size, act_size);
				if(tileEntity.lastChangeTime > 0)
				{
					if(act_size == end_size)
					{
						act_size = start_size;
					}
					else
					{
						act_size = end_size;
					}
					tileEntity.lastChangeTime = 0;
				}
//				System.out.println(act_size);
				if(te.counter == 1)
				{
					Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, pt, false);
//					GlStateManager.translate(0, 0, -0.0625F);
				}
			}
			GlStateManager.popMatrix();
		}
	}
	
}
