/*
 * Copyright (c) 2020 Cryptic Mushroom and contributors
 * This file belongs to the Midnight mod and is licensed under the terms and conditions of Cryptic Mushroom. See
 * https://github.com/Cryptic-Mushroom/The-Midnight/blob/rewrite/LICENSE.md for the full license.
 *
 * Last updated: 2020 - 12 - 23
 */

package midnight.common.world.levelgen.midnight;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import midnight.common.Midnight;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;

public class MnChunkGenerator extends ChunkGenerator {
    public static final Codec<MnChunkGenerator> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Codec.LONG.fieldOf("seed")
                      .stable()
                      .forGetter(gen -> gen.seed),
            BiomeProvider.CODEC.fieldOf("biome_source")
                               .forGetter(gen -> gen.biomeProvider)
        ).apply(instance, instance.stable(MnChunkGenerator::new))
    );

    private final long seed;
    private final MidnightTerrainGenerator terrainGen;
    private final MidnightSurfaceGenerator surfaceGen;
    private final MidnightBedrockGenerator bedrockGen;

    public MnChunkGenerator(long seed, BiomeProvider biomes) {
        super(biomes, new DimensionStructuresSettings(false));
        this.seed = seed;

        terrainGen = new MidnightTerrainGenerator(seed, biomes, this, 72);
        surfaceGen = new MidnightSurfaceGenerator(seed, biomes, this);
        bedrockGen = new MidnightBedrockGenerator(seed, biomes, this);
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new MnChunkGenerator(seed, biomeProvider);
    }

    @Override
    public void buildSurface(WorldGenRegion world, IChunk chunk) {
        surfaceGen.generateSurface(world, chunk);
        bedrockGen.generateBedrock(chunk);
    }

    @Override
    public int getGroundHeight() {
        return 72;
    }

    @Override
    public void populateNoise(IWorld world, StructureManager structureManager, IChunk chunk) {
        terrainGen.generateTerrain(world, chunk);
    }

    public int getHeight(int x, int z, Heightmap.Type type) {
        return 100; // TODO We may no longer have world
    }

    @Override
    public int getSeaLevel() {
        return 72;
    }

    @Override // Once again nobody seems to care about the name of this function...
    public int func_222529_a(int x, int z, Heightmap.Type type) {
        return getHeight(x, z, type);
    }

    @Override
    public IBlockReader getColumnSample(int x, int z) {
        return null; // TODO Fine to be null but for datapack support this is mandatory some day (otherwise NPEs occur)
    }

    static {
        Registry.register(Registry.CHUNK_GENERATOR, Midnight.resLoc("ptg_midnight"), CODEC);
    }
}
