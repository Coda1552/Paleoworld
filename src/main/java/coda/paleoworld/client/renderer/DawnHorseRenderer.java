package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.DawnHorseModel;
import coda.paleoworld.common.entities.DawnHorseEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DawnHorseRenderer extends MobRenderer<DawnHorseEntity, DawnHorseModel<DawnHorseEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/dawn_horse.png");

   public DawnHorseRenderer(EntityRendererProvider.Context manager) {
      super(manager, new DawnHorseModel<>(manager.bakeLayer(DawnHorseModel.LAYER)), 0.25F);
   }

   public ResourceLocation getTextureLocation(DawnHorseEntity entity) {
      return TEXTURE;
   }
}
