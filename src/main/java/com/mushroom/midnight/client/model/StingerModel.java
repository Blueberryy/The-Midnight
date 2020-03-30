package com.mushroom.midnight.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.common.capability.AnimationCapability;
import com.mushroom.midnight.common.entity.creature.StingerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StingerModel extends EntityModel<StingerEntity> {
    private ModelRenderer stingerLeg5;
    private ModelRenderer stingerLeg6;
    private ModelRenderer stingerLeg7;
    private ModelRenderer stingerLeg8;
    private ModelRenderer stingerLeg1;
    private ModelRenderer stingerLeg2;
    private ModelRenderer stingerLeg3;
    private ModelRenderer stingerLeg4;
    private ModelRenderer stingerHead;
    private ModelRenderer stingerBody;
    private ModelRenderer bodyEnd;
    private ModelRenderer stingerBody2;
    private ModelRenderer bodyTail1;
    private ModelRenderer bodyTail2;
    private ModelRenderer bodyTail3;
    private ModelRenderer bodyTail4;
    private ModelRenderer armRight;
    private ModelRenderer armLeft;
    private ModelRenderer PincerRightBack;
    private ModelRenderer PincerRightInt;
    private ModelRenderer PincerRightExt;
    private ModelRenderer PincerRightInt2;
    private ModelRenderer PincerRightExt2;
    private ModelRenderer PincerLeftBack;
    private ModelRenderer PincerLeftInt;
    private ModelRenderer PincerLeftExt;
    private ModelRenderer PincerLeftInt2;
    private ModelRenderer PincerLeftExt2;

    public StingerModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.stingerLeg7 = new ModelRenderer(this, 18, 0);
        this.stingerLeg7.setRotationPoint(-4.0F, 15.0F, -1.0F);
        this.stingerLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(stingerLeg7, 0.0F, -0.7853981633974483F, -0.7853981633974483F);
        this.bodyTail3 = new ModelRenderer(this, 9, 17);
        this.bodyTail3.setRotationPoint(0.0F, 0.5F, 3.5F);
        this.bodyTail3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
        this.setRotateAngle(bodyTail3, 0.6981317007977318F, 0.0F, 0.0F);
        this.stingerLeg6 = new ModelRenderer(this, 18, 0);
        this.stingerLeg6.setRotationPoint(4.0F, 15.0F, 0.0F);
        this.stingerLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(stingerLeg6, 0.0F, 0.39269908169872414F, 0.5811946409141118F);
        this.stingerLeg4 = new ModelRenderer(this, 18, 0);
        this.stingerLeg4.setRotationPoint(4.0F, 15.0F, 1.0F);
        this.stingerLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(stingerLeg4, 0.0F, -0.39269908169872414F, 0.5811946409141118F);
        this.stingerLeg8 = new ModelRenderer(this, 18, 0);
        this.stingerLeg8.setRotationPoint(4.0F, 15.0F, -1.0F);
        this.stingerLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(stingerLeg8, 0.0F, 0.7853981633974483F, 0.7853981633974483F);
        this.stingerBody = new ModelRenderer(this, 0, 12);
        this.stingerBody.setRotationPoint(2.0F, 15.0F, 9.0F);
        this.stingerBody.addBox(-5.0F, -1.0F, -6.0F, 6, 4, 10, 0.0F);
        this.stingerLeg5 = new ModelRenderer(this, 18, 0);
        this.stingerLeg5.setRotationPoint(-4.0F, 15.0F, 0.0F);
        this.stingerLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(stingerLeg5, 0.0F, -0.39269908169872414F, -0.5811946409141118F);
        this.bodyEnd = new ModelRenderer(this, 4, 15);
        this.bodyEnd.setRotationPoint(-4.5F, -0.2F, 2.6F);
        this.bodyEnd.addBox(0.0F, 0.0F, 0.0F, 5, 3, 7, 0.0F);
        this.setRotateAngle(bodyEnd, 0.5235987755982988F, 0.0F, 0.0F);
        this.stingerLeg1 = new ModelRenderer(this, 18, 0);
        this.stingerLeg1.setRotationPoint(-4.0F, 15.0F, 2.0F);
        this.stingerLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(stingerLeg1, 0.0F, 0.7853981633974483F, -0.7853981633974483F);
        this.bodyTail1 = new ModelRenderer(this, 7, 17);
        this.bodyTail1.setRotationPoint(0.5F, 0.6F, 4.9F);
        this.bodyTail1.addBox(0.0F, 0.0F, 0.0F, 4, 3, 5, 0.0F);
        this.setRotateAngle(bodyTail1, 0.6981317007977318F, 0.0F, 0.0F);
        this.stingerHead = new ModelRenderer(this, 0, 0);
        this.stingerHead.setRotationPoint(0.0F, 15.0F, -10.0F);
        this.stingerHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.bodyTail4 = new ModelRenderer(this, 47, 24);
        this.bodyTail4.setRotationPoint(0.5F, 0.6F, 4.8F);
        this.bodyTail4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(bodyTail4, 0.2617993877991494F, 0.0F, 0.0F);
        this.stingerLeg3 = new ModelRenderer(this, 18, 0);
        this.stingerLeg3.setRotationPoint(-4.0F, 15.0F, 1.0F);
        this.stingerLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(stingerLeg3, 0.0F, 0.39269908169872414F, -0.5811946409141118F);
        this.stingerBody2 = new ModelRenderer(this, 0, 12);
        this.stingerBody2.setRotationPoint(-5.0F, -1.0F, -16.0F);
        this.stingerBody2.addBox(0.0F, 0.0F, 0.0F, 6, 4, 10, 0.0F);
        this.stingerLeg2 = new ModelRenderer(this, 18, 0);
        this.stingerLeg2.setRotationPoint(4.0F, 15.0F, 2.0F);
        this.stingerLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(stingerLeg2, 0.0F, -0.7853981633974483F, 0.7853981633974483F);
        this.bodyTail2 = new ModelRenderer(this, 9, 17);
        this.bodyTail2.setRotationPoint(1.0F, 1.0F, 3.1F);
        this.bodyTail2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
        this.setRotateAngle(bodyTail2, 0.8726646259971648F, 0.0F, 0.0F);

        this.armRight = new ModelRenderer(this, 7, 17);
        this.armRight.setRotationPoint(1f, 1.5f, 2f);
        this.armRight.addBox(-1f, 0f, -8f, 1, 1, 8, 0f);
        this.setRotateAngle(armRight, 0.2617993877991494f, 0.4363323129985824f, 0.6981317007977318f);
        this.PincerRightBack = new ModelRenderer(this, 9, 23);
        this.PincerRightBack.setRotationPoint(-6f, 0f, -9f);
        this.PincerRightBack.addBox(0f, 0f, 0f, 5, 1, 2, 0f);
        this.PincerRightInt = new ModelRenderer(this, 23, 17);
        this.PincerRightInt.setRotationPoint(5f, 0f, 1f);
        this.PincerRightInt.addBox(-1f, 0f, -10f, 2, 1, 10, 0f);
        this.PincerRightInt2 = new ModelRenderer(this, 47, 29);
        this.PincerRightInt2.setRotationPoint(-1f, 0f, -11f);
        this.PincerRightInt2.addBox(0f, 0f, 0f, 1, 1, 1, 0f);
        this.PincerRightExt = new ModelRenderer(this, 25, 19);
        this.PincerRightExt.setRotationPoint(0f, 0f, 0f);
        this.PincerRightExt.addBox(-1f, 0f, -8f, 2, 1, 8, 0f);
        this.PincerRightExt2 = new ModelRenderer(this, 47, 30);
        this.PincerRightExt2.setRotationPoint(0f, 0f, -7f);
        this.PincerRightExt2.addBox(0.1f, 0f, -1.4f, 3, 1, 1, 0f);
        this.setRotateAngle(PincerRightExt2, 0f, 0.8726646259971648f, 0f);

        this.armLeft = new ModelRenderer(this, 7, 17);
        this.armLeft.setRotationPoint(6f, 2f, 2f);
        this.armLeft.addBox(-1f, 0f, -8f, 1, 1, 8, 0f);
        this.setRotateAngle(armLeft, -0.2617993877991494f, 0.4363323129985824f, 2.443460952792061f);
        this.PincerLeftBack = new ModelRenderer(this, 9, 23);
        this.PincerLeftBack.setRotationPoint(-6f, 0f, -9f);
        this.PincerLeftBack.addBox(0f, 0f, 0f, 5, 1, 2, 0f);
        this.PincerLeftInt = new ModelRenderer(this, 23, 17);
        this.PincerLeftInt.setRotationPoint(5f, 0f, 1f);
        this.PincerLeftInt.addBox(-1f, 0f, -10f, 2, 1, 10, 0f);
        this.PincerLeftInt2 = new ModelRenderer(this, 47, 29);
        this.PincerLeftInt2.setRotationPoint(-1f, 0f, -11f);
        this.PincerLeftInt2.addBox(0f, 0f, 0f, 1, 1, 1, 0f);
        this.PincerLeftExt = new ModelRenderer(this, 25, 19);
        this.PincerLeftExt.setRotationPoint(0f, 0f, 0f);
        this.PincerLeftExt.addBox(-1f, 0f, -8f, 2, 1, 8, 0f);
        this.PincerLeftExt2 = new ModelRenderer(this, 56, 30);
        this.PincerLeftExt2.setRotationPoint(0f, 0f, -7f);
        this.PincerLeftExt2.addBox(0.1f, 0f, -1.4f, 3, 1, 1, 0f);
        this.setRotateAngle(PincerLeftExt2, 0f, 0.8726646259971648f, 0f);

        this.stingerBody2.addChild(this.armRight);
        this.bodyTail2.addChild(this.bodyTail3);
        this.stingerBody.addChild(this.bodyEnd);
        this.bodyEnd.addChild(this.bodyTail1);
        this.stingerBody2.addChild(this.armLeft);
        this.bodyTail3.addChild(this.bodyTail4);
        this.stingerBody.addChild(this.stingerBody2);
        this.bodyTail1.addChild(this.bodyTail2);

        this.armRight.addChild(this.PincerRightBack);
        this.PincerRightBack.addChild(this.PincerRightInt);
        this.PincerRightInt.addChild(this.PincerRightInt2);
        this.PincerRightBack.addChild(this.PincerRightExt);
        this.PincerRightExt.addChild(this.PincerRightExt2);

        this.armLeft.addChild(this.PincerLeftBack);
        this.PincerLeftBack.addChild(this.PincerLeftInt);
        this.PincerLeftInt.addChild(this.PincerLeftInt2);
        this.PincerLeftBack.addChild(this.PincerLeftExt);
        this.PincerLeftExt.addChild(this.PincerLeftExt2);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.stingerLeg7, this.stingerLeg6, this.stingerLeg4, this.stingerLeg8, this.stingerBody,
                this.stingerLeg5, this.stingerLeg1, this.stingerHead, this.stingerLeg3, this.stingerLeg2).forEach((p_228272_8_) -> {
            p_228272_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(StingerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.stingerHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.stingerHead.rotateAngleX = headPitch * 0.017453292F;
        float piOn4 = (float) (Math.PI / 4f);

        // spider animation for legs
        this.stingerLeg1.rotateAngleZ = this.stingerLeg7.rotateAngleZ = this.stingerLeg2.rotateAngleY = this.stingerLeg7.rotateAngleY = -piOn4;
        this.stingerLeg2.rotateAngleZ = this.stingerLeg8.rotateAngleZ = this.stingerLeg1.rotateAngleY = this.stingerLeg8.rotateAngleY = piOn4;
        this.stingerLeg3.rotateAngleZ = this.stingerLeg5.rotateAngleZ = -0.58119464F;
        this.stingerLeg4.rotateAngleZ = this.stingerLeg6.rotateAngleZ = 0.58119464F;
        this.stingerLeg3.rotateAngleY = this.stingerLeg6.rotateAngleY = 0.3926991F;
        this.stingerLeg4.rotateAngleY = this.stingerLeg5.rotateAngleY = -0.3926991F;

        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;
        this.stingerLeg1.rotateAngleY += f3;
        this.stingerLeg2.rotateAngleY += -f3;
        this.stingerLeg3.rotateAngleY += f4;
        this.stingerLeg4.rotateAngleY += -f4;
        this.stingerLeg5.rotateAngleY += f5;
        this.stingerLeg6.rotateAngleY += -f5;
        this.stingerLeg7.rotateAngleY += f6;
        this.stingerLeg8.rotateAngleY += -f6;
        this.stingerLeg1.rotateAngleZ += f7;
        this.stingerLeg2.rotateAngleZ += -f7;
        this.stingerLeg3.rotateAngleZ += f8;
        this.stingerLeg4.rotateAngleZ += -f8;
        this.stingerLeg5.rotateAngleZ += f9;
        this.stingerLeg6.rotateAngleZ += -f9;
        this.stingerLeg7.rotateAngleZ += f10;
        this.stingerLeg8.rotateAngleZ += -f10;

        // Pincers clapping
        this.PincerLeftExt.rotateAngleY = this.PincerRightInt.rotateAngleY = MathHelper.sin((float) (limbSwing * Math.PI)) * limbSwingAmount * 0.5f;
        this.PincerRightExt.rotateAngleY = this.PincerLeftInt.rotateAngleY = -this.PincerLeftExt.rotateAngleY;

        entity.getCapability(Midnight.ANIMATION_CAP, null).ifPresent(animationCap -> {
            if (animationCap.getAnimationType() == AnimationCapability.Type.ATTACK) {
                float partialTicks = Minecraft.getInstance().getRenderPartialTicks();
                float attackProgress = animationCap.getProgress(partialTicks);
                float attackAnimation = MathHelper.sin((float) (attackProgress * Math.PI));
                // TODO
            }
        });
    }

    private void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }
}
