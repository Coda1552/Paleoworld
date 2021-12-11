package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.AnomalocarisModel;
import coda.paleoworld.common.entities.AnomalocarisEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnomalocarisRenderer extends MobRenderer<AnomalocarisEntity, AnomalocarisModel<AnomalocarisEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/anomalocaris.png");

   public AnomalocarisRenderer(EntityRendererProvider.Context manager) {
      super(manager, new AnomalocarisModel<>(manager.bakeLayer(AnomalocarisModel.LAYER)), 0.3F);
   }

   public ResourceLocation getTextureLocation(AnomalocarisEntity entity) {
      return TEXTURE;
   }
}
