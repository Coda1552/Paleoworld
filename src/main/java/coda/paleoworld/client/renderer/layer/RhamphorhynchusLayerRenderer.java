package coda.paleoworld.client.renderer.layer;

import coda.paleoworld.client.model.RhamphorhynchusModel;
import coda.paleoworld.client.renderer.RhamphorhynchusRenderer;
import coda.paleoworld.common.entities.RhamphorhynchusEntity;
import coda.paleoworld.common.init.PWEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class RhamphorhynchusLayerRenderer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {
    private final RhamphorhynchusModel<RhamphorhynchusEntity> model = new RhamphorhynchusModel<>(RhamphorhynchusModel.createBodyLayer().bakeRoot());

    public RhamphorhynchusLayerRenderer(RenderLayerParent<T, PlayerModel<T>> p_i50929_1_) {
        super(p_i50929_1_);
    }

    public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_, T p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        this.render(p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, p_225628_5_, p_225628_6_, p_225628_9_, p_225628_10_, true);
        this.render(p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, p_225628_5_, p_225628_6_, p_225628_9_, p_225628_10_, false);
    }

    private void render(PoseStack p_229136_1_, MultiBufferSource p_229136_2_, int p_229136_3_, T p_229136_4_, float p_229136_5_, float p_229136_6_, float p_229136_7_, float p_229136_8_, boolean p_229136_9_) {
        CompoundTag compoundnbt = p_229136_9_ ? p_229136_4_.getShoulderEntityLeft() : p_229136_4_.getShoulderEntityRight();
        if (compoundnbt.getString("id").equals(PWEntities.RHAMPHORHYNCHUS.get().getRegistryName().toString())) {
            p_229136_1_.pushPose();
            p_229136_1_.translate(p_229136_9_ ? (double)0.4F : (double)-0.4F, p_229136_4_.isCrouching() ? (double)-1.3F : -1.5D, 0.0D);
            VertexConsumer ivertexbuilder = p_229136_2_.getBuffer(this.model.renderType(RhamphorhynchusRenderer.TEXTURE));
            this.model.renderOnShoulder(p_229136_1_, ivertexbuilder, p_229136_3_, OverlayTexture.NO_OVERLAY, p_229136_5_, p_229136_6_, p_229136_7_, p_229136_8_, p_229136_4_.tickCount);
            p_229136_1_.popPose();
        }
    }
}
