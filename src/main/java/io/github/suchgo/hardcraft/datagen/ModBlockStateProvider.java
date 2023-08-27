package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.BlockInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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

        // Stairs
        stairsBlock((StairBlock) BlockInit.BUSH_STICKS_STAIRS.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));
        blockItem(BlockInit.BUSH_STICKS_STAIRS);

        // Slabs
        slabBlock((SlabBlock) BlockInit.BUSH_STICKS_SLAB.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));
        blockItem(BlockInit.BUSH_STICKS_SLAB);

        // Pressure Plates
        pressurePlateBlock((PressurePlateBlock) BlockInit.BUSH_STICKS_PRESSURE_PLATE.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));
        blockItem(BlockInit.BUSH_STICKS_PRESSURE_PLATE);

        // Buttons
        buttonBlock((ButtonBlock) BlockInit.BUSH_STICKS_BUTTON.get(), blockTexture(BlockInit.BUSH_STICKS_BLOCK.get()));

        // Plants
        simpleBlockWithItem(BlockInit.BUSH_BLOCK.get(), models().cross(BlockInit.BUSH_BLOCK.getId().getPath(), blockTexture(BlockInit.BUSH_BLOCK.get())));

        // Ores
        blockWithItem(BlockInit.SILVER_ORE);
        blockWithItem(BlockInit.DEEPSLATE_SILVER_ORE);
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(HardCraft.MODID + ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
