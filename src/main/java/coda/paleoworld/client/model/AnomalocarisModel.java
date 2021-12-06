package coda.paleoworld.client.model;// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import coda.paleoworld.common.entities.AnomalocarisEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AnomalocarisModel extends EntityModel<AnomalocarisEntity> {
	private final ModelRenderer anomalocaris;
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer cube_r1;
	private final ModelRenderer head;
	private final ModelRenderer right_eye;
	private final ModelRenderer cube_r2;
	private final ModelRenderer left_eye;
	private final ModelRenderer cube_r3;
	private final ModelRenderer right_whisker;
	private final ModelRenderer cube_r4;
	private final ModelRenderer left_whisker;
	private final ModelRenderer cube_r5;
	private final ModelRenderer right_fin;
	private final ModelRenderer cube_r6;
	private final ModelRenderer left_fin;
	private final ModelRenderer cube_r7;

	public AnomalocarisModel() {
		texWidth = 64;
		texHeight = 64;

		anomalocaris = new ModelRenderer(this);
		anomalocaris.setPos(0.0F, 22.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, -4.0F);
		anomalocaris.addChild(body);
		body.texOffs(0, 0).addBox(-4.0F, -2.0F, 0.0F, 7.0F, 4.0F, 10.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 0.0F, 6.0F);
		anomalocaris.addChild(tail);
		tail.texOffs(0, 34).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 1.0F, 2.0F);
		tail.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.1309F, 0.0F, 0.0F);
		cube_r1.texOffs(25, 0).addBox(-4.0F, 0.0F, -1.0F, 7.0F, 0.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, -4.0F);
		anomalocaris.addChild(head);
		head.texOffs(0, 25).addBox(-4.0F, -2.0F, -4.0F, 7.0F, 4.0F, 4.0F, 0.0F, false);

		right_eye = new ModelRenderer(this);
		right_eye.setPos(-4.0F, 0.0F, -2.0F);
		head.addChild(right_eye);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, 0.0F, 0.0F);
		right_eye.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.3054F);
		cube_r2.texOffs(25, 5).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, 0.0F, false);

		left_eye = new ModelRenderer(this);
		left_eye.setPos(3.0F, 0.0F, -2.0F);
		head.addChild(left_eye);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.0F, 0.0F, 0.0F);
		left_eye.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.2618F);
		cube_r3.texOffs(32, 12).addBox(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, 0.0F, false);

		right_whisker = new ModelRenderer(this);
		right_whisker.setPos(-2.0F, 1.0F, -4.0F);
		head.addChild(right_whisker);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.0F, 0.0F, 0.0F);
		right_whisker.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.3054F, 0.0F, 0.0F);
		cube_r4.texOffs(23, 26).addBox(-1.0F, 0.0F, -4.0F, 2.0F, 6.0F, 4.0F, 0.0F, false);

		left_whisker = new ModelRenderer(this);
		left_whisker.setPos(1.0F, 1.0F, -4.0F);
		head.addChild(left_whisker);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.0F, 0.0F, 0.0F);
		left_whisker.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.3054F, 0.0F, 0.0F);
		cube_r5.texOffs(32, 33).addBox(-1.0F, 0.0F, -4.0F, 2.0F, 6.0F, 4.0F, 0.0F, false);

		right_fin = new ModelRenderer(this);
		right_fin.setPos(-4.0F, 0.0F, 0.0F);
		anomalocaris.addChild(right_fin);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(0.0F, 0.0F, 0.0F);
		right_fin.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -0.0873F);
		cube_r6.texOffs(0, 15).addBox(-5.0F, 0.0F, -4.0F, 5.0F, 0.0F, 9.0F, 0.0F, false);

		left_fin = new ModelRenderer(this);
		left_fin.setPos(3.0F, 0.0F, 0.0F);
		anomalocaris.addChild(left_fin);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.0F, 0.0F, 0.0F);
		left_fin.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.0873F);
		cube_r7.texOffs(20, 16).addBox(0.0F, 0.0F, -4.0F, 5.0F, 0.0F, 9.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(AnomalocarisEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		anomalocaris.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}