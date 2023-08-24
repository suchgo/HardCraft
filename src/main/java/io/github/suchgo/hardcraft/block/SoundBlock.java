package io.github.suchgo.hardcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SoundBlock extends Block {
    public SoundBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pLevel.isClientSide || pHand != InteractionHand.MAIN_HAND) {
            return InteractionResult.SUCCESS;
        } else {
            pLevel.playSound(null, pPos, SoundEvents.NOTE_BLOCK_BIT.get(), SoundSource.BLOCKS, 1f, 1f);
            return InteractionResult.CONSUME;
        }
    }
}
