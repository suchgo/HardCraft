package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.ItemInit;
import io.github.suchgo.hardcraft.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, HardCraft.MODID);
    }

    @Override
    protected void start() {
        add("grass_thread_from_grass", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                ExplosionCondition.survivesExplosion().build(),
                LootItemRandomChanceCondition.randomChance(0.4f).build()
        }, ItemInit.GRASS_THREAD_ITEM.get()));

        add("grass_thread_from_tall_grass", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                ExplosionCondition.survivesExplosion().build(),
                LootItemRandomChanceCondition.randomChance(0.4f).build()
        }, ItemInit.GRASS_THREAD_ITEM.get()));

        add("metal_detector_from_zombie", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombie")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()
        }, ItemInit.METAL_DETECTOR_ITEM.get()));
    }
}
