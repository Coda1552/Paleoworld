package coda.paleoworld.common.init;

import coda.paleoworld.Paleoworld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PWSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Paleoworld.MOD_ID);

    public static final RegistryObject<SoundEvent> GLYPTODON_AMBIENT = SOUNDS.register("glyptodon.ambient", () -> new SoundEvent(new ResourceLocation(Paleoworld.MOD_ID, "glyptodon.ambient")));
    public static final RegistryObject<SoundEvent> GLYPTODON_HURT = SOUNDS.register("glyptodon.hurt", () -> new SoundEvent(new ResourceLocation(Paleoworld.MOD_ID, "glyptodon.hurt")));
    public static final RegistryObject<SoundEvent> GLYPTODON_DEATH = SOUNDS.register("glyptodon.death", () -> new SoundEvent(new ResourceLocation(Paleoworld.MOD_ID, "glyptodon.death")));
}