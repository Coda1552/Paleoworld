package coda.paleoworld.common.entities;

import coda.paleoworld.common.init.PWEntities;
import coda.paleoworld.common.init.PWItems;
import coda.paleoworld.common.init.PWSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class GlyptodonEntity extends Animal implements Saddleable, ItemSteerable {
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.CARROT);
    private static final EntityDataAccessor<Integer> BOOST_TIME = SynchedEntityData.defineId(GlyptodonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(GlyptodonEntity.class, EntityDataSerializers.BOOLEAN);
    private final ItemBasedSteering steering = new ItemBasedSteering(this.entityData, BOOST_TIME, SADDLED);

    public GlyptodonEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.15D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.5D, Ingredient.of(PWItems.DINOBEAN_ON_A_STICK.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.15D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof Player)) {
            return false;
        } else {
            Player player = (Player)entity;
            return player.getMainHandItem().getItem() == PWItems.DINOBEAN_ON_A_STICK.get() || player.getOffhandItem().getItem() == PWItems.DINOBEAN_ON_A_STICK.get();
        }
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_184206_1_) {
        if (BOOST_TIME.equals(p_184206_1_) && this.level.isClientSide) {
            this.steering.onSynced();
        }

        super.onSyncedDataUpdated(p_184206_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SADDLED, false);
        this.entityData.define(BOOST_TIME, 0);
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        this.steering.addAdditionalSaveData(p_213281_1_);
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.steering.readAdditionalSaveData(p_70037_1_);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }


    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        boolean flag = this.isFood(player.getItemInHand(hand));
        if (!flag && this.isSaddled() && !this.isVehicle() && !player.isSecondaryUseActive()) {
            if (!this.level.isClientSide) {
                player.startRiding(this);
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            InteractionResult actionresulttype = super.mobInteract(player, hand);
            if (!actionresulttype.consumesAction()) {
                ItemStack itemstack = player.getItemInHand(hand);
                return itemstack.getItem() == Items.SADDLE ? itemstack.interactLivingEntity(player, this, hand) : InteractionResult.PASS;
            } else {
                return actionresulttype;
            }
        }
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.LLAMA_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return PWSounds.GLYPTODON_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return PWSounds.GLYPTODON_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return PWSounds.GLYPTODON_AMBIENT.get();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return PWEntities.GLYPTODON.get().create(p_241840_1_);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PWItems.GLYPTODON_SPAWN_EGG.get());
    }

    @Override
    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return isBaby() ? 0.15F : 0.35F;
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource p_230266_1_) {
        this.steering.setSaddle(true);
        if (p_230266_1_ != null) {
            this.level.playSound(null, this, SoundEvents.PIG_SADDLE, p_230266_1_, 0.5F, 1.0F);
        }
    }

    public void travel(Vec3 p_213352_1_) {
        this.travel(this, this.steering, p_213352_1_);
    }

    public float getSteeringSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225F;
    }

    public void travelWithInput(Vec3 p_230267_1_) {
        super.travel(p_230267_1_);
    }

    public boolean boost() {
        return this.steering.boost(this.getRandom());
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }

    }

    @Override
    public boolean isSaddled() {
        return this.steering.hasSaddle();
    }
}
