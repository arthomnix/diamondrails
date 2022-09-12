package dev.arthomnix.diamondrails;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class DiamondRailsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(DiamondRails.DIAMOND_RAIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DiamondRails.ENHANCED_DIAMOND_RAIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DiamondRails.NETHERITE_RAIL, RenderLayer.getCutout());
    }
}
