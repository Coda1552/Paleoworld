package coda.paleoworld.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.util.math.MathHelper;

public class AstraspisModel<T extends AbstractFishEntity> extends EntityModel<T> {
	private final ModelRenderer fish;
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer cube_r1;

	public AstraspisModel() {
		texWidth = 32;
		texHeight = 32;

		fish = new ModelRenderer(this);
		fish.setPos(0.0F, 23.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		fish.addChild(body);
		body.texOffs(0, 0).addBox(-3.0F, -2.0F, -5.0F, 6.0F, 3.0F, 10.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 0.0F, 5.0F);
		fish.addChild(tail);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, -2.0F, 0.0F);
		tail.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.1309F, 0.0F, 0.0F);
		cube_r1.texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, false);
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