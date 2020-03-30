package com.mushroom.midnight.common.block;

import com.mushroom.midnight.common.registry.MidnightTileEntities;
import com.mushroom.midnight.common.tile.MidnightChestTileEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class MidnightChestBlock extends ChestBlock {

    public MidnightChestBlock(Properties properties) {
        super(properties, () -> {
            return MidnightTileEntities.MIDNIGHT_CHEST;
        });
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new MidnightChestTileEntity(this);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
