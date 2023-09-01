package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.HardCraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleInit {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HardCraft.MODID);

    public static final RegistryObject<SimpleParticleType> BLOOD_PARTICLES = PARTICLE_TYPES.register("blood_particles", () -> new SimpleParticleType(true));
}
