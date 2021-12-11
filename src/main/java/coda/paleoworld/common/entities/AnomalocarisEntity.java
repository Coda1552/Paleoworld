package coda.paleoworld.common.entities;

import coda.paleoworld.common.init.PWItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class AnomalocarisEntity extends AbstractFish {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(AnomalocarisEntity.class, EntityDataSerializers.INT);

    public AnomalocarisEntity(EntityType<? extends AbstractFish> p_i48855_1_, Level p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(PWItems.CEPHALASPIS_BUCKET.get());
    }

    @Override
    public SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PWItems.CEPHALASPIS_SPAWN_EGG.get());
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 3;
    }

    public void saveToBucketTag(ItemStack bucket) {
        super.saveToBucketTag(bucket);
        CompoundTag tag = bucket.getOrCreateTag();
        tag.putInt("Variant", this.getVariant());
    }

    @Override
    public void defineSynchedData() {
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
}