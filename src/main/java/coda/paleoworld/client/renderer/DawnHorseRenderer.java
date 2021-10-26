package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.DawnHorseModel;
import coda.paleoworld.common.entities.DawnHorseEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DawnHorseRenderer extends MobRenderer<DawnHorseEntity, DawnHorseModel<DawnHorseEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/dawn_horse.png");

   public DawnHorseRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new DawnHorseModel<>(), 0.25F);
   }

   public ResourceLocation getTextureLocation(DawnHorseEntity entity) {
      return TEXTURE;
   }
}
