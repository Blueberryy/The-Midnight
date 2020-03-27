package com.mushroom.midnight.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mushroom.midnight.common.entity.creature.ShadeSquirrelEntity;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShadeSquirrelModel extends SegmentedModel<ShadeSquirrelEntity> implements IHasArm {
    public ModelRenderer body;
    public ModelRenderer legL;
    public ModelRenderer legR;
    public ModelRenderer tail;
    public ModelRenderer head;
    public ModelRenderer handL;
    public ModelRenderer handR;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer earR2;
    public ModelRenderer earL2;

    public ShadeSquirrelModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.earL = new ModelRenderer(this, 0, 22);
        this.earL.setRotationPoint(-2.0F, -3.0F, -2.0F);
        this.earL.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.tail2 = new ModelRenderer(this, 36, 6);
        this.tail2.setRotationPoint(0.0F, -1.0F, 0.1F);
        this.tail2.addBox(-2.0F, -7.0F, -2.0F, 4, 7, 4, 0.0F);
        this.setRotateAngle(tail2, 0.5009094953223726F, 0.0F, 0.0F);
        this.legL = new ModelRenderer(this, 24, 0);
        this.legL.setRotationPoint(1.4F, 1.0F, 1.0F);
        this.legL.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(legL, -0.36425021489121656F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 36, 0);
        this.tail.setRotationPoint(0.0F, -3.0F, 0.1F);
        this.tail.addBox(-1.5F, -2.0F, -1.5F, 3, 3, 3, 0.0F);
        this.earL2 = new ModelRenderer(this, 0, 22);
        this.earL2.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.earL2.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(earL2, 0.0F, 0.0F, -0.5918411493512771F);
        this.legR = new ModelRenderer(this, 30, 0);
        this.legR.setRotationPoint(-1.4F, 1.0F, 1.0F);
        this.legR.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(legR, -0.36425021489121656F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 21.7F, 2.1F);
        this.body.addBox(-2.5F, -2.5F, -6.0F, 5, 5, 7, 0.0F);
        this.setRotateAngle(body, -1.1838568316277536F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 12);
        this.head.setRotationPoint(0.0F, 14.5F, 1.1F);
        this.head.addBox(-2.5F, -3.0F, -5.0F, 5, 5, 5, 0.0F);
        this.tail3 = new ModelRenderer(this, 36, 6);
        this.tail3.setRotationPoint(0.0F, -5.7F, 0.1F);
        this.tail3.addBox(-2.0F, -7.0F, -2.0F, 4, 7, 4, 0.0F);
        this.setRotateAngle(tail3, 0.5009094953223726F, 0.0F, 0.0F);
        this.earR2 = new ModelRenderer(this, 6, 22);
        this.earR2.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.earR2.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(earR2, 0.0F, 0.0F, 0.5918411493512771F);
        this.earR = new ModelRenderer(this, 6, 22);
        this.earR.setRotationPoint(2.0F, -3.0F, -2.0F);
        this.earR.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.handL = new ModelRenderer(this, 24, 4);
        this.handL.setRotationPoint(3.0F, 1.0F, -3.3F);
        this.handL.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(handL, 0.0F, 0.0F, 0.40980330836826856F);
        this.handR = new ModelRenderer(this, 30, 4);
        this.handR.setRotationPoint(-3.0F, 1.0F, -3.3F);
        this.handR.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(handR, 0.0F, 0.0F, -0.40980330836826856F);
        this.head.addChild(this.earL);
        this.tail.addChild(this.tail2);
        this.body.addChild(this.legL);
        this.body.addChild(this.tail);
        this.earL.addChild(this.earL2);
        this.body.addChild(this.legR);
        this.tail2.addChild(this.tail3);
        this.earR.addChild(this.earR2);
        this.head.addChild(this.earR);
        this.body.addChild(this.handL);
        this.body.addChild(this.handR);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.head, this.body);
    }

    @Override
    public void setRotationAngles(ShadeSquirrelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);

        if(!entity.isSitting()){
            this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);

            this.body.rotateAngleX = 0.0F;
            this.handR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.handL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.handR.rotateAngleZ = 0.0F;
            this.handL.rotateAngleZ = 0.0F;
            this.head.setRotationPoint(0.0F, 16.0F, -3.2F);
            this.body.setRotationPoint(0.0F, 19.7F, 2.1F);
        }else {
            this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
            this.body.rotateAngleX = -1.1838568316277536F;
            this.handR.rotateAngleZ = -0.40980330836826856F;
            this.handL.rotateAngleZ = 0.40980330836826856F;
            this.handR.rotateAngleX = 0.0F;
            this.handL.rotateAngleX = 0.0F;
            this.head.setRotationPoint(0.0F, 14.5F, 1.1F);
            this.body.setRotationPoint(0.0F, 21.7F, 2.1F);
        }

        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.tail.rotateAngleY = MathHelper.sin((float) (ageInTicks * Math.PI * 0.25f)) * 0.05f;
        this.tail.rotateAngleX = MathHelper.sin((float) (ageInTicks * Math.PI * 0.25f)) * 0.1f;
        this.tail2.rotateAngleX = 0.5009094953223726F + MathHelper.sin((float) (ageInTicks * Math.PI * 0.25F)) * 0.08F;
        this.tail3.rotateAngleX = 0.5009094953223726F + MathHelper.sin((float) (ageInTicks * Math.PI * 0.25F)) * 0.05F;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    private ModelRenderer getArm(HandSide p_191216_1_) {
        return p_191216_1_ == HandSide.LEFT ? this.handL : this.handR;
    }

    public void postRenderArm(float scale, HandSide side) {
        if(this.isSitting) {

        }
    }

    @Override
    public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
        this.getArm(sideIn).translateRotate(matrixStackIn);
    }
}
