package io.github.suchgo.hardcraft.block;

import io.github.suchgo.hardcraft.HardCraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TentHerbalBedBlock extends BedBlock {
    protected static final VoxelShape BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public TentHerbalBedBlock(Properties properties) {
        super(DyeColor.GREEN, properties);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return BASE;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if (level.isClientSide()) {
            return InteractionResult.CONSUME;
        }

        if (!isMultiBlock(blockState, level, pos)) {
            return InteractionResult.CONSUME;
        }

        return super.use(blockState, level, pos, player, interactionHand, blockHitResult);
    }

    private boolean isMultiBlock(@NotNull BlockState blockState, Level level, BlockPos pos) {
        Direction facingDirection = blockState.getValue(FACING);
        Direction connectedDirection = getConnectedDirection(blockState);
        Stream<Direction> horizontalDirections = Stream.of(facingDirection.getClockWise(), facingDirection.getCounterClockWise());
        Stream<BlockPos> verticalBlocks = Stream.of(pos, pos.relative(connectedDirection));

        return horizontalDirections.allMatch(direction -> {
            Stream<BlockState> states = Stream.of(level.getBlockState(pos.relative(direction)), level.getBlockState(pos.relative(connectedDirection).relative(direction)));

            return states.allMatch(state -> state.is(BlockTags.STAIRS) && level.getBlockState(pos.relative(direction).relative(state.getValue(FACING))).is(this));
        }) && verticalBlocks.allMatch(pos1 -> level.getBlockState(pos1.above()).is(BlockTags.SLABS));
    }
}
