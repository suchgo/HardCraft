package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.BlockInit;
import io.github.suchgo.hardcraft.init.DamageTypeInit;
import io.github.suchgo.hardcraft.init.TagInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagsProvider extends DamageTypeTagsProvider {
    public ModDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HardCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        // Bleeding
        this.tag(TagInit.DamageTypes.IS_BLEEDING).addOptional(
                DamageTypeInit.BLEEDING.location()
        );

        // Injury
        this.tag(TagInit.DamageTypes.IS_INJURY).addOptional(
                DamageTypeInit.INJURY.location()
        );
    }

    @Override
    public @NotNull String getName() {
        return "Damage Type Tags";
    }
}
