package thewizardmod.entity;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

@SideOnly(Side.CLIENT)
public class RenderMiniZombie extends RenderBiped<EntityMiniZombie>
{
    private ResourceLocation mobTexture = new ResourceLocation("thewizardmod:textures/entity/weirdzombie.png");
    public static final Factory FACTORY = new Factory();


    private final ModelBiped defaultModel;
    private final List<LayerRenderer<EntityMiniZombie>> defaultLayers;

    public RenderMiniZombie(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelZombie(), 0.5F, 1.0F);
        LayerRenderer<?> layerrenderer = (LayerRenderer)this.layerRenderers.get(0);
        this.defaultModel = this.modelBipedMain;
        this.addLayer(new LayerHeldItem(this));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.defaultLayers = Lists.newArrayList(this.layerRenderers);

        if (layerrenderer instanceof LayerCustomHead)
        {
            this.removeLayer(layerrenderer);
        }

        this.removeLayer(layerbipedarmor);
        this.addLayer(new LayerVillagerArmor(this));
    }

    
    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityMiniZombie entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        this.setRenderOutlines(false);
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityMiniZombie entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.swapArmor(entity);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMiniZombie entity)
    {
        return mobTexture;
    }
    public static class Factory implements IRenderFactory<EntityMiniZombie> {

        @Override
        public Render<? super EntityMiniZombie> createRenderFor(RenderManager manager) {
            return new RenderMiniZombie(manager);
        }

    }

    private void swapArmor(EntityMiniZombie entity)
    {
            this.mainModel = this.defaultModel;
            this.layerRenderers = this.defaultLayers;

        this.modelBipedMain = (ModelBiped)this.mainModel;
    }

    protected void rotateCorpse(EntityMiniZombie entityLiving, float p_77043_2_, float p_77043_3_, float partialTicks)
    {
        super.rotateCorpse(entityLiving, p_77043_2_, p_77043_3_, partialTicks);
    }


}
