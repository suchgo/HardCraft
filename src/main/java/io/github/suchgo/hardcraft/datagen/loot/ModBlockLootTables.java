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

        // Stairs
        this.dropSelf(BlockInit.BUSH_STICKS_STAIRS.get());

        // Slabs
        this.add(BlockInit.BUSH_STICKS_SLAB.get(), createSlabItemTable(BlockInit.BUSH_STICKS_SLAB.get()));

        // Fences
        this.dropSelf(BlockInit.BUSH_STICKS_FENCE.get());

        // Fence Gates
        this.dropSelf(BlockInit.BUSH_STICKS_FENCE_GATE.get());

        // Walls
        this.dropSelf(BlockInit.BUSH_STICKS_WALL.get());

        // Doors
        this.add(BlockInit.BUSH_STICKS_DOOR.get(), block -> createDoorTable(BlockInit.BUSH_STICKS_DOOR.get()));

        // Trapdoors
        this.dropSelf(BlockInit.BUSH_STICKS_TRAPDOOR.get());

        // Pressure Plates
        this.dropSelf(BlockInit.BUSH_STICKS_PRESSURE_PLATE.get());

        // Buttons
        this.dropSelf(BlockInit.BUSH_STICKS_BUTTON.get());

        // Plants
        this.dropSelf(BlockInit.BUSH_BLOCK.get());

        // Ores
        this.add(BlockInit.SILVER_ORE.get(), createOreDrop(BlockInit.SILVER_ORE.get(), ItemInit.RAW_SILVER_ITEM.get()));
        this.add(BlockInit.DEEPSLATE_SILVER_ORE.get(), createOreDrop(BlockInit.DEEPSLATE_SILVER_ORE.get(), ItemInit.RAW_SILVER_ITEM.get()));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
