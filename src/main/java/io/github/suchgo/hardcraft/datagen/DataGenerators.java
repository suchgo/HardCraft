package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = HardCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));
        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(packOutput));

        ModBlockTagsProvider blockTags = generator.addProvider(event.includeServer(), new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeClient(), new ForgeAdvancementProvider(packOutput, lookupProvider, existingFileHelper, List.of(new ModAdvancementProvider())));
    }
}
