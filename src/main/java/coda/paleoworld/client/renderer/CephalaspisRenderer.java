package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.CephalaspisModel;
import coda.paleoworld.common.entities.CephalaspisEntity;
import coda.paleoworld.common.entities.CephalaspisEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CephalaspisRenderer extends MobRenderer<CephalaspisEntity, CephalaspisModel<CephalaspisEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/cephalaspis.png");

   public CephalaspisRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new CephalaspisModel<>(), 0.3F);
   }

   public ResourceLocation getTextureLocation(CephalaspisEntity entity) {
      return TEXTURE;
   }

   protected void setupRotations(CephalaspisEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
      super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
      float f = 4.3F * MathHelper.sin(0.6F * p_225621_3_);
      p_225621_2_.mulPose(Vector3f.YP.rotationDegrees(f));
      if (!p_225621_1_.isInWater()) {
         p_225621_2_.translate(0.1F, 0.1F, -0.1F);
         p_225621_2_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
      }
   }
}
