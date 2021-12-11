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

public class GlyptodonModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(Paleoworld.MOD_ID, "glyptodon"), "main");
	private final ModelPart creature;
	private final ModelPart head;
	private final ModelPart left_leg_1;
	private final ModelPart left_leg_2;
	private final ModelPart right_leg_1;
	private final ModelPart right_leg_2;
	private final ModelPart tail;

	public GlyptodonModel(ModelPart root) {
		this.creature = root.getChild("creature");
		this.head = this.creature.getChild("head");
		this.left_leg_1 = this.creature.getChild("left_leg_1");
		this.left_leg_2 = this.creature.getChild("left_leg_2");
		this.right_leg_1 = this.creature.getChild("right_leg_1");
		this.right_leg_2 = this.creature.getChild("right_leg_2");
		this.tail = this.creature.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition creature = partdefinition.addOrReplaceChild("creature", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition left_leg_1 = creature.addOrReplaceChild("left_leg_1", CubeListBuilder.create().texOffs(25, 53).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 4.0F, -5.0F));

		PartDefinition left_leg_2 = creature.addOrReplaceChild("left_leg_2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 4.0F, 6.0F));

		PartDefinition right_leg_1 = creature.addOrReplaceChild("right_leg_1", CubeListBuilder.create().texOffs(50, 49).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 4.0F, -5.0F));

		PartDefinition right_leg_2 = creature.addOrReplaceChild("right_leg_2", CubeListBuilder.create().texOffs(0, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 4.0F, 6.0F));

		PartDefinition body = creature.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -10.0F, -10.0F, 21.0F, 18.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition head = creature.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -4.0F, -7.0F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -10.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(7, 16).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -4.0F, -2.0F, 0.0F, 0.0F, -0.829F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -4.0F, -2.0F, 0.0F, 0.0F, 0.829F));

		PartDefinition tail = creature.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition cube_r3 = tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(29, 40).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
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
		creature.render(poseStack, buffer, packedLight, packedOverlay);
	}
}