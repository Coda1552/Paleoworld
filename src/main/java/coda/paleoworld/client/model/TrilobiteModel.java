package coda.paleoworld.client.model;

import coda.paleoworld.Paleoworld;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class TrilobiteModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(Paleoworld.MOD_ID, "trilobite"), "main");
	private final ModelPart creature;

	public TrilobiteModel(ModelPart root) {
		this.creature = root.getChild("creature");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition creature = partdefinition.addOrReplaceChild("creature", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition head = creature.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, 0.75F, -4.25F, 11.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-3.5F, -2.25F, -4.25F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.25F, -0.75F));

		PartDefinition tail = creature.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(19, 16).addBox(-2.5F, -1.6085F, -0.6654F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.6085F, -0.3346F));

		PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(19, 12).addBox(-4.0F, 0.0F, 0.0F, 9.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.3915F, 0.3346F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 6).addBox(-3.0F, 0.0F, 0.0F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.3915F, 3.3346F, 0.0436F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		creature.render(poseStack, buffer, packedLight, packedOverlay);
	}
}