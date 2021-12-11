package coda.paleoworld.common.init;

import coda.paleoworld.Paleoworld;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

public class PWTags {
    public static final Tag.Named<Block> AMBER_LOGS = blockTag("amber_logs");

    private static Tag.Named<Block> blockTag(String path) {
        return BlockTags.bind(Paleoworld.MOD_ID + ":" + path);
    }
}
