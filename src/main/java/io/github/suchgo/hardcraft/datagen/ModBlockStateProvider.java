package io.github.suchgo.hardcraft.datagen;

import com.google.gson.JsonElement;
import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.block.custom.ModCropBlock;
import io.github.suchgo.hardcraft.init.BlockInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
        tentBlock(BlockInit.TENT_HERBAL_BED_BLOCK);

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
        plantTintBlock(BlockInit.WILD_BUSH_BLOCK);

        // Crops
        cropBlock(BlockInit.PLANTAIN_BLOCK, true);

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

    private void plantBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), models().cross(block.getId().getPath(), blockTexture(block.get())).renderType("cutout"));
    }

    private void plantTintBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), models().singleTexture(block.getId().getPath(), mcLoc("block/tinted_cross"), "cross", blockTexture(block.get())).renderType("cutout"));
    }

    public void cropBlock(RegistryObject<Block> block, boolean cross) {
        CropBlock cropBlock = (ModCropBlock)block.get();
        String name = block.getId().getPath() + "_stage";
        Function<BlockState, ConfiguredModel[]> function = state -> cropStates(state, cropBlock, name, name, cross);

        getVariantBuilder(cropBlock).forAllStates(function);
    }

    private ConfiguredModel[] cropStates(BlockState state, CropBlock block, String modelName, String textureName, boolean cross) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        int age = state.getValue(((ModCropBlock) block).getAgeProperty());
        if (cross) {
            models[0] = new ConfiguredModel(models().cross(modelName + age, new ResourceLocation(HardCraft.MODID, "block/" + textureName + age)).renderType("cutout"));
        }
        else {
            models[0] = new ConfiguredModel(models().crop(modelName + age, new ResourceLocation(HardCraft.MODID, "block/" + textureName + age)).renderType("cutout"));
        }

        return models;
    }

    public void tentBlock(RegistryObject<Block> block) {
        String name = block.getId().getPath() + "_";
        Function<BlockState, ModelFile> function = state -> new ModelFile.UncheckedModelFile(modLoc("block/" + name + state.getValue(BedBlock.PART)));

        getVariantBuilder(block.get()).forAllStatesExcept(state -> ConfiguredModel.builder()
                .modelFile(function.apply(state))
                .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                .build(), BedBlock.OCCUPIED
        );
    }
}
