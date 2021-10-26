package coda.paleoworld.client.model;

import coda.paleoworld.common.entities.RhamphorhynchusEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class RhamphorhynchusModel<T extends RhamphorhynchusEntity> extends EntityModel<T> {
	private final ModelRenderer creature;
	private final ModelRenderer left_wing;
	private final ModelRenderer left_wing_second_block;
	private final ModelRenderer cube_r1;
	private final ModelRenderer right_wing;
	private final ModelRenderer right_wing_second_block;
	private final ModelRenderer cube_r2;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer cube_r3;
	private final ModelRenderer left_leg;
	private final ModelRenderer cube_r4;
	private final ModelRenderer tail;
	private final ModelRenderer cube_r5;
	private final ModelRenderer head;
	private final ModelRenderer cube_r6;

	public RhamphorhynchusModel() {
		texWidth = 64;
		texHeight = 64;

		creature = new ModelRenderer(this);
		creature.setPos(0.0F, 22.0F, 0.0F);

		left_wing = new ModelRenderer(this);
		left_wing.setPos(2.0F, -1.0F, 0.0F);
		creature.addChild(left_wing);
		left_wing.texOffs(20, 23).addBox(0.0F, 0.0F, -4.0F, 6.0F, 0.0F, 7.0F, 0.0F, false);

		left_wing_second_block = new ModelRenderer(this);
		left_wing_second_block.setPos(6.0F, 0.0F, -1.0F);
		left_wing.addChild(left_wing_second_block);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 0.0F, 1.0F);
		left_wing_second_block.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.1745F);
		cube_r1.texOffs(21, 0).addBox(0.0F, 0.0F, -4.0F, 9.0F, 0.0F, 6.0F, 0.0F, false);

		right_wing = new ModelRenderer(this);
		right_wing.setPos(-1.0F, -1.0F, 0.0F);
		creature.addChild(right_wing);
		right_wing.texOffs(0, 22).addBox(-6.0F, 0.0F, -4.0F, 6.0F, 0.0F, 7.0F, 0.0F, false);

		right_wing_second_block = new ModelRenderer(this);
		right_wing_second_block.setPos(-6.0F, 0.0F, -1.0F);
		right_wing.addChild(right_wing_second_block);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, 0.0F, 1.0F);
		right_wing_second_block.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.1745F);
		cube_r2.texOffs(0, 15).addBox(-9.0F, 0.0F, -4.0F, 9.0F, 0.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 2.0F, 0.0F);
		creature.addChild(body);
		body.texOffs(29, 9).addBox(-1.0F, -3.0F, -2.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(0.0F, 0.0F, 4.0F);
		body.addChild(right_leg);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.0F, 0.0F, 0.0F);
		right_leg.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.1309F, 0.0F, 0.0F);
		cube_r3.texOffs(21, 7).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 4.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(1.0F, 0.0F, 4.0F);
		body.addChild(left_leg);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.0F, 0.0F, 0.0F);
		left_leg.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.1309F, 0.0F, 0.0F);
		cube_r4.texOffs(0, 6).addBox(0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 4.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, -2.0F, 4.0F);
		body.addChild(tail);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.0F, 0.0F, 0.0F);
		tail.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.0873F, 0.0F, 0.0F);
		cube_r5.texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 0.0F, 14.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.5F, 1.4015F, -1.456F);
		creature.addChild(head);
		head.texOffs(0, 0).addBox(-1.5F, -1.4015F, -5.544F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		head.texOffs(7, 6).addBox(-0.5F, -0.4015F, -2.544F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(-0.5F, -0.4015F, -5.544F);
		head.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.2182F, 0.0F, 0.0F);
		cube_r6.texOffs(0, 22).addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		creature.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	public void renderOnShoulder(MatrixStack p_228284_1_, IVertexBuilder p_228284_2_, int p_228284_3_, int p_228284_4_, float p_228284_5_, float p_228284_6_, float p_228284_7_, float p_228284_8_, int p_228284_9_) {
		this.body.render(p_228284_1_, p_228284_2_, p_228284_3_, p_228284_4_);
	}
}