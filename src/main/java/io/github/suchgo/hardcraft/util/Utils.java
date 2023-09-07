package io.github.suchgo.hardcraft.util;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.Random;

public class Utils {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static boolean randomChance(int chance) {
        return chance >= new Random().nextDouble(100f);
    }
}
