package coda.paleoworld.common.entities;

import coda.paleoworld.common.init.PWItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class HaikouichthysEntity extends AbstractFish {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(HaikouichthysEntity.class, EntityDataSerializers.INT);

    public HaikouichthysEntity(EntityType<? extends AbstractFish> p_i48855_1_, Level p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(PWItems.HAIKOUICHTHYS_BUCKET.get());
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PWItems.HAIKOUICHTHYS_SPAWN_EGG.get());
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 3;
    }

    public void saveToBucketTag(ItemStack bucket) {
        super.saveToBucketTag(bucket);
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("Variant", this.getVariant());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
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
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (dataTag != null && dataTag.contains("Variant", 3)) {
            this.setVariant(dataTag.getInt("Variant"));
        }
        else {
            setVariant(random.nextInt(4));
        }
        return spawnDataIn;
    }
}