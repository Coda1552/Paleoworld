package coda.paleoworld.common.entities;

import coda.paleoworld.common.init.PWItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;

public class RhamphorhynchusEntity extends TamableAnimal {

    public RhamphorhynchusEntity(EntityType<? extends TamableAnimal> p_i48574_1_, Level p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
        this.setTame(false);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3D).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FLYING_SPEED, 0.7D);
    }

    @Override
    public void tick() {
        super.tick();
        Player player = (Player) getVehicle();

        if (player != null) {
            player.startFallFlying();
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!this.level.isClientSide) {
            if (this.isTame()) {
                if (itemstack.is(ItemTags.FISHES) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.isCreative()) {
                        itemstack.shrink(1);
                    }

                    this.heal((float) item.getFoodProperties().getNutrition());
                    return InteractionResult.SUCCESS;
                }

                if (isShiftKeyDown()) {
                    startRiding(player, true);
                }

                // TODO change tame item
            } else if (item == Items.COD) {
                if (!player.isCreative()) {
                    itemstack.shrink(1);
                }

                if (this.random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setOrderedToSit(true);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }

                return InteractionResult.SUCCESS;
            }
        }
            return super.mobInteract(player, hand);
    }

    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isInvulnerableTo(p_70097_1_)) {
            return false;
        } else {
            Entity entity = p_70097_1_.getEntity();
            this.setOrderedToSit(false);
            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
            }

            return super.hurt(p_70097_1_, p_70097_2_);
        }
    }

    public void setTame(boolean p_70903_1_) {
        super.setTame(p_70903_1_);
        if (p_70903_1_) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(30.0D);
            this.setHealth(30.0F);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
        }

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PWItems.RHAMPHORHYNCHUS_SPAWN_EGG.get());
    }
}
