package io.github.suchgo.hardcraft.block;

import io.github.suchgo.hardcraft.block.custom.ModCropBlock;
import io.github.suchgo.hardcraft.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PlantainBlock extends ModCropBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(5.0D, 0.0D, 5.0D, 11.0D, 2.0D, 11.0D), Block.box(5.0D, 0.0D, 5.0D, 11.0D, 5.0D, 11.0D), Block.box(5.0D, 0.0D, 5.0D, 11.0D, 12.0D, 11.0D), Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D)};
    public PlantainBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ItemInit.PLANTAIN_LEAF_ITEM.get();
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return SHAPE_BY_AGE[this.getAge(blockState)];
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return blockState.is(BlockTags.DIRT) || blockState.is(Blocks.FARMLAND);
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
