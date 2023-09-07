package io.github.suchgo.hardcraft.compat;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.BlockInit;
import io.github.suchgo.hardcraft.recipe.GemEmpoweringRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GemEmpoweringRecipeCategory implements IRecipeCategory<GemEmpoweringRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(HardCraft.MODID, "gem_empowering");
    public static final ResourceLocation TEXTURE = new ResourceLocation(HardCraft.MODID, "textures/gui/gem_empowering_station_gui.png");

    public static final RecipeType<GemEmpoweringRecipe> GEM_EMPOWERING_TYPE = new RecipeType<>(UID, GemEmpoweringRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawableAnimated animatedArrow;

    public GemEmpoweringRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 75);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.GEM_EMPOWERING_STATION_BLOCK.get()));
        this.animatedArrow = helper.createAnimatedDrawable(helper.createDrawable(TEXTURE, 176, 0, 8, 14), 100, IDrawableAnimated.StartDirection.TOP, false);
    }

    @Override
    public @NotNull RecipeType<GemEmpoweringRecipe> getRecipeType() {
        return GEM_EMPOWERING_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return BlockInit.GEM_EMPOWERING_STATION_BLOCK.get().getName();
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GemEmpoweringRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 53).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(@NotNull GemEmpoweringRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        animatedArrow.draw(guiGraphics, 85, 36);
    }
}
