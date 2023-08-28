package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.HardCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ToolTierInit {
    public static final Tier SILVER = TierSortingRegistry.registerTier(
            new ForgeTier(2, 1000, 7f, 2f, 12, TagInit.Blocks.NEEDS_SILVER_TOOl, () -> Ingredient.of(Items.IRON_INGOT)),
            new ResourceLocation(HardCraft.MODID, "silver"), List.of(Tiers.DIAMOND), List.of()
    );
}
