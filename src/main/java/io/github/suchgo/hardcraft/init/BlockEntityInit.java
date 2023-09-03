package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.block.entity.GemEmpoweringStationBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HardCraft.MODID);

    public static final RegistryObject<BlockEntityType<GemEmpoweringStationBlockEntity>> GEM_EMPOWERING_STATION_BE = BLOCK_ENTITIES.register("gem_empowering_station_block_entity", () -> BlockEntityType.Builder.of(GemEmpoweringStationBlockEntity::new, BlockInit.GEM_EMPOWERING_STATION_BLOCK.get()).build(null));
}
