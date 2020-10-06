package midnight.core.biome;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

// TODO Since this is in Midnight Core, we might want to document this.
public class MnBiomeBuilder extends Biome.Builder {
    private final ResourceLocation id;
    private BiomeColoring coloring;
    private TerrainFactors terrainFactors;

    public MnBiomeBuilder(ResourceLocation id) {
        this.id = id;
    }

    public MnBiomeBuilder coloring(BiomeColoring coloring) {
        this.coloring = coloring;
        return this;
    }

    public MnBiomeBuilder terrainFactors(TerrainFactors terrainFactors) {
        this.terrainFactors = terrainFactors;
        return this;
    }

    @Override
    public MnBiomeBuilder precipitation(Biome.RainType type) {
        return (MnBiomeBuilder) super.precipitation(type);
    }

    @Override
    public MnBiomeBuilder category(Biome.Category category) {
        return (MnBiomeBuilder) super.category(category);
    }

    @Override
    public MnBiomeBuilder depth(float depth) {
        return (MnBiomeBuilder) super.depth(depth);
    }

    @Override
    public MnBiomeBuilder scale(float scale) {
        return (MnBiomeBuilder) super.scale(scale);
    }

    @Override
    public MnBiomeBuilder temperature(float temperature) {
        return (MnBiomeBuilder) super.temperature(temperature);
    }

    @Override
    public MnBiomeBuilder downfall(float downfall) {
        return (MnBiomeBuilder) super.downfall(downfall);
    }

    @Override
    public MnBiomeBuilder effects(BiomeAmbience ambience) {
        return (MnBiomeBuilder) super.effects(ambience);
    }

    @Override
    public MnBiomeBuilder spawnSettings(MobSpawnInfo spawns) {
        return (MnBiomeBuilder) super.spawnSettings(spawns);
    }

    @Override
    public MnBiomeBuilder generationSettings(BiomeGenerationSettings generation) {
        return (MnBiomeBuilder) super.generationSettings(generation);
    }

    @Override
    public MnBiomeBuilder temperatureModifier(Biome.TemperatureModifier temperature) {
        return (MnBiomeBuilder) super.temperatureModifier(temperature);
    }

    @Override
    public Biome build() {
        Biome biome = super.build();
        biome.setRegistryName(id);
        if(coloring != null)
            BiomeConfigLookup.putColoring(id, coloring);
        if(terrainFactors != null)
            BiomeConfigLookup.putTerrainFactors(id, terrainFactors);
        return biome;
    }
}
