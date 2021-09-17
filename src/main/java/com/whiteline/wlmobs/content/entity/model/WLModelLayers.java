package com.whiteline.wlmobs.content.entity.model;

import com.whiteline.wlmobs.WLMobs;
import com.whiteline.wlmobs.init.Registry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WLModelLayers {
    public static ModelLayerLocation RAT = new ModelLayerLocation(new ResourceLocation(WLMobs.MOD_ID + "rat"), "rat");

    @SubscribeEvent
    public static void layerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WLModelLayers.RAT, RatModel::createBodyMesh);
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(Registry.RAT.get(), RatRenderer::new);
    }
}
