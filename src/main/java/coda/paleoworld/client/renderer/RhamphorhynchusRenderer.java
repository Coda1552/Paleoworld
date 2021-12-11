package coda.paleoworld.client.renderer;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.RhamphorhynchusModel;
import coda.paleoworld.common.entities.RhamphorhynchusEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RhamphorhynchusRenderer extends MobRenderer<RhamphorhynchusEntity, RhamphorhynchusModel<RhamphorhynchusEntity>> {
   public static final ResourceLocation TEXTURE = new ResourceLocation(Paleoworld.MOD_ID, "textures/entity/rhamphorhynchus.png");

   public RhamphorhynchusRenderer(EntityRendererProvider.Context manager) {
      super(manager, new RhamphorhynchusModel<>(manager.bakeLayer(RhamphorhynchusModel.LAYER)), 0.5F);
   }

   public ResourceLocation getTextureLocation(RhamphorhynchusEntity entity) {
      return TEXTURE;
   }
}
