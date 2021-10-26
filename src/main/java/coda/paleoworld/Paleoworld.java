package coda.paleoworld;

import coda.paleoworld.common.entities.RhamphorhynchusEntity;
import coda.paleoworld.common.entities.AstraspisEntity;
import coda.paleoworld.common.entities.DawnHorseEntity;
import coda.paleoworld.common.entities.GlyptodonEntity;
import coda.paleoworld.common.init.PWBlocks;
import coda.paleoworld.common.init.PWEntities;
import coda.paleoworld.common.init.PWItems;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Paleoworld.MOD_ID)
public class Paleoworld {
    public static final String MOD_ID = "paleoworld";

    public Paleoworld() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::registerEntityAttributes);
        bus.addListener(this::registerCommon);

        PWItems.ITEMS.register(bus);
        PWBlocks.BLOCKS.register(bus);
        PWEntities.ENTITIES.register(bus);
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        EntitySpawnPlacementRegistry.register(PWEntities.GLYPTODON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(PWEntities.DAWN_HORSE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(PWEntities.HAIKOUICHTHYS.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, AbstractFishEntity::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(PWEntities.ASTRASPIS.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, AbstractFishEntity::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(PWEntities.CEPHALASPIS.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, AbstractFishEntity::checkFishSpawnRules);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
         event.put(PWEntities.DAWN_HORSE.get(), DawnHorseEntity.createAttributes().build());
         event.put(PWEntities.CEPHALASPIS.get(), AbstractFishEntity.createAttributes().build());
         event.put(PWEntities.GLYPTODON.get(), GlyptodonEntity.createAttributes().build());
         event.put(PWEntities.ASTRASPIS.get(), AstraspisEntity.createAttributes().build());
         event.put(PWEntities.HAIKOUICHTHYS.get(), AbstractFishEntity.createAttributes().build());
         event.put(PWEntities.RHAMPHORHYNCHUS.get(), RhamphorhynchusEntity.createAttributes().build());
    }

    public static final ItemGroup GROUP = new ItemGroup(Paleoworld.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PWItems.AMBER.get());
        }
    };
}
