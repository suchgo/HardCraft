package io.github.suchgo.hardcraft.compat;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.recipe.GemEmpoweringRecipe;
import io.github.suchgo.hardcraft.screen.GemEmpoweringStationScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class JEIHardcraftPlugin implements IModPlugin  {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(HardCraft.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new GemEmpoweringRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<GemEmpoweringRecipe> empoweringRecipes = recipeManager.getAllRecipesFor(GemEmpoweringRecipe.Type.INSTANCE);
        registration.addRecipes(GemEmpoweringRecipeCategory.GEM_EMPOWERING_TYPE, empoweringRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(GemEmpoweringStationScreen.class, 79, 34, 18, 18, GemEmpoweringRecipeCategory.GEM_EMPOWERING_TYPE);
    }
}
