package coda.paleoworld.client.model;

import coda.paleoworld.common.entities.DawnHorseEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;

public class DawnHorseModel<T extends DawnHorseEntity> extends AgeableModel<T> {
	private final ModelRenderer horse;
	private final ModelRenderer head;
	private final ModelRenderer cube_r1;
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer cube_r2;
	private final ModelRenderer left_leg_1;
	private final ModelRenderer left_leg_2;
	private final ModelRenderer right_leg_1;
	private final ModelRenderer right_leg_2;

	public DawnHorseModel() {
		texWidth = 32;
		texHeight = 32;

		horse = new ModelRenderer(this);
		horse.setPos(0.0F, 19.0F, 1.0F);

		head = new ModelRenderer(this);
		head.setPos(0.5F, -1.4889F, -3.4893F);
		horse.addChild(head);
		head.texOffs(0, 13).addBox(-1.5F, -1.5111F, -6.5107F, 3.0F, 3.0F, 7.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-0.5F, -1.5111F, -3.5107F);
		head.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.0436F, 0.0F, 0.0F);
		cube_r1.texOffs(14, 0).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		horse.addChild(body);
		body.texOffs(0, 0).addBox(-1.0F, -3.0F, -3.0F, 3.0F, 5.0F, 7.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, -2.0F, 4.0F);
		body.addChild(tail);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, 0.0F, 0.0F);
		tail.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0873F, 0.0F, 0.0F);
		cube_r2.texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		left_leg_1 = new ModelRenderer(this);
		left_leg_1.setPos(1.0F, 2.0F, -2.0F);
		horse.addChild(left_leg_1);
		left_leg_1.texOffs(0, 13).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		left_leg_2 = new ModelRenderer(this);
		left_leg_2.setPos(1.0F, 2.0F, 2.0F);
		horse.addChild(left_leg_2);
		left_leg_2.texOffs(14, 13).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		right_leg_1 = new ModelRenderer(this);
		right_leg_1.setPos(0.0F, 2.0F, -2.0F);
		horse.addChild(right_leg_1);
		right_leg_1.texOffs(21, 3).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		right_leg_2 = new ModelRenderer(this);
		right_leg_2.setPos(0.0F, 2.0F, 2.0F);
		horse.addChild(right_leg_2);
		right_leg_2.texOffs(19, 13).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.left_leg_1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg_1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.left_leg_2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.right_leg_2.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.tail.zRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
	}

	@Override
	protected Iterable<ModelRenderer> headParts() {
		return Collections.emptyList();
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(horse);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}