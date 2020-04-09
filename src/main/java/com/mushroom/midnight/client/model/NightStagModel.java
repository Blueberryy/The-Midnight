package com.mushroom.midnight.client.model;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.common.entity.creature.NightStagEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NightStagModel extends QuadrupedModel<NightStagEntity> {
    public RendererModel snout;
    public RendererModel rightAntler;
    public RendererModel leftAntler;

    public NightStagModel() {
        super(15, 0f);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.body = new RendererModel(this, 0, 32); // body
        this.body.setRotationPoint(0.0F, 8.0F, 16.0F);
        this.body.addBox(-5.0F, -6.0F, -20.0F, 10, 10, 22, 0.0F);
        this.legBackRight = new RendererModel(this, 0, 36); // leg1
        this.legBackRight.setRotationPoint(-3.75F, 12.0F, 0.0F);
        this.legBackRight.addBox(-2.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);
        this.legBackLeft = new RendererModel(this, 46, 31); // leg2
        this.legBackLeft.setRotationPoint(-4.0F, 8.0F, 14.0F);
        this.legBackLeft.addBox(-2.0F, -2.0F, -2.5F, 4, 18, 5, 0.0F);
        this.leftAntler = new RendererModel(this, 0, 0);
        this.leftAntler.mirror = true;
        this.leftAntler.setRotationPoint(1.5F, -15.0F, -1.0F);
        this.leftAntler.addBox(-1.0F, -17.0F, -6.0F, 1, 18, 12, 0.0F);
        this.setRotateAngle(this.leftAntler, 0.0F, 0.7853981633974483F, 0.7853981633974483F);
        this.legFrontRight = new RendererModel(this, 0, 36); // leg3
        this.legFrontRight.setRotationPoint(3.75F, 12.0F, 0.0F);
        this.legFrontRight.addBox(-2.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);
        this.legFrontLeft = new RendererModel(this, 46, 31); // leg4
        this.legFrontLeft.setRotationPoint(4.0F, 8.0F, 14.0F);
        this.legFrontLeft.addBox(-2.0F, -2.0F, -2.5F, 4, 18, 5, 0.0F);
        this.headModel = new RendererModel(this, 32, 5);
        this.headModel.setRotationPoint(0.0F, 6.0F, -5.0F);
        this.headModel.addBox(-4.0F, -15.0F, -4.0F, 8, 18, 8, 0.0F);
        this.setRotateAngle(this.headModel, 0.17453292519943295F, 0.0F, 0.0F);
        this.rightAntler = new RendererModel(this, 0, 0);
        this.rightAntler.setRotationPoint(-1.5F, -15.0F, -1.0F);
        this.rightAntler.addBox(0.0F, -17.0F, -6.0F, 1, 18, 12, 0.0F);
        this.setRotateAngle(this.rightAntler, 0.0F, -0.7853981633974483F, -0.7853981633974483F);
        this.snout = new RendererModel(this, 16, 0);
        this.snout.setRotationPoint(0.0F, -5.0F, -4.0F);
        this.snout.addBox(-3.0F, -5.0F, -6.0F, 6, 5, 6, 0.0F);
        this.setRotateAngle(this.snout, -0.17453292519943295F, 0.0F, 0.0F);
        this.headModel.addChild(this.leftAntler);
        this.headModel.addChild(this.rightAntler);
        this.headModel.addChild(this.snout);
    }

    @Override
    public void render(NightStagEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        GlStateManager.pushMatrix();
        GlStateManager.translatef(0.0F, 0.0F, -0.4F);

        this.headModel.render(scale);
        this.body.render(scale);
        this.legBackRight.render(scale);
        this.legBackLeft.render(scale);
        this.legFrontRight.render(scale);
        this.legFrontLeft.render(scale);

        GlStateManager.popMatrix();
    }

    private void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(NightStagEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.headModel.rotateAngleX = headPitch * 0.017453292f + 0.17453292519943295f;
        this.headModel.rotateAngleY = netHeadYaw * 0.017453292f;
        this.body.rotateAngleX = 0f;
        this.legBackRight.rotateAngleX = this.legFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.legBackLeft.rotateAngleX = this.legFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float) Math.PI) * 1.4f * limbSwingAmount;
        this.headModel.rotationPointX = 0f;
        this.legFrontRight.rotateAngleZ = 0f;

        entity.getCapability(Midnight.ANIMATION_CAP, null).ifPresent(animationCap -> {
            if (animationCap.isAnimate()) {
                float partialTicks = Minecraft.getInstance().getRenderPartialTicks();
                float progress = animationCap.getProgress(partialTicks);
                float fctAnimation;
                switch (animationCap.getAnimationType()) {
                    case ATTACK:
                        fctAnimation = MathHelper.sin((float) (progress * Math.PI));
                        this.body.rotateAngleX = fctAnimation * 0.2f;
                        this.headModel.rotateAngleX = 0.17453292519943295f + fctAnimation * 1.5f;
                        break;
                    case CURTSEY:
                        fctAnimation = MathHelper.sin((float) (progress * Math.PI));
                        this.headModel.rotateAngleX = 0.17453292519943295f + fctAnimation;
                        this.body.rotateAngleX = this.legBackRight.rotateAngleX = this.legFrontRight.rotateAngleZ = fctAnimation * 0.2f;
                        this.legFrontRight.rotateAngleX = -this.legFrontRight.rotateAngleZ;
                        break;
                    case EAT:
                        this.body.rotateAngleX = MathHelper.sin((float) (progress * Math.PI)) * 0.2f;
                        this.headModel.rotateAngleX = 0.17453292519943295f + (progress <= 0.1f ? progress * 15f : progress >= 0.9f ? (1f - progress) * 15f : 1.5f);
                        if (progress > 0.1f && progress < 0.9f) {
                            float wobble = (progress - 0.1f) / 0.8f;
                            this.headModel.rotateAngleX += MathHelper.sin((float) (wobble * 10 * Math.PI)) * 0.1f;
                        }
                        break;
                    case CHARGE:
                        fctAnimation = MathHelper.sin((float) ((progress % 1) * 10f * Math.PI));
                        this.headModel.rotateAngleX = 0.17453292519943295f + (progress <= 0.05f ? progress * 28f : progress >= 0.9f ? (1f - progress) * 14f : 1.4f + fctAnimation * 0.02f);
                        this.headModel.rotateAngleY = 0f;
                        this.body.rotateAngleX = fctAnimation * 0.05f;
                        if (progress <= 0.1f) {
                            this.legBackRight.rotateAngleX = this.legBackRight.rotateAngleY = fctAnimation * 0.3f;
                        }
                        break;
                }
            }
        });
    }
}
