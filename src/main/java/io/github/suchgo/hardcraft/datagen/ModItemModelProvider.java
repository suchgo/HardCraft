package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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

        // Tools
        simpleHandheldItem(ItemInit.PRIMITIVE_AXE);
        simpleItem(ItemInit.METAL_DETECTOR_ITEM);

        // Food
        simpleItem(ItemInit.KOHLRABI_ITEM);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(HardCraft.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleHandheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(HardCraft.MODID,"item/" + item.getId().getPath()));
    }
}
