package coda.paleoworld.common.init;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.common.entities.GlyptodonEntity;
import coda.paleoworld.common.items.PWBucketItem;
import coda.paleoworld.common.items.PWItemTier;
import coda.paleoworld.common.items.PWSpawnEggItem;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PWItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Paleoworld.MOD_ID);

    public static final Item DINOBEAN_ON_A_STICK_PROPERTIES = new OnAStickItem<GlyptodonEntity>( new Item.Properties().tab(Paleoworld.GROUP).stacksTo(1).durability(30), PWEntities.GLYPTODON.get(), 5);

    // Materials
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () -> new Item(new Item.Properties().tab(Paleoworld.GROUP)));

    // Tools, Gear, & Buckets
    public static final RegistryObject<Item> AMBER_SWORD = ITEMS.register("amber_sword", () -> new SwordItem(PWItemTier.AMBER, 3, -2.4F, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> AMBER_PICKAXE = ITEMS.register("amber_pickaxe", () -> new PickaxeItem(PWItemTier.AMBER, 1, -2.8F, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> AMBER_AXE = ITEMS.register("amber_axe", () -> new AxeItem(PWItemTier.AMBER, 6.0F, -3.0F, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> AMBER_SHOVEL = ITEMS.register("amber_shovel", () -> new ShovelItem(PWItemTier.AMBER, 1.5F, -3.0F, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> AMBER_HOE = ITEMS.register("amber_hoe", () -> new HoeItem(PWItemTier.AMBER, 0, -3.0F, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> CEPHALASPIS_BUCKET = ITEMS.register("cephalaspis_bucket", () -> new FishBucketItem(PWEntities.CEPHALASPIS, () -> Fluids.WATER, new Item.Properties().tab(Paleoworld.GROUP).stacksTo(1)));
    public static final RegistryObject<Item> ASTRRASPIS_BUCKET = ITEMS.register("astraspis_bucket", () -> new FishBucketItem(PWEntities.ASTRASPIS, () -> Fluids.WATER, new Item.Properties().tab(Paleoworld.GROUP).stacksTo(1)));
    public static final RegistryObject<Item> HAIKOUICHTHYS_BUCKET = ITEMS.register("haikouichthys_bucket", () -> new FishBucketItem(PWEntities.HAIKOUICHTHYS, () -> Fluids.WATER, new Item.Properties().tab(Paleoworld.GROUP).stacksTo(1)));
    public static final RegistryObject<Item> TRILOBITE_BUCKET = ITEMS.register("trilobite_bucket", () -> new PWBucketItem(PWEntities.TRILOBITE, () -> Fluids.WATER, new Item.Properties().tab(Paleoworld.GROUP).stacksTo(1)));
    public static final RegistryObject<Item> DINOBEAN_ON_A_STICK = ITEMS.register("dinobean_on_a_stick", () -> DINOBEAN_ON_A_STICK_PROPERTIES);
    // Food
    public static final RegistryObject<Item> CEPHALASPIS_TAIL = ITEMS.register("cephalaspis_tail", () -> new Item(new Item.Properties().tab(Paleoworld.GROUP).food(new Food.Builder().saturationMod(0.25F).nutrition(4).build())));
    public static final RegistryObject<Item> DINOBEAN = ITEMS.register("dinobean", () -> new Item(new Item.Properties().tab(Paleoworld.GROUP).food(new Food.Builder().saturationMod(0.3F).nutrition(2).build())));

    // Spawn Eggs
    public static final RegistryObject<Item> DAWN_HORSE_SPAWN_EGG = ITEMS.register("dawn_horse_spawn_egg", () -> new PWSpawnEggItem(PWEntities.DAWN_HORSE, 0xdbae6b, 0xebe0c4, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> CEPHALASPIS_SPAWN_EGG = ITEMS.register("cephalaspis_spawn_egg", () -> new PWSpawnEggItem(PWEntities.CEPHALASPIS, 0x353e0f, 0x637322, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> GLYPTODON_SPAWN_EGG = ITEMS.register("glyptodon_spawn_egg", () -> new PWSpawnEggItem(PWEntities.GLYPTODON, 0x68693b, 0x3a2e27, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> ASTRRASPIS_SPAWN_EGG = ITEMS.register("astraspis_spawn_egg", () -> new PWSpawnEggItem(PWEntities.ASTRASPIS, 0xa79d96, 0x6f6a71, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> HAIKOUICHTHYS_SPAWN_EGG = ITEMS.register("haikouichthys_spawn_egg", () -> new PWSpawnEggItem(PWEntities.HAIKOUICHTHYS, 0x63673f, 0x83836e, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> TRILOBITE_SPAWN_EGG = ITEMS.register("trilobite_spawn_egg", () -> new PWSpawnEggItem(PWEntities.TRILOBITE, 0x6f623b, 0x83836e, new Item.Properties().tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> RHAMPHORHYNCHUS_SPAWN_EGG = ITEMS.register("rhamphorhynchus_spawn_egg", () -> new PWSpawnEggItem(PWEntities.RHAMPHORHYNCHUS, 0x2d3d4d, 0x4a2720, new Item.Properties().tab(Paleoworld.GROUP)));

    // Blocks
    public static final RegistryObject<Item> CLOUDINA_FAN = ITEMS.register("cloudina_fan", () -> new WallOrFloorItem(PWBlocks.CLOUDINA_FAN.get(), PWBlocks.CLOUDINA_WALL_FAN.get(), (new Item.Properties()).tab(Paleoworld.GROUP)));
    public static final RegistryObject<Item> DEAD_CLOUDINA_FAN = ITEMS.register("dead_cloudina_fan", () -> new WallOrFloorItem(PWBlocks.DEAD_CLOUDINA_FAN.get(), PWBlocks.DEAD_CLOUDINA_WALL_FAN.get(), (new Item.Properties()).tab(Paleoworld.GROUP)));

}
