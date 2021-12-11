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

public class RhamphorhynchusModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(Paleoworld.MOD_ID, "rhamphorhynchus"), "main");
	private final ModelPart creature;

	public RhamphorhynchusModel(ModelPart root) {
		this.creature = root.getChild("creature");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition creature = partdefinition.addOrReplaceChild("creature", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition left_wing = creature.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(20, 23).addBox(0.0F, 0.0F, -4.0F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.0F, 0.0F));

		PartDefinition left_wing_second_block = left_wing.addOrReplaceChild("left_wing_second_block", CubeListBuilder.create(), PartPose.offset(6.0F, 0.0F, -1.0F));

		PartDefinition cube_r1 = left_wing_second_block.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(21, 0).addBox(0.0F, 0.0F, -4.0F, 9.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition right_wing = creature.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 22).addBox(-6.0F, 0.0F, -4.0F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -1.0F, 0.0F));

		PartDefinition right_wing_second_block = right_wing.addOrReplaceChild("right_wing_second_block", CubeListBuilder.create(), PartPose.offset(-6.0F, 0.0F, -1.0F));

		PartDefinition cube_r2 = right_wing_second_block.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 15).addBox(-9.0F, 0.0F, -4.0F, 9.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition body = creature.addOrReplaceChild("body", CubeListBuilder.create().texOffs(29, 9).addBox(-1.0F, -3.0F, -2.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition cube_r3 = right_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 4.0F));

		PartDefinition cube_r4 = left_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(21, 7).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 4.0F));

		PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition head = creature.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -5.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.5F, -2.0F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -0.5F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		creature.render(poseStack, buffer, packedLight, packedOverlay);
	}

	public void renderOnShoulder(PoseStack p_228284_1_, VertexConsumer p_228284_2_, int p_228284_3_, int p_228284_4_, float p_228284_5_, float p_228284_6_, float p_228284_7_, float p_228284_8_, int p_228284_9_) {
		this.creature.render(p_228284_1_, p_228284_2_, p_228284_3_, p_228284_4_);
	}
}