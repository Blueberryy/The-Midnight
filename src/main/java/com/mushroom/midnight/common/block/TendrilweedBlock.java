package com.mushroom.midnight.common.block;

import com.mushroom.midnight.common.registry.MidnightBlocks;
import com.mushroom.midnight.common.registry.MidnightEffects;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Random;

@SuppressWarnings("deprecation")
public class TendrilweedBlock extends MidnightPlantBlock {
    // TODO particle  tendril spore
    public TendrilweedBlock(Properties properties) {
        super(properties.tickRandomly(), true);
    }

    @Override
    public void randomTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (!world.isRemote && random.nextFloat() < 0.01f) {
            BlockPos placePos = pos.add(random.nextInt(5) - 2, random.nextInt(3) - 1, random.nextInt(5) - 2);
            if (isValidPosition(world.getBlockState(placePos), world, placePos)) {
                int flowers = 0;
                Iterator<BlockPos> it = BlockPos.getAllInBox(pos.add(-2, -1, -2), pos.add(2, 1, 2)).iterator();
                while (it.hasNext()) {
                    BlockPos currentPos = it.next();
                    if (world.getBlockState(currentPos).getBlock() == MidnightBlocks.TENDRILWEED) {
                        flowers++;
                    }
                }
                if (flowers < 6) {
                    world.setBlockState(placePos, MidnightBlocks.TENDRILWEED.getDefaultState(), 3);
                }
            }
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && !entity.world.isRemote && entity.ticksExisted % 20 == 0) {
            ((LivingEntity) entity).addPotionEffect(new EffectInstance(MidnightEffects.POLLINATED, 200, 1, false, true));
        }
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == MidnightBlocks.NIGHTSTONE;
    }
}
