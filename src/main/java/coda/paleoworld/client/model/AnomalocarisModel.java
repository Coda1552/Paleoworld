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

public class AnomalocarisModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(Paleoworld.MOD_ID, "anomalocaris"), "main");
	private final ModelPart anomalocaris;

	public AnomalocarisModel(ModelPart root) {
		this.anomalocaris = root.getChild("anomalocaris");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition anomalocaris = partdefinition.addOrReplaceChild("anomalocaris", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition body = anomalocaris.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, 0.0F, 7.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition tail = anomalocaris.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 34).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(25, 0).addBox(-4.0F, 0.0F, -1.0F, 7.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition head = anomalocaris.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -2.0F, -4.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition right_eye = head.addOrReplaceChild("right_eye", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, -2.0F));

		PartDefinition cube_r2 = right_eye.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 12).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition left_eye = head.addOrReplaceChild("left_eye", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, -2.0F));

		PartDefinition cube_r3 = left_eye.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(25, 5).addBox(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition right_whisker = head.addOrReplaceChild("right_whisker", CubeListBuilder.create(), PartPose.offset(-2.0F, 1.0F, -4.0F));

		PartDefinition cube_r4 = right_whisker.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 33).addBox(-1.0F, 0.0F, -4.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition left_whisker = head.addOrReplaceChild("left_whisker", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, -4.0F));

		PartDefinition cube_r5 = left_whisker.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(23, 26).addBox(-1.0F, 0.0F, -4.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition right_fin = anomalocaris.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, 0.0F));

		PartDefinition cube_r6 = right_fin.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 16).addBox(-5.0F, 0.0F, -4.0F, 5.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition left_fin = anomalocaris.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition cube_r7 = left_fin.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 15).addBox(0.0F, 0.0F, -4.0F, 5.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		anomalocaris.render(poseStack, buffer, packedLight, packedOverlay);
	}
}