package io.github.suchgo.hardcraft.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodPropertiesInit {
    public static final FoodProperties KOHLRABI = new FoodProperties.Builder().nutrition(3).saturationMod(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100), 0.1f).build();
}
