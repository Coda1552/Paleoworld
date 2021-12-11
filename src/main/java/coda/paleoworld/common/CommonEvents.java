package coda.paleoworld.common;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.common.init.PWItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = Paleoworld.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void onLogStripped(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack().getItem() instanceof AxeItem) {
            Level world = event.getWorld();
            BlockPos pos = event.getPos();
            BlockState state = world.getBlockState(pos);

            if (state.is(Blocks.OAK_LOG) && world.random.nextFloat() > 0.9F) {
                ItemStack stack = new ItemStack(PWItems.AMBER.get());
                ItemEntity entity = EntityType.ITEM.create(world);

                entity.setItem(stack);
                entity.moveTo(pos.getX(), pos.getY(), pos.getZ());
                world.addFreshEntity(entity);
            }
        }
    }

}
