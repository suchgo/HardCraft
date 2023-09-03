package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.recipe.GemEmpoweringRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HardCraft.MODID);

    public static final RegistryObject<RecipeSerializer<GemEmpoweringRecipe>> GEM_EMPOWERING_SERIALIZER = SERIALIZERS.register("gem_empowering", () -> GemEmpoweringRecipe.Serializer.INSTANCE);
}
