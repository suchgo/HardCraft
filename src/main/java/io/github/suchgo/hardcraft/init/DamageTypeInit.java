package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.HardCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DamageTypeInit {
    public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.create(Registries.DAMAGE_TYPE, HardCraft.MODID);

    public static final ResourceKey<DamageType> BLEEDING = register("bleeding");
    public static final ResourceKey<DamageType> INJURY = register("injury");

    private static ResourceKey<DamageType> register(String name)
    {
        return ResourceKey.create(DAMAGE_TYPES.getRegistryKey(), new ResourceLocation(HardCraft.MODID, name));
    }

    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(BLEEDING, new DamageType("bleeding", 0.1F));
        context.register(INJURY, new DamageType("injury", 0.1F));
    }
}
