package coda.paleoworld.client.model;

import coda.paleoworld.common.entities.TrilobiteEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TrilobiteModel<T extends TrilobiteEntity> extends EntityModel<T> {
	private final ModelRenderer creature;
	private final ModelRenderer head;
	private final ModelRenderer tail;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;

	public TrilobiteModel() {
		texWidth = 64;
		texHeight = 64;

		creature = new ModelRenderer(this);
		creature.setPos(0.0F, 23.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setPos(0.5F, 0.25F, -0.75F);
		creature.addChild(head);
		head.texOffs(0, 0).addBox(-5.5F, 0.75F, -4.25F, 11.0F, 0.0F, 5.0F, 0.0F, false);
		head.texOffs(0, 12).addBox(-3.5F, -2.25F, -4.25F, 7.0F, 3.0F, 4.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.5F, 0.6085F, -0.3346F);
		creature.addChild(tail);
		tail.texOffs(19, 16).addBox(-2.5F, -1.6085F, -0.6654F, 5.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-0.5F, 0.3915F, 0.3346F);
		tail.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0436F, 0.0F, 0.0F);
		cube_r1.texOffs(19, 12).addBox(-4.0F, 0.0F, 0.0F, 9.0F, 0.0F, 3.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-0.5F, 0.3915F, 3.3346F);
		tail.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0436F, 0.0F, 0.0F);
		cube_r2.texOffs(0, 6).addBox(-3.0F, 0.0F, 0.0F, 7.0F, 0.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
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
}