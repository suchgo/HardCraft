package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.HardCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class TagInit {
    public static class Items {
        public static final TagKey<Item> ORES_SILVER = forgeTag("ores/silver");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(HardCraft.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");
        public static final TagKey<Block> ORES_SILVER = forgeTag("ores/silver");
        public static final TagKey<Block> NEEDS_SILVER_TOOl = tag("needs_silver_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(HardCraft.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class DamageTypes {
        public static final TagKey<DamageType> IS_BLEEDING = forgeTag("is_bleeding");
        public static final TagKey<DamageType> IS_INJURY = tag("is_injury");

        private static TagKey<DamageType> tag(String name) {
            return create(new ResourceLocation(HardCraft.MODID, name));
        }

        private static TagKey<DamageType> forgeTag(String name) {
            return create(new ResourceLocation("forge", name));
        }

        private static TagKey<DamageType> create(ResourceLocation name) {
            return TagKey.create(Registries.DAMAGE_TYPE, name);
        }
    }
}
