/*
 * Copyright (c) 2020 Cryptic Mushroom and contributors
 * This file belongs to the Midnight mod and is licensed under the terms and conditions of Cryptic Mushroom. See
 * https://github.com/Cryptic-Mushroom/The-Midnight/blob/rewrite/LICENSE.md for the full license.
 *
 * Last updated: 2020 - 10 - 18
 */

package midnight.data.loottables;

import midnight.common.block.MnBlockStateProperties;
import midnight.common.block.MnBlocks;
import midnight.common.item.MnItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class MnBlockLootTables extends BlockLootTables {
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
    private static final float[] DEFAULT_SAPLING_DROP_RATES = {1 / 20F, 1 / 16F, 1 / 12F, 1 / 10F};
    private static final float[] DEFAULT_POWDER_DROP_RATES = {1 / 5F, 1 / 3F, 2 / 3F, 1};

    @Override
    protected void addTables() {
        registerDropSelfLootTable(MnBlocks.NIGHTSTONE);
        registerDropSelfLootTable(MnBlocks.TRENCHSTONE);
        registerLootTable(MnBlocks.NIGHT_BEDROCK, block -> droppingNothing());

        registerDropSelfLootTable(MnBlocks.NIGHT_DIRT);
        registerLootTable(MnBlocks.NIGHT_GRASS_BLOCK, block -> droppingWithSilkTouch(block, MnBlocks.NIGHT_DIRT));
        registerDropSelfLootTable(MnBlocks.DECEITFUL_PEAT);
        registerDropSelfLootTable(MnBlocks.DECEITFUL_MUD);
        registerDropSelfLootTable(MnBlocks.STRANGE_SAND);
        registerDropSelfLootTable(MnBlocks.COARSE_NIGHT_DIRT);

        registerDropSelfLootTable(MnBlocks.GHOST_PLANT_LEAF);
        registerDropSelfLootTable(MnBlocks.GHOST_PLANT_STEM);
        registerDropSelfLootTable(MnBlocks.GHOST_PLANT);

        registerLootTable(MnBlocks.NIGHT_GRASS, onlyWithShears(MnBlocks.NIGHT_GRASS));
        registerLootTable(MnBlocks.TALL_NIGHT_GRASS, onlyWithShears(MnBlocks.TALL_NIGHT_GRASS));

        registerLootTable(MnBlocks.DARK_WATER, block -> droppingNothing());

        registerDropSelfLootTable(MnBlocks.DEAD_WOOD);
        registerDropSelfLootTable(MnBlocks.DEAD_WOOD_LOG);
        registerDropSelfLootTable(MnBlocks.DEAD_WOOD_PLANKS);
        registerDropSelfLootTable(MnBlocks.STRIPPED_DEAD_WOOD);
        registerDropSelfLootTable(MnBlocks.STRIPPED_DEAD_WOOD_LOG);
        registerDropSelfLootTable(MnBlocks.DEAD_SAPLING);

        registerDropSelfLootTable(MnBlocks.SHADOWROOT_WOOD);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_LOG);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_PLANKS);
        registerDropSelfLootTable(MnBlocks.SHADOWROOT_SAPLING);
        registerLootTable(MnBlocks.SHADOWROOT_LEAVES, block -> droppingWithChancesAndDarkSticks(block, MnBlocks.SHADOWROOT_SAPLING, DEFAULT_SAPLING_DROP_RATES));
        registerDropSelfLootTable(MnBlocks.STRIPPED_SHADOWROOT_WOOD);
        registerDropSelfLootTable(MnBlocks.STRIPPED_SHADOWROOT_LOG);

        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_WOOD);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_LOG);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_PLANKS);
        registerDropSelfLootTable(MnBlocks.DARK_WILLOW_SAPLING);
        registerLootTable(MnBlocks.DARK_WILLOW_LEAVES, block -> droppingWithChancesAndDarkSticks(block, MnBlocks.DARK_WILLOW_SAPLING, DEFAULT_SAPLING_DROP_RATES));
        registerLootTable(MnBlocks.HANGING_DARK_WILLOW_LEAVES, onlyWithShears(MnBlocks.HANGING_DARK_WILLOW_LEAVES));
        registerDropSelfLootTable(MnBlocks.STRIPPED_DARK_WILLOW_WOOD);
        registerDropSelfLootTable(MnBlocks.STRIPPED_DARK_WILLOW_LOG);

        registerDropSelfLootTable(MnBlocks.NIGHTSHROOM_STEM);
        registerDropSelfLootTable(MnBlocks.NIGHTSHROOM_PLANKS);
        registerDropSelfLootTable(MnBlocks.NIGHTSHROOM);
        registerDropSelfLootTable(MnBlocks.NIGHTSHROOM_SHELF);
        registerLootTable(MnBlocks.NIGHTSHROOM_FIBRE, MnBlockLootTables::droppingFibre);
        registerLootTable(MnBlocks.NIGHTSHROOM_ROOTS, BlockLootTables::onlyWithShears);
        registerLootTable(MnBlocks.FLOWERING_NIGHTSHROOM_ROOTS, BlockLootTables::onlyWithShears);
        registerLootTable(MnBlocks.TALL_NIGHTSHROOM, block -> droppingWhen(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        registerLootTable(MnBlocks.NIGHTSHROOM_CAP, block -> droppingShroomOrPowder(block, MnItems.NIGHTSHROOM_POWDER, MnBlocks.NIGHTSHROOM, MnBlocks.TALL_NIGHTSHROOM, DEFAULT_POWDER_DROP_RATES));

        registerDropSelfLootTable(MnBlocks.DEWSHROOM_STEM);
        registerDropSelfLootTable(MnBlocks.DEWSHROOM_PLANKS);
        registerDropSelfLootTable(MnBlocks.DEWSHROOM);
        registerDropSelfLootTable(MnBlocks.DEWSHROOM_SHELF);
        registerLootTable(MnBlocks.DEWSHROOM_FIBRE, MnBlockLootTables::droppingFibre);
        registerLootTable(MnBlocks.DEWSHROOM_ROOTS, BlockLootTables::onlyWithShears);
        registerLootTable(MnBlocks.FLOWERING_DEWSHROOM_ROOTS, BlockLootTables::onlyWithShears);
        registerLootTable(MnBlocks.TALL_DEWSHROOM, block -> droppingWhen(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        registerLootTable(MnBlocks.DEWSHROOM_CAP, block -> droppingShroomOrPowder(block, MnItems.DEWSHROOM_POWDER, MnBlocks.DEWSHROOM, MnBlocks.TALL_DEWSHROOM, DEFAULT_POWDER_DROP_RATES));

        registerDropSelfLootTable(MnBlocks.VIRIDSHROOM_STEM);
        registerDropSelfLootTable(MnBlocks.VIRIDSHROOM_PLANKS);
        registerDropSelfLootTable(MnBlocks.VIRIDSHROOM);
        registerDropSelfLootTable(MnBlocks.VIRIDSHROOM_SHELF);
        registerLootTable(MnBlocks.VIRIDSHROOM_FIBRE, MnBlockLootTables::droppingFibre);
        registerLootTable(MnBlocks.VIRIDSHROOM_ROOTS, BlockLootTables::onlyWithShears);
        registerLootTable(MnBlocks.FLOWERING_VIRIDSHROOM_ROOTS, BlockLootTables::onlyWithShears);
        registerLootTable(MnBlocks.TALL_VIRIDSHROOM, block -> droppingWhen(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        registerLootTable(MnBlocks.VIRIDSHROOM_CAP, block -> droppingShroomOrPowder(block, MnItems.VIRIDSHROOM_POWDER, MnBlocks.VIRIDSHROOM, MnBlocks.TALL_VIRIDSHROOM, DEFAULT_POWDER_DROP_RATES));

        registerDropSelfLootTable(MnBlocks.BOGSHROOM_STEM);
        registerDropSelfLootTable(MnBlocks.BOGSHROOM_PLANKS);
        registerDropSelfLootTable(MnBlocks.BOGSHROOM);
        registerDropSelfLootTable(MnBlocks.BOGSHROOM_SHELF);
        registerLootTable(MnBlocks.BOGSHROOM_FIBRE, MnBlockLootTables::droppingFibre);
        registerLootTable(MnBlocks.TALL_BOGSHROOM, block -> droppingWhen(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        registerLootTable(MnBlocks.BOGSHROOM_CAP, block -> droppingShroomOrPowder(block, MnItems.BOGSHROOM_POWDER, MnBlocks.BOGSHROOM, MnBlocks.TALL_BOGSHROOM, DEFAULT_POWDER_DROP_RATES));

        registerDropSelfLootTable(MnBlocks.GLOB_FUNGUS);
        registerDropSelfLootTable(MnBlocks.GLOB_FUNGUS_CAP);
        registerDropSelfLootTable(MnBlocks.GLOB_FUNGUS_STEM);
        registerDropSelfLootTable(MnBlocks.GLOB_FUNGUS_HYPHAE);
        registerDropSelfLootTable(MnBlocks.GLOB_FUNGUS_THATCH);

        registerLootTable(MnBlocks.DARK_PEARL_ORE, block -> droppingItemWithFortune(block, MnItems.GEODE));
        registerDropSelfLootTable(MnBlocks.DARK_PEARL_BLOCK);
    }

    protected static LootTable.Builder droppingNothing() {
        return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(0)));
    }

    protected static LootTable.Builder droppingWithChancesAndDarkSticks(Block leaves, Block sapling, float... saplingBonus) {
        return droppingWithSilkTouchOrShears(
            leaves,
            withSurvivesExplosion(leaves, ItemLootEntry.builder(sapling))
                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, saplingBonus))
        ).addLootPool(
            LootPool.builder()
                    .rolls(ConstantRange.of(1))
                    .acceptCondition(NOT_SILK_TOUCH_OR_SHEARS)
                    .addEntry(
                        withExplosionDecay(
                            leaves,
                            ItemLootEntry.builder(MnItems.DARK_STICK)
                                         .acceptFunction(SetCount.builder(RandomValueRange.of(1, 2)))
                        ).acceptCondition(
                            TableBonus.builder(Enchantments.FORTUNE, 1 / 50F, 1 / 45F, 1 / 40F, 1 / 30F, 1 / 10F)
                        )
                    )
        );
    }

    protected static LootTable.Builder droppingShroomOrPowder(Block block, IItemProvider item, IItemProvider shroom, IItemProvider tall_shroom, float... itemBonus) {
        return droppingWithSilkTouch(
            block,
            withSurvivesExplosion(
                block,
                AlternativesLootEntry.builder(ItemLootEntry.builder(item).acceptCondition(RandomChance.builder(1 / 2F)))
                                     .alternatively(ItemLootEntry.builder(shroom).acceptCondition(RandomChance.builder(2 / 3F)))
                                     .alternatively(ItemLootEntry.builder(tall_shroom).acceptCondition(RandomChance.builder(1 / 2F)))
            )
        );
    }

    protected static LootTable.Builder droppingFibre(Block block) {
        return LootTable.builder().addLootPool(
            LootPool.builder().addEntry(
                ItemLootEntry.builder(block)
                             .acceptFunction(
                                 SetCount.builder(ConstantRange.of(2))
                                         .acceptCondition(BlockStateProperty.builder(block).properties(
                                             StatePropertiesPredicate.Builder.create().exactMatch(
                                                 MnBlockStateProperties.DENSE,
                                                 true
                                             )
                                         ))
                             )
                             .acceptCondition(
                                 SILK_TOUCH_OR_SHEARS
                             )
            )
        );
    }


    @Override
    @SuppressWarnings("deprecation")
    protected Iterable<Block> getKnownBlocks() {
        return Registry.BLOCK
                   .stream()
                   .filter(block -> {
                       ResourceLocation loc = block.getRegistryName();
                       assert loc != null;
                       return loc.getNamespace().equals("midnight");
                   })
                   .distinct()
                   ::iterator;
    }
}
