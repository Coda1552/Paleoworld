package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.HaikouichthysModel;
import coda.paleoworld.common.entities.HaikouichthysEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class HaikouichthysRenderer extends MobRenderer<HaikouichthysEntity, HaikouichthysModel<HaikouichthysEntity>> {
   public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
      hashMap.put(0, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/green_haikouichthys.png"));
      hashMap.put(1, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/normal_haikouichthys.png"));
      hashMap.put(2, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/yellow_haikouichthys.png"));
      hashMap.put(3, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/classic_haikouichthys.png"));
   });

   public HaikouichthysRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new HaikouichthysModel<>(), 0.3F);
   }

   public ResourceLocation getTextureLocation(HaikouichthysEntity entity) {
      return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
   }

   protected void setupRotations(HaikouichthysEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
      super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
      float f = 4.3F * MathHelper.sin(0.6F * p_225621_3_);
      p_225621_2_.mulPose(Vector3f.YP.rotationDegrees(f));
      if (!p_225621_1_.isInWater()) {
         p_225621_2_.translate(0.1F, 0.1F, -0.1F);
         p_225621_2_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
      }
   }
}
