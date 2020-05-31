package com.mushroom.midnight.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;

public class GlobFungusBlock extends MidnightPlantBlock implements IGrowable {
    private final Tree tree;

    public GlobFungusBlock(Properties properties, Tree tree) {
        super(properties, true);
        this.tree = tree;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
        return world.rand.nextFloat() < 0.75f;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        if (ForgeEventFactory.saplingGrowTree(world, rand, pos)) {
            this.tree.place(world, world.getChunkProvider().getChunkGenerator(), pos, state, rand);
        }
    }
}
