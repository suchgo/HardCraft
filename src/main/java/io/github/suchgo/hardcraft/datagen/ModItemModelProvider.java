package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.BlockInit;
import io.github.suchgo.hardcraft.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HardCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Items/Resources
        simpleItem(ItemInit.BUSH_STICK_ITEM);
        simpleItem(ItemInit.GRASS_THREAD_ITEM);
        simpleItem(ItemInit.PEAT_BRICK_ITEM);
        simpleItem(ItemInit.RAW_SILVER_ITEM);
        simpleItem(ItemInit.SILVER_INGOT_ITEM);
        simpleItem(ItemInit.BANDAGE_ITEM);
        simpleItem(ItemInit.PLANTAIN_LEAF_ITEM);

        // Tools
        simpleHandheldItem(ItemInit.PRIMITIVE_AXE);
        simpleItem(ItemInit.METAL_DETECTOR_ITEM);

        // Food
        simpleItem(ItemInit.KOHLRABI_ITEM);

        // Buttons
        buttonItem(BlockInit.BUSH_STICKS_BUTTON, BlockInit.BUSH_STICKS_BLOCK);

        // Fences
        fenceItem(BlockInit.BUSH_STICKS_FENCE, BlockInit.BUSH_STICKS_BLOCK);

        // Walls
        wallItem(BlockInit.BUSH_STICKS_WALL, BlockInit.BUSH_STICKS_BLOCK);

        // Doors
        simpleBlockItem(BlockInit.BUSH_STICKS_DOOR);

        // Plants
        simpleBlockItemBasedOnBlockSprite(ItemInit.WILD_BUSH_BLOCK_ITEM);

        // Blocks
        simpleBlockItem(BlockInit.ROCK_BLOCK);
    }

    private void simpleItem(RegistryObject<Item> item) {
        this.item("item/generated", "layer0", "item", item.getId().getPath());
    }

    private void simpleHandheldItem(RegistryObject<Item> item) {
        this.item("item/handheld", "layer0", "item", item.getId().getPath());
    }

    private void simpleBlockItem(RegistryObject<Block> item) {
        this.item("item/generated", "layer0", "item", item.getId().getPath());
    }

    private void simpleBlockItemBasedOnBlockSprite(RegistryObject<BlockItem> item) {
        this.item("item/generated", "layer0", "block", item.getId().getPath());
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.blockItem("texture", "block/button_inventory", block, baseBlock);
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.blockItem("texture", "block/fence_inventory", block, baseBlock);
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.blockItem("wall", "block/wall_inventory", block, baseBlock);
    }

    private void item(String resourceLocation, String key, String type, String path) {
        this.withExistingParent(path,
                new ResourceLocation(resourceLocation)).texture(key,
                new ResourceLocation(HardCraft.MODID,type + "/" + path));
    }

    private void blockItem(String key, String mcLocation, RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc(mcLocation))
                .texture(key,  new ResourceLocation(HardCraft.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
}
