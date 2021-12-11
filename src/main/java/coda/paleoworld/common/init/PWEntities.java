package coda.paleoworld.common.init;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.common.entities.RhamphorhynchusEntity;
import coda.paleoworld.common.entities.TrilobiteEntity;
import coda.paleoworld.common.entities.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PWEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Paleoworld.MOD_ID);

    public static final RegistryObject<EntityType<DawnHorseEntity>> DAWN_HORSE = create("dawn_horse", EntityType.Builder.of(DawnHorseEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<CephalaspisEntity>> CEPHALASPIS = create("cephalaspis", EntityType.Builder.of(CephalaspisEntity::new, MobCategory.WATER_CREATURE).sized(0.45F, 0.35F));
    public static final RegistryObject<EntityType<GlyptodonEntity>> GLYPTODON = create("glyptodon", EntityType.Builder.of(GlyptodonEntity::new, MobCategory.CREATURE).sized(1.35F, 1.35F));
    public static final RegistryObject<EntityType<AstraspisEntity>> ASTRASPIS = create("astraspis", EntityType.Builder.of(AstraspisEntity::new, MobCategory.WATER_CREATURE).sized(0.45F, 0.35F));
    public static final RegistryObject<EntityType<HaikouichthysEntity>> HAIKOUICHTHYS = create("haikouichthys", EntityType.Builder.of(HaikouichthysEntity::new, MobCategory.WATER_CREATURE).sized(0.3F, 0.25F));
    public static final RegistryObject<EntityType<TrilobiteEntity>> TRILOBITE = create("trilobite", EntityType.Builder.of(TrilobiteEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.25F));
    public static final RegistryObject<EntityType<RhamphorhynchusEntity>> RHAMPHORHYNCHUS = create("rhamphorhynchus", EntityType.Builder.of(RhamphorhynchusEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<AnomalocarisEntity>> ANOMALOCARIS = create("anomalocaris", EntityType.Builder.of(AnomalocarisEntity::new, MobCategory.WATER_CREATURE).sized(0.75F, 0.5F));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(Paleoworld.MOD_ID + "." + name));
    }
}