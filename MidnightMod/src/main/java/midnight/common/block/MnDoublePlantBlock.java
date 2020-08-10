package midnight.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class MnDoublePlantBlock extends DoublePlantBlock {
    private VoxelShape hitboxLo = VoxelShapes.fullCube();
    private VoxelShape hitboxHi = VoxelShapes.fullCube();
    private OffsetType offsetType = OffsetType.NONE;

    public MnDoublePlantBlock(Properties props) {
        super(props);
    }

    public void setHitbox(VoxelShape lo, VoxelShape hi) {
        this.hitboxLo = lo;
        this.hitboxHi = hi;
    }

    public void setPlantHitbox(double size, double height) {
        double radius = size / 2;
        if(height < 16) {
            setHitbox(makeCuboidShape(8 - radius, 0, 8 - radius, 8 + radius, height, 8 + radius), VoxelShapes.empty());
        } else {
            setHitbox(
                makeCuboidShape(8 - radius, 0, 8 - radius, 8 + radius, 16, 8 + radius),
                makeCuboidShape(8 - radius, 0, 8 - radius, 8 + radius, height - 16, 8 + radius)
            );
        }
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        return state.getBlock() instanceof NightDirtBlock || super.isValidGround(state, world, pos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return state.get(HALF) == DoubleBlockHalf.LOWER ? hitboxLo : hitboxHi;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    public void setOffsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
    }
}
