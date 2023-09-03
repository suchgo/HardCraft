package io.github.suchgo.hardcraft.utils;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import org.slf4j.Logger;

import java.util.Random;
import java.util.stream.Stream;

public class Utils {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static boolean randomChance(int chance) {
        return chance >= new Random().nextDouble(100f);
    }
}
