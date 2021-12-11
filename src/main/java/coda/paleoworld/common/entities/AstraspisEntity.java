package coda.paleoworld.common.entities;

import coda.paleoworld.common.init.PWItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class AstraspisEntity extends AbstractFish {

    public AstraspisEntity(EntityType<? extends AbstractFish> p_i48855_1_, Level p_i48855_2_) {
        super(p_i48855_1_, p_i48855_2_);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(PWItems.ASTRRASPIS_BUCKET.get());
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PWItems.ASTRRASPIS_SPAWN_EGG.get());
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 3;
    }
}