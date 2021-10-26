package coda.paleoworld.client.model;

import coda.paleoworld.common.entities.CephalaspisEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CephalaspisModel<T extends CephalaspisEntity> extends EntityModel<T> {
	private final ModelRenderer fish;
	private final ModelRenderer right_fin;
	private final ModelRenderer left_fin;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer tail;

	public CephalaspisModel() {
		texWidth = 32;
		texHeight = 32;

		fish = new ModelRenderer(this);
		fish.setPos(0.0F, 23.0F, 1.0F);

		right_fin = new ModelRenderer(this);
		right_fin.setPos(-2.0F, 1.0F, 1.0F);
		fish.addChild(right_fin);
		right_fin.texOffs(13, 12).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

		left_fin = new ModelRenderer(this);
		left_fin.setPos(2.0F, 1.0F, 1.0F);
		fish.addChild(left_fin);
		left_fin.texOffs(18, 15).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 2.0F);
		fish.addChild(body);
		body.texOffs(0, 0).addBox(0.0F, -4.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(0, 12).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -1.0F, 0.0F);
		fish.addChild(head);
		head.texOffs(0, 0).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 0.0F, 4.0F);
		fish.addChild(tail);
		tail.texOffs(11, 15).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		float f = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
		}

		this.tail.yRot = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		fish.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}