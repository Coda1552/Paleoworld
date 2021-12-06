package coda.paleoworld.common.entities;

import coda.paleoworld.common.init.PWEntities;
import coda.paleoworld.common.init.PWItems;
import coda.paleoworld.common.init.PWSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class GlyptodonEntity extends AnimalEntity implements IEquipable, IRideable {
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.CARROT);
    private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.defineId(GlyptodonEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> SADDLED = EntityDataManager.defineId(GlyptodonEntity.class, DataSerializers.BOOLEAN);
    private final BoostHelper steering = new BoostHelper(this.entityData, BOOST_TIME, SADDLED);

    public GlyptodonEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.15D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.5D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.15D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof PlayerEntity)) {
            return false;
        } else {
            PlayerEntity playerentity = (PlayerEntity)entity;
            return playerentity.getMainHandItem().getItem() == Items.CARROT_ON_A_STICK || playerentity.getOffhandItem().getItem() == Items.CARROT_ON_A_STICK;
        }
    }

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
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

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        this.steering.addAdditionalSaveData(p_213281_1_);
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.steering.readAdditionalSaveData(p_70037_1_);
    }


    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }


    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        boolean flag = this.isFood(p_230254_1_.getItemInHand(p_230254_2_));
        if (!flag && this.isSaddled() && !this.isVehicle() && !p_230254_1_.isSecondaryUseActive()) {
            if (!this.level.isClientSide) {
                p_230254_1_.startRiding(this);
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            ActionResultType actionresulttype = super.mobInteract(p_230254_1_, p_230254_2_);
            if (!actionresulttype.consumesAction()) {
                ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
                return itemstack.getItem() == Items.SADDLE ? itemstack.interactLivingEntity(p_230254_1_, this, p_230254_2_) : ActionResultType.PASS;
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
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return PWEntities.GLYPTODON.get().create(p_241840_1_);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(PWItems.GLYPTODON_SPAWN_EGG.get());
    }

    @Override
    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 0.35F;
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void equipSaddle(@Nullable SoundCategory p_230266_1_) {
        this.steering.setSaddle(true);
        if (p_230266_1_ != null) {
            this.level.playSound(null, this, SoundEvents.PIG_SADDLE, p_230266_1_, 0.5F, 1.0F);
        }
    }

    public void travel(Vector3d p_213352_1_) {
        this.travel(this, this.steering, p_213352_1_);
    }

    public float getSteeringSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225F;
    }

    public void travelWithInput(Vector3d p_230267_1_) {
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
