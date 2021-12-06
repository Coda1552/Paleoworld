package coda.paleoworld.common.init;

import coda.paleoworld.Paleoworld;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class PWBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Paleoworld.MOD_ID);

    public static final Block CLOUDINA_DEAD =  new DeadCoralPlantBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).requiresCorrectToolForDrops());
    public static final Block CLOUDINA_FAN_DEAD =  new CoralFanBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).requiresCorrectToolForDrops());
    public static final Block CLOUDINA_WALL_FAN_DEAD =  new DeadCoralWallFanBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(PWBlocks.CLOUDINA_FAN_DEAD));

    public static final RegistryObject<Block> AMBER_BLOCK = register("amber_block", () -> new Block(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_ORANGE).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).strength(2.5F, 3.0F).requiresCorrectToolForDrops().noCollission()), new Item.Properties().tab(Paleoworld.GROUP));
    public static final RegistryObject<Block> COOKSONIA = register("cooksonia", () -> new BushBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_YELLOW).sound(SoundType.GRASS).instabreak().noCollission()), new Item.Properties().tab(Paleoworld.GROUP));

    public static final RegistryObject<Block> CLOUDINA = register("cloudina", () -> new CoralPlantBlock(PWBlocks.CLOUDINA_DEAD, AbstractBlock.Properties.of(Material.CORAL, MaterialColor.COLOR_GREEN).sound(SoundType.WET_GRASS).requiresCorrectToolForDrops().noCollission()), new Item.Properties().tab(Paleoworld.GROUP));
    public static final RegistryObject<Block> DEAD_CLOUDINA = register("dead_cloudina", () -> CLOUDINA_DEAD, new Item.Properties().tab(Paleoworld.GROUP));

    public static final RegistryObject<Block> CLOUDINA_FAN = register("cloudina_fan", () -> new CoralFinBlock(PWBlocks.CLOUDINA_FAN_DEAD, AbstractBlock.Properties.of(Material.CORAL, MaterialColor.COLOR_GREEN).sound(SoundType.WET_GRASS).requiresCorrectToolForDrops().noCollission()), null);
    public static final RegistryObject<Block> DEAD_CLOUDINA_FAN = register("dead_cloudina_fan", () -> CLOUDINA_FAN_DEAD, null);

    public static final RegistryObject<Block> CLOUDINA_WALL_FAN = register("cloudina_wall_fan", () -> new CoralWallFanBlock(PWBlocks.CLOUDINA_WALL_FAN_DEAD, AbstractBlock.Properties.of(Material.CORAL, MaterialColor.COLOR_GREEN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(PWBlocks.CLOUDINA_FAN.get())), null);
    public static final RegistryObject<Block> DEAD_CLOUDINA_WALL_FAN = register("dead_cloudina_wall_fan", () -> CLOUDINA_WALL_FAN_DEAD, null);


    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Item.Properties itemProperties) {
        return register(name, block, BlockItem::new, itemProperties);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, BiFunction<Block, Item.Properties, BlockItem> item, Item.Properties itemProperties) {
        final RegistryObject<T> registryObject = BLOCKS.register(name, block);
        if (itemProperties != null) PWItems.ITEMS.register(name, () -> item == null ? new BlockItem(registryObject.get(), itemProperties) : item.apply(registryObject.get(), itemProperties));
        return registryObject;
    }
}