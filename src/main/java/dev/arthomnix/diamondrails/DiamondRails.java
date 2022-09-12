package dev.arthomnix.diamondrails;

import dev.arthomnix.diamondrails.item.BlockItemWithGlint;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DiamondRails implements ModInitializer {
    public static final Block DIAMOND_RAIL = new PoweredRailBlock(FabricBlockSettings.of(Material.DECORATION)
            .sounds(BlockSoundGroup.METAL)
            .strength(0.7f)
            .noCollision()
    );

    public static final Block ENHANCED_DIAMOND_RAIL = new PoweredRailBlock(FabricBlockSettings.of(Material.DECORATION)
            .sounds(BlockSoundGroup.METAL)
            .strength(0.7f)
            .noCollision()
    );

    public static final Block NETHERITE_RAIL = new PoweredRailBlock(FabricBlockSettings.of(Material.DECORATION)
            .sounds(BlockSoundGroup.METAL)
            .strength(0.7f)
            .noCollision()
    );

    public static final TagKey<Block> TAG_POWERED_RAILS = TagKey.of(
            Registry.BLOCK_KEY, new Identifier("diamondrails", "powered_rails")
    );

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("diamondrails", "diamond_rail"), DIAMOND_RAIL);
        Registry.register(Registry.BLOCK, new Identifier("diamondrails", "enhanced_diamond_rail"), ENHANCED_DIAMOND_RAIL);
        Registry.register(Registry.BLOCK, new Identifier("diamondrails", "netherite_rail"), NETHERITE_RAIL);

        Registry.register(Registry.ITEM, new Identifier("diamondrails", "diamond_rail"),
                new BlockItem(DIAMOND_RAIL, new FabricItemSettings().group(ItemGroup.TRANSPORTATION)));
        Registry.register(Registry.ITEM, new Identifier("diamondrails", "enhanced_diamond_rail"),
                new BlockItemWithGlint(ENHANCED_DIAMOND_RAIL, new FabricItemSettings().group(ItemGroup.TRANSPORTATION)));
        Registry.register(Registry.ITEM, new Identifier("diamondrails", "netherite_rail"),
                new BlockItem(NETHERITE_RAIL, new FabricItemSettings().group(ItemGroup.TRANSPORTATION)));
    }
}
