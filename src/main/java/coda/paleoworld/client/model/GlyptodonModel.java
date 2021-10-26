package coda.paleoworld.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;

public class GlyptodonModel<T extends LivingEntity> extends AgeableModel<T> {
	private final ModelRenderer glyptodon;
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer left_leg_2;
	private final ModelRenderer left_leg_1;
	private final ModelRenderer right_leg_2;
	private final ModelRenderer right_leg_1;
	private final ModelRenderer head;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;

	public GlyptodonModel() {
			texWidth = 128;
			texHeight = 128;

			glyptodon = new ModelRenderer(this);
			glyptodon.setPos(0.0F, 15.0F, 0.0F);

			body = new ModelRenderer(this);
			body.setPos(0.0F, -2.0F, 0.0F);
			glyptodon.addChild(body);
			body.texOffs(0, 0).addBox(-10.0F, -10.0F, -11.0F, 21.0F, 18.0F, 21.0F, 0.0F, false);

			tail = new ModelRenderer(this);
			tail.setPos(0.5F, 3.3207F, 9.7337F);
			body.addChild(tail);

			cube_r1 = new ModelRenderer(this);
			cube_r1.setPos(-0.5F, -0.3207F, 0.2663F);
			tail.addChild(cube_r1);
			setRotationAngle(cube_r1, -0.2618F, 0.0F, 0.0F);
			cube_r1.texOffs(29, 40).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 5.0F, 7.0F, 0.0F, false);

			left_leg_1 = new ModelRenderer(this);
			left_leg_1.setPos(8.0F, 6.0F, -6.0F);
			glyptodon.addChild(left_leg_1);
			left_leg_1.texOffs(25, 53).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

			left_leg_2 = new ModelRenderer(this);
			left_leg_2.setPos(8.0F, 6.0F, 7.0F);
			glyptodon.addChild(left_leg_2);
			left_leg_2.texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

			right_leg_1 = new ModelRenderer(this);
			right_leg_1.setPos(-7.0F, 6.0F, -6.0F);
			glyptodon.addChild(right_leg_1);
			right_leg_1.texOffs(50, 49).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

			right_leg_2 = new ModelRenderer(this);
			right_leg_2.setPos(-7.0F, 6.0F, 7.0F);
			glyptodon.addChild(right_leg_2);
			right_leg_2.texOffs(0, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

			head = new ModelRenderer(this);
			head.setPos(0.5F, 3.0204F, -11.5F);
			glyptodon.addChild(head);
			head.texOffs(0, 40).addBox(-3.5F, -3.0204F, -6.5F, 7.0F, 6.0F, 7.0F, 0.0F, false);

			cube_r2 = new ModelRenderer(this);
			cube_r2.setPos(2.5F, -3.0204F, -1.5F);
			head.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, 0.0F, 1.1345F);
			cube_r2.texOffs(7, 16).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

			cube_r3 = new ModelRenderer(this);
			cube_r3.setPos(-2.5F, -3.0204F, -1.5F);
			head.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, 0.0F, -1.1345F);
			cube_r3.texOffs(0, 16).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
			this.head.xRot = headPitch * ((float)Math.PI / 180F);
			this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
			this.left_leg_1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.right_leg_1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
			this.left_leg_2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
			this.right_leg_2.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.tail.yRot = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
		}

	@Override
	protected Iterable<ModelRenderer> headParts() {
		return Collections.emptyList();
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(glyptodon);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.xRot = x;
			modelRenderer.yRot = y;
			modelRenderer.zRot = z;
		}
	}