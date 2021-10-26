package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.TrilobiteModel;
import coda.paleoworld.common.entities.TrilobiteEntity;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class TrilobiteRenderer extends MobRenderer<TrilobiteEntity, TrilobiteModel<TrilobiteEntity>> {
   public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
      hashMap.put(0, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/basic_trilobite.png"));
      hashMap.put(1, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/boomerang_trilobite.png"));
      hashMap.put(2, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/spiky_trilobite.png"));
      hashMap.put(3, new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/fish/long_tailed_trilobite.png"));
   });

   public TrilobiteRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new TrilobiteModel<>(), 0.3F);
   }

   public ResourceLocation getTextureLocation(TrilobiteEntity entity) {
      return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
   }
}
