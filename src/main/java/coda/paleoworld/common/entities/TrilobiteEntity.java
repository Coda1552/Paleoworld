package coda.paleoworld.common.entities;

import coda.paleoworld.common.entities.HaikouichthysEntity;
import coda.paleoworld.common.init.PWItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class TrilobiteEntity extends AbstractFishEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(TrilobiteEntity.class, DataSerializers.INT);

    public TrilobiteEntity(EntityType<? extends AbstractFishEntity> p_i48855_1_, World p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(PWItems.TRILOBITE_BUCKET.get());
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(PWItems.TRILOBITE_SPAWN_EGG.get());
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 3;
    }

    protected void saveToBucketTag(ItemStack bucket) {
        super.saveToBucketTag(bucket);
        CompoundNBT compoundnbt = bucket.getOrCreateTag();
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
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (dataTag != null && dataTag.contains("Variant", 3)) {
            this.setVariant(dataTag.getInt("Variant"));
        }
        else {
            setVariant(random.nextInt(4));
        }
        return spawnDataIn;
    }

    public static boolean checkPrehistoricFishSpawnRules(EntityType<? extends AbstractFishEntity> p_223363_0_, IWorld p_223363_1_, SpawnReason p_223363_2_, BlockPos p_223363_3_, Random p_223363_4_) {
        return p_223363_1_.getBlockState(p_223363_3_.below()).is(Blocks.WATER);
    }
}