package coda.paleoworld.client;

import coda.paleoworld.Paleoworld;
import coda.paleoworld.client.model.*;
import coda.paleoworld.client.renderer.*;
import coda.paleoworld.client.renderer.layer.RhamphorhynchusLayerRenderer;
import coda.paleoworld.common.init.PWBlocks;
import coda.paleoworld.common.init.PWEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD, modid = Paleoworld.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        EntityRenderers.register(PWEntities.DAWN_HORSE.get(), DawnHorseRenderer::new);
        EntityRenderers.register(PWEntities.CEPHALASPIS.get(), CephalaspisRenderer::new);
        EntityRenderers.register(PWEntities.GLYPTODON.get(), GlyptodonRenderer::new);
        EntityRenderers.register(PWEntities.ASTRASPIS.get(), AstraspisRenderer::new);
        EntityRenderers.register(PWEntities.HAIKOUICHTHYS.get(), HaikouichthysRenderer::new);
        EntityRenderers.register(PWEntities.TRILOBITE.get(), TrilobiteRenderer::new);
        EntityRenderers.register(PWEntities.RHAMPHORHYNCHUS.get(), RhamphorhynchusRenderer::new);
        EntityRenderers.register(PWEntities.ANOMALOCARIS.get(), AnomalocarisRenderer::new);

        ForgeHooksClient.registerLayerDefinition(AnomalocarisModel.LAYER, AnomalocarisModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(AstraspisModel.LAYER, AstraspisModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(CephalaspisModel.LAYER, CephalaspisModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(DawnHorseModel.LAYER, DawnHorseModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(HaikouichthysModel.LAYER, HaikouichthysModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(GlyptodonModel.LAYER, GlyptodonModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(RhamphorhynchusModel.LAYER, RhamphorhynchusModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(TrilobiteModel.LAYER, TrilobiteModel::createBodyLayer);

        ItemBlockRenderTypes.setRenderLayer(PWBlocks.CLOUDINA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PWBlocks.DEAD_CLOUDINA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PWBlocks.CLOUDINA_FAN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PWBlocks.DEAD_CLOUDINA_FAN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PWBlocks.CLOUDINA_WALL_FAN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PWBlocks.DEAD_CLOUDINA_WALL_FAN.get(), RenderType.cutout());

        var managerDefault = (PlayerRenderer)Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("default");
        var managerSlim = (PlayerRenderer)Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().get("slim");

        if (managerDefault != null && managerSlim != null) {
            managerDefault.addLayer(new RhamphorhynchusLayerRenderer<>(managerDefault));
            managerSlim.addLayer(new RhamphorhynchusLayerRenderer<>(managerSlim));
        }
    }
}
