package com.mushroom.midnight.common.biome;

import com.mushroom.midnight.common.registry.ModBlocks;

public class BiomeBlackRidge extends BiomeBase {

    public BiomeBlackRidge(BiomeProperties properties) {
        super(properties);

        this.topBlock = ModBlocks.NIGHTSTONE.getDefaultState();
        this.fillerBlock = ModBlocks.NIGHTSTONE.getDefaultState();
    }
}