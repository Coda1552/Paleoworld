package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.TrilobiteModel;
import coda.paleoworld.common.TrilobiteEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class TrilobiteRenderer extends MobRenderer<TrilobiteEntity, TrilobiteModel<TrilobiteEntity>> {
   public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
      hashMap.put(0, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/green_Trilobite.png"));
      hashMap.put(1, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/normal_Trilobite.png"));
      hashMap.put(2, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/yellow_Trilobite.png"));
      hashMap.put(3, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/classic_Trilobite.png"));
   });

   public TrilobiteRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new TrilobiteModel<>(), 0.3F);
   }

   public ResourceLocation getTextureLocation(TrilobiteEntity entity) {
      return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
   }
}
