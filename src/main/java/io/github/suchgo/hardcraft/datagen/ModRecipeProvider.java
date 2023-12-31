package io.github.suchgo.hardcraft.datagen;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.datagen.custom.GemEmpoweringRecipeBuilder;
import io.github.suchgo.hardcraft.init.BlockInit;
import io.github.suchgo.hardcraft.init.ItemInit;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SILVER_SMELTABLES = List.of(
            BlockInit.SILVER_ORE.get(),
            BlockInit.DEEPSLATE_SILVER_ORE.get(),
            ItemInit.RAW_SILVER_ITEM.get()
    );

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> pWriter) {
        // Simple blocks/items
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ItemInit.BUSH_STICK_ITEM.get(), RecipeCategory.MISC, BlockInit.BUSH_STICKS_BLOCK.get(),
                HardCraft.MODID + ":bush_stick", "bush_stick",HardCraft.MODID + ":bush_sticks_block", "bush_sticks_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.GRASS_FABRIC_ITEM.get(), 1)
                .requires(ItemInit.GRASS_THREAD_ITEM.get(), 4)
                .unlockedBy(getHasName(ItemInit.GRASS_THREAD_ITEM.get()), has(ItemInit.GRASS_THREAD_ITEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.GRASS_BANDAGE_ITEM.get(), 1)
                .requires(ItemInit.GRASS_FABRIC_ITEM.get(), 3)
                .requires(ItemInit.PLANTAIN_LEAF_ITEM.get())
                .unlockedBy("has_grass_fabric_or_has_plantain_leaf", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ItemInit.GRASS_FABRIC_ITEM.get(), ItemInit.PLANTAIN_LEAF_ITEM.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.TENT_HERBAL_BED_BLOCK.get())
                .pattern("FF ")
                .pattern("FF ")
                .pattern("FF ")
                .define('F', ItemInit.GRASS_FABRIC_ITEM.get())
                .unlockedBy(getHasName(ItemInit.GRASS_FABRIC_ITEM.get()), inventoryTrigger(ItemPredicate.Builder.item().
                        of(ItemInit.GRASS_FABRIC_ITEM.get()).build()))
                .save(pWriter);

        // Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemInit.PRIMITIVE_AXE.get())
                .pattern("RG")
                .pattern("B ")
                .define('R', ItemInit.ROCK_BLOCK_ITEM.get())
                .define('G', ItemInit.GRASS_THREAD_ITEM.get())
                .define('B', ItemInit.BUSH_STICK_ITEM.get())
                .unlockedBy("has_bush_stick_or_has_rock_or_has_grass_thread", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ItemInit.BUSH_STICK_ITEM.get(), ItemInit.ROCK_BLOCK_ITEM.get(), ItemInit.GRASS_THREAD_ITEM.get()).build()))
                .save(pWriter);

        // Stairs
        stairBuilder(ItemInit.BUSH_STICKS_STAIRS_ITEM.get(), Ingredient.of(ItemInit.BUSH_STICKS_BLOCK_ITEM.get())).unlockedBy("has_bush_sticks_block", has(ItemInit.BUSH_STICKS_BLOCK_ITEM.get())).save(pWriter);

        // Slabs
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ItemInit.BUSH_STICKS_SLAB_ITEM.get(), Ingredient.of(ItemInit.BUSH_STICKS_BLOCK_ITEM.get())).unlockedBy("has_bush_sticks_block", has(ItemInit.BUSH_STICKS_BLOCK_ITEM.get())).save(pWriter);

        // Smelting/Blasting
        oreSmelting(pWriter, SILVER_SMELTABLES, RecipeCategory.MISC, ItemInit.SILVER_INGOT_ITEM.get(), 0.25f, 200, "silver");
        oreBlasting(pWriter, SILVER_SMELTABLES, RecipeCategory.MISC, ItemInit.SILVER_INGOT_ITEM.get(), 0.25f, 100, "silver");

        // Cooking

        // Empowering
        new GemEmpoweringRecipeBuilder(ItemInit.RAW_SILVER_ITEM.get(), ItemInit.SILVER_INGOT_ITEM.get(), 3)
                .unlockedBy(getHasName(ItemInit.RAW_SILVER_ITEM.get()), has(ItemInit.RAW_SILVER_ITEM.get())).save(pWriter);

        // Override vanilla
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.CRAFTING_TABLE, 1)
                .pattern("P#")
                .pattern("##")
                .define('P', ItemInit.PRIMITIVE_AXE.get())
                .define('#', ItemTags.PLANKS)
                .unlockedBy("unlock_right_away", PlayerTrigger.TriggerInstance.tick())
                .showNotification(false)
                .save(pWriter);
    }

    protected static void oreSmelting(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTIme, @NotNull String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, @NotNull RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, HardCraft.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
