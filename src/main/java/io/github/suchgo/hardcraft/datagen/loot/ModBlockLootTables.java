package io.github.suchgo.hardcraft.datagen.loot;

import io.github.suchgo.hardcraft.block.custom.ModCropBlock;
import io.github.suchgo.hardcraft.init.BlockInit;
import io.github.suchgo.hardcraft.init.ItemInit;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
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
        this.dropSelf(BlockInit.ROCK_BLOCK.get());
        this.dropSelf(BlockInit.GEM_EMPOWERING_STATION_BLOCK.get());
        this.add(BlockInit.TENT_HERBAL_BED_BLOCK.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));

        // Stairs
        this.dropSelf(BlockInit.BUSH_STICKS_STAIRS.get());

        // Slabs
        this.add(BlockInit.BUSH_STICKS_SLAB.get(), this::createSlabItemTable);

        // Fences
        this.dropSelf(BlockInit.BUSH_STICKS_FENCE.get());

        // Fence Gates
        this.dropSelf(BlockInit.BUSH_STICKS_FENCE_GATE.get());

        // Walls
        this.dropSelf(BlockInit.BUSH_STICKS_WALL.get());

        // Doors
        this.add(BlockInit.BUSH_STICKS_DOOR.get(), this::createDoorTable);

        // Trapdoors
        this.dropSelf(BlockInit.BUSH_STICKS_TRAPDOOR.get());

        // Pressure Plates
        this.dropSelf(BlockInit.BUSH_STICKS_PRESSURE_PLATE.get());

        // Buttons
        this.dropSelf(BlockInit.BUSH_STICKS_BUTTON.get());

        // Plants
        this.add(BlockInit.WILD_BUSH_BLOCK.get(), createSingleItemTable(ItemInit.BUSH_STICK_ITEM.get(), UniformGenerator.between(1f, 3f)));

        // Crops
        this.add(BlockInit.PLANTAIN_BLOCK.get(), block -> this.createCropDrops(block, ItemInit.PLANTAIN_LEAF_ITEM.get(), ItemInit.PLANTAIN_SEEDS_ITEM.get(), cropBuilder(block)));

        // Ores
        this.add(BlockInit.SILVER_ORE.get(), block -> createOreDrop(block, ItemInit.RAW_SILVER_ITEM.get()));
        this.add(BlockInit.DEEPSLATE_SILVER_ORE.get(), block -> createOreDrop(block, ItemInit.RAW_SILVER_ITEM.get()));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    private LootItemCondition.Builder cropBuilder(Block block) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(((ModCropBlock) block).getAgeProperty(), ((ModCropBlock) block).getMaxAge()));
    }
}
