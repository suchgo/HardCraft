package io.github.suchgo.hardcraft.utils.classes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class DestroyBlock {
    LevelAccessor levelAccessor;
    BlockPos blockPos;
    public DestroyBlock(LevelAccessor levelAccessor, BlockPos blockPos) {
        this.levelAccessor = levelAccessor;
        this.blockPos = blockPos;
    }

    public void destroy (boolean drop) {
        levelAccessor.destroyBlock(blockPos, drop);
    }
}
