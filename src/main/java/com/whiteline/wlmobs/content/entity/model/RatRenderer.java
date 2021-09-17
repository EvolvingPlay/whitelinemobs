package com.whiteline.wlmobs.content.entity.model;

import com.whiteline.wlmobs.content.entity.passive.Rat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RatRenderer extends MobRenderer<Rat, RatModel<Rat>> {
    public RatRenderer(EntityRendererProvider.Context p_173943_) {
        super(p_173943_, new RatModel<>(p_173943_.bakeLayer(WLModelLayers.RAT)), 0.4F);
    }

    public ResourceLocation getTextureLocation(Rat rat) {
        return rat.getResourceLocation();
    }
}
