package coda.paleoworld.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.util.math.MathHelper;

public class HaikouichthysModel<T extends AbstractFishEntity> extends EntityModel<T> {
	private final ModelRenderer haikouichthys;
	private final ModelRenderer cube_r1;

	public HaikouichthysModel() {
		texWidth = 16;
		texHeight = 16;

		haikouichthys = new ModelRenderer(this);
		haikouichthys.setPos(0.0F, 23.0F, 1.0F);
		haikouichthys.texOffs(5, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		haikouichthys.texOffs(6, 5).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, -1.0F, -1.0F);
		haikouichthys.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.0436F, 0.0F, 0.0F);
		cube_r1.texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		float f = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
		}

		this.cube_r1.yRot = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		haikouichthys.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}