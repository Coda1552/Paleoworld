package coda.paleoworld;

import coda.paleoworld.common.entities.DawnHorseEntity;
import coda.paleoworld.common.entities.GlyptodonEntity;
import coda.paleoworld.common.entities.RhamphorhynchusEntity;
import coda.paleoworld.common.entities.TrilobiteEntity;
import coda.paleoworld.common.init.PWBlocks;
import coda.paleoworld.common.init.PWEntities;
import coda.paleoworld.common.init.PWItems;
import coda.paleoworld.common.init.PWSounds;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

@Mod(Paleoworld.MOD_ID)
public class Paleoworld {
    public static final String MOD_ID = "paleoworld";
    public static final List<Runnable> CALLBACKS = new ArrayList<>();

    public Paleoworld() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::registerEntityAttributes);
        bus.addListener(this::registerCommon);
        bus.addListener(this::registerClient);

        PWItems.ITEMS.register(bus);
        PWSounds.SOUNDS.register(bus);
        PWBlocks.BLOCKS.register(bus);
        PWEntities.ENTITIES.register(bus);
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        SpawnPlacements.register(PWEntities.GLYPTODON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(PWEntities.DAWN_HORSE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(PWEntities.HAIKOUICHTHYS.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TrilobiteEntity::checkFishSpawnRules);
        SpawnPlacements.register(PWEntities.ASTRASPIS.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TrilobiteEntity::checkFishSpawnRules);
        SpawnPlacements.register(PWEntities.CEPHALASPIS.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TrilobiteEntity::checkFishSpawnRules);
        SpawnPlacements.register(PWEntities.TRILOBITE.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TrilobiteEntity::checkFishSpawnRules);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(PWEntities.DAWN_HORSE.get(), DawnHorseEntity.createAttributes().build());
        event.put(PWEntities.CEPHALASPIS.get(), AbstractFish.createAttributes().build());
        event.put(PWEntities.GLYPTODON.get(), GlyptodonEntity.createAttributes().build());
        event.put(PWEntities.ASTRASPIS.get(), AbstractFish.createAttributes().build());
        event.put(PWEntities.HAIKOUICHTHYS.get(), AbstractFish.createAttributes().build());
        event.put(PWEntities.RHAMPHORHYNCHUS.get(), RhamphorhynchusEntity.createAttributes().build());
        event.put(PWEntities.TRILOBITE.get(), AbstractFish.createAttributes().build());
    }

    private void registerClient(FMLClientSetupEvent event) {
        CALLBACKS.forEach(Runnable::run);
        CALLBACKS.clear();
    }

    public static final CreativeModeTab GROUP = new CreativeModeTab(Paleoworld.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PWItems.AMBER.get());
        }
    };
}
