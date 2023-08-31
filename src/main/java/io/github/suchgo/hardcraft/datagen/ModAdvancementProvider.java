package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.ItemInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.@NotNull Provider registries, @NotNull Consumer<Advancement> saver, @NotNull ExistingFileHelper existingFileHelper) {
        Advancement rootAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ItemInit.BUSH_STICK_ITEM.get()),
                        Component.literal("Hardcraft"), Component.translatable("advancements.hardcraft.root.description"),
                        new ResourceLocation(HardCraft.MODID, "textures/block/silver_ore.png"), FrameType.TASK,
                        false, false, false))
                .addCriterion("has_bush_stick", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.BUSH_STICK_ITEM.get()))
                .save(saver, new ResourceLocation(HardCraft.MODID, HardCraft.MODID), existingFileHelper);

        Advancement primitiveAxe = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ItemInit.PRIMITIVE_AXE.get()),
                        Component.translatable("advancements.hardcraft.primitive_axe.title"), Component.translatable("advancements.hardcraft.primitive_axe.description", ItemInit.PRIMITIVE_AXE.get().getDescription()),
                        new ResourceLocation(HardCraft.MODID, "textures/block/silver_ore.png"), FrameType.TASK,
                        true, true, false))
                .parent(rootAdvancement)
                .addCriterion("has_primitive_axe", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.PRIMITIVE_AXE.get()))
                .save(saver, new ResourceLocation(HardCraft.MODID, "primitive_axe"), existingFileHelper);

        Advancement metalDetector = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ItemInit.METAL_DETECTOR_ITEM.get()),
                        Component.translatable("advancements.hardcraft.metal_detector.title"), Component.translatable("advancements.hardcraft.metal_detector.description", ItemInit.METAL_DETECTOR_ITEM.get().getDescription()),
                        null, FrameType.TASK,
                        true, true, false))
                .parent(primitiveAxe)
                .addCriterion("has_metal_detector", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.METAL_DETECTOR_ITEM.get()))
                .save(saver, new ResourceLocation(HardCraft.MODID, "metal_detector"), existingFileHelper);
    }
}
