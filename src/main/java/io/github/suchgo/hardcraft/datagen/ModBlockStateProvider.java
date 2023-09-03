package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.BlockInit;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, HardCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Blocks
        blockWithItem(BlockInit.BUSH_STICKS_BLOCK);
        blockWithItem(BlockInit.SOUND_BLOCK);
        simpleBlock(BlockInit.ROCK_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/rock")));
        horizontalBlock(BlockInit.GEM_EMPOWERING_STATION_BLOCK.get(), modLoc("block/gem_empowering_station_side"), modLoc("block/gem_empowering_station_front"), modLoc("block/gem_empowering_station_top"));
        blockItem(BlockInit.GEM_EMPOWERING_STATION_BLOCK);

        // Stairs
        stairsBlock((StairBlock) BlockInit.BUSH_STICKS_STAIRS.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));
        blockItem(BlockInit.BUSH_STICKS_STAIRS);

        // Slabs
        slabBlock((SlabBlock) BlockInit.BUSH_STICKS_SLAB.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));
        blockItem(BlockInit.BUSH_STICKS_SLAB);

        // Fences
        fenceBlock((FenceBlock) BlockInit.BUSH_STICKS_FENCE.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));

        // Fence Gates
        fenceGateBlock((FenceGateBlock) BlockInit.BUSH_STICKS_FENCE_GATE.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));
        blockItem(BlockInit.BUSH_STICKS_FENCE_GATE);

        // Walls
        wallBlock((WallBlock) BlockInit.BUSH_STICKS_WALL.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));

        // Doors
        doorBlockWithRenderType((DoorBlock) BlockInit.BUSH_STICKS_DOOR.get(), modLoc("block/bush_sticks_door_bottom"), modLoc("block/bush_sticks_door_top"), "cutout");

        // Trapdoors
        trapdoorBlockWithRenderType((TrapDoorBlock) BlockInit.BUSH_STICKS_TRAPDOOR.get(), modLoc("block/bush_sticks_trapdoor"), true, "cutout");
        blockItem(BlockInit.BUSH_STICKS_TRAPDOOR, "_bottom");

        // Pressure Plates
        pressurePlateBlock((PressurePlateBlock) BlockInit.BUSH_STICKS_PRESSURE_PLATE.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));
        blockItem(BlockInit.BUSH_STICKS_PRESSURE_PLATE);

        // Buttons
        buttonBlock((ButtonBlock) BlockInit.BUSH_STICKS_BUTTON.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));

        // Plants
        simpleBlock(BlockInit.WILD_BUSH_BLOCK.get(), models().singleTexture(BlockInit.WILD_BUSH_BLOCK.getId().getPath(), mcLoc("block/tinted_cross"), "cross", blockTexture(BlockInit.WILD_BUSH_BLOCK.get())).renderType("cutout"));

        // Ores
        blockWithItem(BlockInit.SILVER_ORE);
        blockWithItem(BlockInit.DEEPSLATE_SILVER_ORE);
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(HardCraft.MODID + ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(HardCraft.MODID + ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
