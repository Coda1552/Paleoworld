package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.CephalaspisModel;
import coda.paleoworld.common.entities.CephalaspisEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class CephalaspisRenderer extends MobRenderer<CephalaspisEntity, CephalaspisModel<CephalaspisEntity>> {
   public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
      hashMap.put(0, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/cephalaspis_green.png"));
      hashMap.put(1, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/cephalaspis_yellow.png"));
   });

   public CephalaspisRenderer(EntityRendererProvider.Context manager) {
      super(manager, new CephalaspisModel<>(manager.bakeLayer(CephalaspisModel.LAYER)), 0.3F);
   }

   public ResourceLocation getTextureLocation(CephalaspisEntity entity) {
      return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
   }

   protected void setupRotations(CephalaspisEntity p_225621_1_, PoseStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
      super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
      float f = 4.3F * Mth.sin(0.6F * p_225621_3_);
      p_225621_2_.mulPose(Vector3f.YP.rotationDegrees(f));
      if (!p_225621_1_.isInWater()) {
         p_225621_2_.translate(0.1F, 0.1F, -0.1F);
         p_225621_2_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
      }
   }
}
