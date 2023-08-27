package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.BlockInit;
import io.github.suchgo.hardcraft.init.TagInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HardCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(TagInit.Blocks.ORES_SILVER).add(
                BlockInit.SILVER_ORE.get(),
                BlockInit.DEEPSLATE_SILVER_ORE.get()
        );

        this.tag(Tags.Blocks.ORES).addTag(TagInit.Blocks.ORES_SILVER);

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                BlockInit.BUSH_STICKS_BLOCK.get(),
                BlockInit.BUSH_STICKS_STAIRS.get(),
                BlockInit.BUSH_STICKS_SLAB.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockInit.SILVER_ORE.get(),
                BlockInit.DEEPSLATE_SILVER_ORE.get()
        );

        this.tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(
                BlockInit.BUSH_STICKS_BLOCK.get(),
                BlockInit.BUSH_STICKS_STAIRS.get(),
                BlockInit.BUSH_STICKS_SLAB.get()
        );

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
                BlockInit.SILVER_ORE.get(),
                BlockInit.DEEPSLATE_SILVER_ORE.get()
        );
    }

    @Override
    public @NotNull String getName() {
        return "Block Tags";
    }
}
