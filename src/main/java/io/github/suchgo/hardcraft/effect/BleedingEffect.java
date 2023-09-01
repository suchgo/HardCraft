package io.github.suchgo.hardcraft.effect;

import io.github.suchgo.hardcraft.init.ParticleInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class BleedingEffect extends MobEffect {
    public BleedingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.hurt(pLivingEntity.damageSources().cactus(), 1f);

        if (!pLivingEntity.level().isClientSide()) {
            spawnParticles(pLivingEntity);
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    private void spawnParticles(LivingEntity livingEntity) {
        ServerLevel level = (ServerLevel) livingEntity.level();
        for(int i = 0; i < 20; i++) {
            level.sendParticles(ParticleInit.BLOOD_PARTICLES.get(), livingEntity.getX() + 0.5d, livingEntity.getY() + 1, livingEntity.getZ() + 0.5d, 1, Math.cos(i * 18) * 0.15d, 0.15d, Math.sin(i * 18) * 0.15d, 0.1);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        int j = 50 >> pAmplifier;
        if (j > 0) {
            return pDuration % j == 0;
        } else {
            return true;
        }
    }
}
