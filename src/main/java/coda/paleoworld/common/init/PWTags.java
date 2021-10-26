package coda.paleoworld.common.init;

import coda.paleoworld.Paleoworld;
import net.minecraft.tags.BlockTags;
import net.minecraft.block.Block;
import net.minecraft.tags.ITag;

public class PWTags {
    public static final ITag.INamedTag<Block> AMBER_LOGS = blockTag("amber_logs");

    private static ITag.INamedTag<Block> blockTag(String path) {
        return BlockTags.bind(Paleoworld.MOD_ID + ":" + path);
    }
}
