package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.GlyptodonModel;
import coda.paleoworld.common.entities.GlyptodonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlyptodonRenderer extends MobRenderer<GlyptodonEntity, GlyptodonModel<GlyptodonEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/glyptodon/glyptodon.png");

   public GlyptodonRenderer(EntityRendererProvider.Context manager) {
      super(manager, new GlyptodonModel<>(manager.bakeLayer(GlyptodonModel.LAYER)), 1.0F);
      this.addLayer(new SaddleLayer<>(this, new GlyptodonModel<>(manager.bakeLayer(GlyptodonModel.LAYER)), new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/glyptodon/saddle.png")));
   }

   public ResourceLocation getTextureLocation(GlyptodonEntity entity) {
      return TEXTURE;
   }
}
