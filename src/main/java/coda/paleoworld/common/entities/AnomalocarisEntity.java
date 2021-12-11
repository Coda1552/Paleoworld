package coda.paleoworld.common.entities;

import coda.paleoworld.common.init.PWEntities;
import coda.paleoworld.common.init.PWItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class AnomalocarisEntity extends Animal implements Bucketable {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(AnomalocarisEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(AnomalocarisEntity.class, EntityDataSerializers.BOOLEAN);

    public AnomalocarisEntity(EntityType<? extends Animal> p_i48855_1_, Level p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new AnomalocarisMoveControl(this);
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 40));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.3F);
    }

    protected PathNavigation createNavigation(Level p_27480_) {
        return new WaterBoundPathNavigation(this, p_27480_);
    }

    public void travel(Vec3 p_27490_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, p_27490_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_27490_);
        }

    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected void handleAirSupply(int p_30344_) {
        if (this.isAlive() && !this.isInWaterOrBubble()) {
            this.setAirSupply(p_30344_ - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(DamageSource.DROWN, 2.0F);
            }
        } else {
            this.setAirSupply(300);
        }

    }

    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        this.handleAirSupply(i);
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBeLeashed(Player p_30346_) {
        return false;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(PWItems.CEPHALASPIS_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return null;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PWItems.ANOMALOCARIS_SPAWN_EGG.get());
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 3;
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean p_149196_) {
        this.entityData.set(FROM_BUCKET, p_149196_);
    }

    public void saveToBucketTag(ItemStack bucket) {
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        CompoundTag tag = bucket.getOrCreateTag();
        tag.putInt("Variant", this.getVariant());
    }

    @Override
    public void loadFromBucketTag(CompoundTag p_148832_) {
        Bucketable.loadDefaultDataFromBucketTag(this, p_148832_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return PWEntities.ANOMALOCARIS.get().create(p_146743_);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    private void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
        compound.putBoolean("FromBucket", this.fromBucket());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
        this.setFromBucket(compound.getBoolean("FromBucket"));
    }

    static class AnomalocarisMoveControl extends MoveControl {
        private final AnomalocarisEntity creature;

        AnomalocarisMoveControl(AnomalocarisEntity p_27501_) {
            super(p_27501_);
            this.creature = p_27501_;
        }

        public void tick() {
            if (this.creature.isEyeInFluid(FluidTags.WATER)) {
                this.creature.setDeltaMovement(this.creature.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.creature.getNavigation().isDone()) {
                float f = (float)(this.speedModifier * this.creature.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.creature.setSpeed(Mth.lerp(0.125F, this.creature.getSpeed(), f));
                double d0 = this.wantedX - this.creature.getX();
                double d1 = this.wantedY - this.creature.getY();
                double d2 = this.wantedZ - this.creature.getZ();
                if (d1 != 0.0D) {
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    this.creature.setDeltaMovement(this.creature.getDeltaMovement().add(0.0D, (double)this.creature.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.creature.setYRot(this.rotlerp(this.creature.getYRot(), f1, 90.0F));
                    this.creature.yBodyRot = this.creature.getYRot();
                }

            } else {
                this.creature.setSpeed(0.0F);
            }
        }
    }

}