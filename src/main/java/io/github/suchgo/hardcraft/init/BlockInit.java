package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.block.SoundBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.suchgo.hardcraft.HardCraft.MODID;

public class BlockInit {
    // Deferred Register to hold Blocks which will all be registered under the "hardcraft" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    // Blocks
    public static final RegistryObject<Block> BUSH_STICKS_BLOCK = BLOCKS.register("bush_sticks_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).mapColor(MapColor.WOOD)));
    public static final RegistryObject<Block> SOUND_BLOCK = BLOCKS.register("sound_block", () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.NOTE_BLOCK)));

    // Stairs
    public static final RegistryObject<Block> BUSH_STICKS_STAIRS = BLOCKS.register("bush_sticks_stairs", () -> new StairBlock(() -> BUSH_STICKS_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));

    // Slabs
    public static final RegistryObject<Block> BUSH_STICKS_SLAB = BLOCKS.register("bush_sticks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));

    // Pressure Plates
    public static final RegistryObject<Block> BUSH_STICKS_PRESSURE_PLATE = BLOCKS.register("bush_sticks_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));

    // Buttons
    public static final RegistryObject<Block> BUSH_STICKS_BUTTON = BLOCKS.register("bush_sticks_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 30, true));

    // Plants
    public static final RegistryObject<Block> BUSH_BLOCK = BLOCKS.register("bush", () -> new BushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));

    // Ores
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE), UniformInt.of(1, 3)));
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = BLOCKS.register("deepslate_silver_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(SILVER_ORE.get()), UniformInt.of(1, 3)));
}
