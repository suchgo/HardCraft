package io.github.suchgo.hardcraft.datagen.loot;

import io.github.suchgo.hardcraft.init.BlockInit;
import io.github.suchgo.hardcraft.init.ItemInit;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // Blocks
        this.dropSelf(BlockInit.SOUND_BLOCK.get());
        this.add(BlockInit.BUSH_STICKS_BLOCK.get(), createSingleItemTable(ItemInit.BUSH_STICK_ITEM.get(), ConstantValue.exactly(9f)));

        // Plants
        this.dropSelf(BlockInit.BUSH_BLOCK.get());

        // Ores
        this.add(BlockInit.SILVER_ORE.get(),
                block -> createOreDrop(BlockInit.SILVER_ORE.get(), Items.RAW_IRON));
        this.add(BlockInit.DEEPSLATE_SILVER_ORE.get(),
                block -> createOreDrop(BlockInit.DEEPSLATE_SILVER_ORE.get(), Items.RAW_IRON));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
