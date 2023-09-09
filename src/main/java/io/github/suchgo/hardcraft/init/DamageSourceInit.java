package io.github.suchgo.hardcraft.init;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

public class DamageSourceInit {
    public static DamageSource bleeding(Level level) {
        return getDamageSource(DamageTypeInit.BLEEDING, level.registryAccess());
    }

    public static DamageSource injury(Level level) {
        return getDamageSource(DamageTypeInit.INJURY, level.registryAccess());
    }

    private static DamageSource getDamageSource(ResourceKey<DamageType> damageType, RegistryAccess access) {
        return new DamageSource(access.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(damageType));
    }
}
