package com.mushroom.midnight.common.entity.creature;

import com.mushroom.midnight.common.entity.FlyingNavigator;
import com.mushroom.midnight.common.registry.MidnightLootTables;
import com.mushroom.midnight.common.registry.MidnightSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtWithoutMovingGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class NovaEntity extends MonsterEntity implements EntityFlying {
    private static final DataParameter<Boolean> IS_ATTACKING = EntityDataManager.createKey(NovaEntity.class, DataSerializers.BOOLEAN);
    private float heightOffset = 0.5f;
    private int heightOffsetUpdateTime;

    public NovaEntity(EntityType<NovaEntity> entityType, World world) {
        super(entityType, world);
        this.experienceValue = 10;
        setPathPriority(PathNodeType.LAVA, 8f);
        setPathPriority(PathNodeType.DANGER_FIRE, 0f);
        setPathPriority(PathNodeType.DAMAGE_FIRE, 0f);
        this.moveController = new FlyingMovementController(this);
    }

    @Override
    protected PathNavigator createNavigator(World world) {
        FlyingNavigator nav = new FlyingNavigator(this, world);
        nav.setCanFloat(true);
        nav.setCanOpenDoors(false);
        nav.setCanEnterDoors(false);
        return nav;
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.5f;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (getPosition().getY() > 50) {
            return false;
        }
        BlockState belowState = world.getBlockState(new BlockPos(this).down());
        return belowState.isFullCube() && belowState.canEntitySpawn(this);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_ATTACKING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1d, false) {
            @Override
            public void resetTask() {
                super.resetTask();
                setAttacking(false);
            }

            @Override
            public void startExecuting() {
                super.startExecuting();
                setAttacking(true);
            }
        });
        this.goalSelector.addGoal(5, new MoveTowardsTargetGoal(this, 1d, 40));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.8d));
        this.goalSelector.addGoal(8, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 8f, 0.01f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, true, false));
    }

    @Override
    protected void updateAITasks() {
        if (isWet()) {
            this.attackEntityFrom(DamageSource.DROWN, 1f);
        }
        // stay over the target by moment
        --this.heightOffsetUpdateTime;
        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5f + (float) this.rand.nextGaussian() * 3f;
        }
        LivingEntity target = getAttackTarget();
        if (target != null && target.isAlive() && target.posY + (double) target.getEyeHeight() > this.posY + (double) getEyeHeight() + (double) this.heightOffset) {
            this.motionY += (0.3 - this.motionY) * 0.3;
            this.isAirBorne = true;
        }
        super.updateAITasks();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3d);
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16d);
        getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4d);
        getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40d);
        getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2d);
        getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.4d);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return MidnightLootTables.LOOT_TABLE_NOVA;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return MidnightSounds.NOVA_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MidnightSounds.NOVA_DEATH;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MidnightSounds.NOVA_IDLE;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getBrightnessForRender() {
        return 14 << 20 | 14 << 4;
    }

    public boolean isAttacking() {
        return dataManager.get(IS_ATTACKING);
    }

    public void setAttacking(boolean isAttacking) {
        dataManager.set(IS_ATTACKING, isAttacking);
    }
}
