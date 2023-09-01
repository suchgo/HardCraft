package io.github.suchgo.hardcraft.event;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.init.ParticleInit;
import io.github.suchgo.hardcraft.particle.BloodParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HardCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventsClient {
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleInit.BLOOD_PARTICLES.get(), BloodParticles.Provider::new);
    }
}
