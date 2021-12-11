package coda.paleoworld.common.init;

import coda.paleoworld.Paleoworld;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class PWDimensions {
    public static final ResourceKey<Level> PALEOWORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Paleoworld.MOD_ID, "paleoworld"));
}
