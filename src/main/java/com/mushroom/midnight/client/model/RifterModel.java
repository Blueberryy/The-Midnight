package com.mushroom.midnight.client.model;

import com.google.common.collect.ImmutableList;
import com.mushroom.midnight.common.entity.creature.RifterEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RifterModel extends SegmentedModel<RifterEntity> {
    private final ModelRenderer Abdomen;
    private final ModelRenderer Chest;
    private final ModelRenderer upperArmRight;
    private final ModelRenderer lowerArmRight;
    private final ModelRenderer upperArmLeft;
    private final ModelRenderer lowerArmLeft;
    private final ModelRenderer Head;
    private final ModelRenderer hipRight;
    private final ModelRenderer upperLegRight;
    private final ModelRenderer lowerLegRight;
    private final ModelRenderer hipLeft;
    private final ModelRenderer upperLegLeft;
    private final ModelRenderer lowerLegLeft;

    private final ModelPartAnimator animator = new ModelPartAnimator();

    public RifterModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.Abdomen = new ModelRenderer(this, 16, 16);
        this.Abdomen.setRotationPoint(-0.100000024F, 7.0F, 0.0F);
        this.Abdomen.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4);
        this.Chest = new ModelRenderer(this, 16, 41);
        this.Chest.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.Chest.addBox(-4.0F, 0.0F, -2.0F, 8, 7, 4);
        this.Abdomen.addChild(this.Chest);
        this.upperArmRight = new ModelRenderer(this, 40, 5);
        this.upperArmRight.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.upperArmRight.addBox(-1.0F, -2.0F, -1.0F, 2, 8, 2);
        this.upperArmRight.mirror = true;
        this.setRotateAngle(this.upperArmRight, 0.0F, 0.0F, -0.10000736647217022F);
        this.Chest.addChild(this.upperArmRight);
        this.lowerArmRight = new ModelRenderer(this, 41, 16);
        this.lowerArmRight.setRotationPoint(1.0F, 5.900002F, 0.0F);
        this.lowerArmRight.addBox(-2.0F, 0.0F, -1.0F, 2, 8, 2);
        this.setRotateAngle(this.lowerArmRight, 0.0F, 0.0F, 0.10000736647217022F);
        this.upperArmRight.addChild(this.lowerArmRight);
        this.upperArmLeft = new ModelRenderer(this, 49, 4);
        this.upperArmLeft.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.upperArmLeft.addBox(-1.0F, -2.0F, -1.0F, 2, 8, 2);
        this.setRotateAngle(this.upperArmLeft, 0.0F, 0.0F, 0.10000736647217022F);
        this.Chest.addChild(this.upperArmLeft);
        this.lowerArmLeft = new ModelRenderer(this, 50, 16);
        this.lowerArmLeft.setRotationPoint(-1.0F, 5.900002F, -1.1920929E-7F);
        this.lowerArmLeft.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2);
        this.setRotateAngle(this.lowerArmLeft, 0.0F, 0.0F, -0.10000736647217022F);
        this.upperArmLeft.addChild(this.lowerArmLeft);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.Chest.addChild(this.Head);
        this.hipRight = new ModelRenderer(this, 28, 0);
        this.hipRight.setRotationPoint(1.9F, 5.1000004F, -0.8F);
        this.hipRight.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
        this.Abdomen.addChild(this.hipRight);
        this.upperLegRight = new ModelRenderer(this, 1, 33);
        this.upperLegRight.setRotationPoint(0.6F, 2.0F, 1.1F);
        this.upperLegRight.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2);
        this.upperLegRight.mirror = true;
        this.hipRight.addChild(this.upperLegRight);
        this.lowerLegRight = new ModelRenderer(this, 10, 30);
        this.lowerLegRight.setRotationPoint(0.0F, 4.0F, -1.0000001F);
        this.lowerLegRight.addBox(-1.0F, 0.0F, 0.0F, 2, 6, 2);
        this.upperLegRight.addChild(this.lowerLegRight);
        this.hipLeft = new ModelRenderer(this, 35, 0);
        this.hipLeft.setRotationPoint(-2.7F, 5.1000004F, -0.8F);
        this.hipLeft.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
        this.Abdomen.addChild(this.hipLeft);
        this.upperLegLeft = new ModelRenderer(this, 1, 25);
        this.upperLegLeft.setRotationPoint(0.6F, 2.0F, 1.1F);
        this.upperLegLeft.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2);
        this.upperLegLeft.mirror = true;
        this.hipLeft.addChild(this.upperLegLeft);
        this.lowerLegLeft = new ModelRenderer(this, 20, 30);
        this.lowerLegLeft.setRotationPoint(0.0F, 4.0F, -1.0000001F);
        this.lowerLegLeft.addBox(-1.0F, 0.0F, 0.0F, 2, 6, 2);
        this.upperLegLeft.addChild(this.lowerLegLeft);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.Abdomen);
    }

    // TODO: NEEDS ATTENTION!
    @Override
    public void setRotationAngles(RifterEntity entity, float limbSwing, float limbSwingAmount, float age, float yaw, float pitch) {
        this.Head.rotateAngleY = (float) Math.toRadians(yaw);
        this.Head.rotateAngleX = (float) Math.toRadians(pitch);

        this.animator.flap(this.upperArmRight, 0.1F, 0.4F, false, 0.0F, -1.0F, age, 0.1F);
        this.animator.flap(this.upperArmLeft, 0.1F, 0.4F, true, 0.0F, -1.0F, age, 0.1F);

        this.animator.flap(this.lowerArmRight, 0.1F, 0.4F, true, 0.4F, -1.0F, age, 0.1F);
        this.animator.flap(this.lowerArmLeft, 0.1F, 0.4F, false, 0.4F, -1.0F, age, 0.1F);

        boolean dragging = entity.hasCaptured();

        float globalSpeed = 0.6F;
        float globalDegree = 1.2F;

        this.Abdomen.rotationPointY = 7.0F + this.animator.computeAnimation(globalSpeed * 2.0F, globalDegree, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);

        this.animator.walk(this.upperLegRight, globalSpeed, globalDegree * 1.2F, false, 0.0F, -0.6F, limbSwing, limbSwingAmount);
        this.animator.walk(this.upperLegLeft, globalSpeed, globalDegree * 1.2F, true, 0.0F, 0.6F, limbSwing, limbSwingAmount);
        this.animator.walk(this.lowerLegRight, globalSpeed, globalDegree * 0.65F, false, -2.2F, 1.0F, limbSwing, limbSwingAmount);
        this.animator.walk(this.lowerLegLeft, globalSpeed, globalDegree * 0.65F, true, -2.2F, -1.0F, limbSwing, limbSwingAmount);

        if (!dragging) {
            this.animator.walk(this.upperArmRight, globalSpeed, globalDegree * 0.8F, true, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.animator.walk(this.upperArmLeft, globalSpeed, globalDegree * 0.8F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
            this.animator.walk(this.lowerArmRight, globalSpeed, globalDegree * 0.6F, true, -1.4F, 0.7F, limbSwing, limbSwingAmount);
            this.animator.walk(this.lowerArmLeft, globalSpeed, globalDegree * 0.6F, false, -1.4F, -0.7F, limbSwing, limbSwingAmount);
        } else {
            this.upperArmRight.rotateAngleX = 0.4F;
            this.upperArmLeft.rotateAngleX = 0.4F;
            this.lowerArmRight.rotateAngleX = 0.3F;
            this.lowerArmLeft.rotateAngleX = 0.3F;
        }
    }

    private void setRotateAngle(ModelRenderer cuboid, float x, float y, float z) {
        cuboid.rotateAngleX = x;
        cuboid.rotateAngleY = y;
        cuboid.rotateAngleZ = z;
    }
}
