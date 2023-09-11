package io.github.suchgo.hardcraft.block.custom;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public class ModCropBlock extends CropBlock {
    public ModCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return super.getAgeProperty();
    }
}
