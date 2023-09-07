package io.github.suchgo.hardcraft.util;

import java.util.Random;

public class BasicUtil {
    public static boolean randomChance(int chance) {
        return chance >= new Random().nextDouble(100f);
    }
}
