package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.HardCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DamageTypeInit {
    public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.createOptional(Registries.DAMAGE_TYPE, HardCraft.MODID);

    public static final RegistryObject<DamageType> BLEEDING = DAMAGE_TYPES.register("bleeding", () -> new DamageType("bleeding", 0.1f));
}
