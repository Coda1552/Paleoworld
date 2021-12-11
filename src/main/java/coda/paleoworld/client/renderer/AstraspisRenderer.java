package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.AstraspisModel;
import coda.paleoworld.common.entities.AstraspisEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AstraspisRenderer extends MobRenderer<AstraspisEntity, AstraspisModel<AstraspisEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/astraspis.png");

   public AstraspisRenderer(EntityRendererProvider.Context manager) {
      super(manager, new AstraspisModel<>(manager.bakeLayer(AstraspisModel.LAYER)), 0.3F);
   }

   public ResourceLocation getTextureLocation(AstraspisEntity entity) {
      return TEXTURE;
   }

   protected void setupRotations(AstraspisEntity p_225621_1_, PoseStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
      super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
      float f = 4.3F * Mth.sin(0.6F * p_225621_3_);
      p_225621_2_.mulPose(Vector3f.YP.rotationDegrees(f));
      if (!p_225621_1_.isInWater()) {
         p_225621_2_.translate(0.1F, 0.1F, -0.1F);
         p_225621_2_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
      }
   }
}
