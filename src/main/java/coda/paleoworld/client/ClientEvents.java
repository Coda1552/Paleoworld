package coda.paleoworld.client;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.renderer.*;
import coda.paleoworld.client.renderer.layer.RhamphorhynchusLayerRenderer;
import coda.paleoworld.common.init.PWBlocks;
import coda.paleoworld.common.init.PWEntities;
import coda.paleoworld.common.items.PWSpawnEggItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD, modid = Paleoworld.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(PWEntities.DAWN_HORSE.get(), DawnHorseRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PWEntities.CEPHALASPIS.get(), CephalaspisRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PWEntities.GLYPTODON.get(), GlyptodonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PWEntities.ASTRASPIS.get(), AstraspisRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PWEntities.HAIKOUICHTHYS.get(), HaikouichthysRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PWEntities.RHAMPHORHYNCHUS.get(), RhamphorhynchusRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PWEntities.TRILOBITE.get(), TrilobiteRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PWEntities.RHAMPHORHYNCHUS.get(), RhamphorhynchusRenderer::new);

        RenderTypeLookup.setRenderLayer(PWBlocks.CLOUDINA.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(PWBlocks.DEAD_CLOUDINA.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(PWBlocks.CLOUDINA_FAN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(PWBlocks.DEAD_CLOUDINA_FAN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(PWBlocks.CLOUDINA_WALL_FAN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(PWBlocks.DEAD_CLOUDINA_WALL_FAN.get(), RenderType.cutout());

        PlayerRenderer managerDefault = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("default");
        PlayerRenderer managerSlim = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("slim");
        managerDefault.addLayer(new RhamphorhynchusLayerRenderer<>(managerDefault));
        managerSlim.addLayer(new RhamphorhynchusLayerRenderer<>(managerSlim));
    }

    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
         ItemColors handler = event.getItemColors();
         IItemColor eggColor = (stack, tintIndex) -> ((PWSpawnEggItem) stack.getItem()).getColor(tintIndex);
         for (PWSpawnEggItem e : PWSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}
