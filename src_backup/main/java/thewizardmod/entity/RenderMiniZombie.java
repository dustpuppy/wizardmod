package thewizardmod.entity;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import org.lwjgl.opengl.GL11;

public class RenderMiniZombie extends RenderLiving<EntityMiniZombie> {

    private ResourceLocation mobTexture = new ResourceLocation("thewizardmod:textures/entity/weirdzombie.png");

    public static final Factory FACTORY = new Factory();

    public RenderMiniZombie(RenderManager rendermanagerIn) {
        // We use the vanilla zombie model here and we simply
        // retexture it. Of course you can make your own model
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }

 
    @Override
    protected void preRenderCallback(EntityMiniZombie entitylivingbaseIn, float partialTickTime)
    {
    	preRenderCallbackMiniZombie(entitylivingbaseIn, partialTickTime);
    	super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
    
    protected void preRenderCallbackMiniZombie(EntityMiniZombie entity, float f)

    {
    	// make our worker small as small can
        GL11.glScalef(0.5F, 0.5F, 0.5F); 
    }


    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityMiniZombie entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityMiniZombie> {

        @Override
        public Render<? super EntityMiniZombie> createRenderFor(RenderManager manager) {
            return new RenderMiniZombie(manager);
        }

    }

}