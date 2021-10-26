package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.GlyptodonModel;
import coda.paleoworld.common.entities.GlyptodonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlyptodonRenderer extends MobRenderer<GlyptodonEntity, GlyptodonModel<GlyptodonEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/glyptodon.png");

   public GlyptodonRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new GlyptodonModel<>(), 1.0F);
   }

   public ResourceLocation getTextureLocation(GlyptodonEntity entity) {
      return TEXTURE;
   }
}
