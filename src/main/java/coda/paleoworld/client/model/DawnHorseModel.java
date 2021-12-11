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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class DawnHorseModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(Paleoworld.MOD_ID, "dawn_horse"), "main");
	private final ModelPart horse;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart left_leg_1;
	private final ModelPart left_leg_2;
	private final ModelPart right_leg_1;
	private final ModelPart right_leg_2;
	private final ModelPart tail;

	public DawnHorseModel(ModelPart root) {
		this.horse = root.getChild("horse");
		this.body = this.horse.getChild("body");
		this.head = this.horse.getChild("head");
		this.left_leg_1 = this.horse.getChild("left_leg_1");
		this.left_leg_2 = this.horse.getChild("left_leg_2");
		this.right_leg_1 = this.horse.getChild("right_leg_1");
		this.right_leg_2 = this.horse.getChild("right_leg_2");
		this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition horse = partdefinition.addOrReplaceChild("horse", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 1.0F));

		PartDefinition head = horse.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -1.5111F, -6.5107F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -1.4889F, -3.4893F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.5111F, -3.5107F, -0.0436F, 0.0F, 0.0F));

		PartDefinition body = horse.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.0F, -3.0F, 3.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 4.0F));

		PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition left_leg_1 = horse.addOrReplaceChild("left_leg_1", CubeListBuilder.create().texOffs(21, 3).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, -2.0F));

		PartDefinition left_leg_2 = horse.addOrReplaceChild("left_leg_2", CubeListBuilder.create().texOffs(19, 13).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, 2.0F));

		PartDefinition right_leg_1 = horse.addOrReplaceChild("right_leg_1", CubeListBuilder.create().texOffs(14, 13).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -2.0F));

		PartDefinition right_leg_2 = horse.addOrReplaceChild("right_leg_2", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.left_leg_1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg_1.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.left_leg_2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.right_leg_2.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.tail.zRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		horse.render(poseStack, buffer, packedLight, packedOverlay);
	}
}