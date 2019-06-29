package com.mushroom.midnight.common.biome.surface;

import com.mushroom.midnight.common.biome.MidnightBiomeConfigurator;
import com.mushroom.midnight.common.biome.MidnightSurfaceBuilders;
import com.mushroom.midnight.common.registry.MidnightEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class WarpedFieldsBiome extends SurfaceBiome {
    public WarpedFieldsBiome() {
        super(new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, MidnightSurfaceBuilders.GRASS_DIRT_MUD_CONFIG)
                .category(Category.FOREST)
                .depth(0.1F)
                .scale(0.8F)
                .densityScale(0.5F)
                .ridgeWeight(0.0F)
                .wet()
        );

        MidnightBiomeConfigurator.addVegetatedFeatures(this);

        // .withFeature(SHADOWROOT_TREE_FEATURE, new SurfacePlacementConfig(-3, 1))

        MidnightBiomeConfigurator.addGlobalFeatures(this);

        MidnightBiomeConfigurator.addStandardCreatureSpawns(this);
        MidnightBiomeConfigurator.addStandardMonsterSpawns(this);

        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(MidnightEntities.HUNTER, 5, 0, 2));
    }
}
