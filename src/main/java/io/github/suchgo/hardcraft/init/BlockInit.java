package io.github.suchgo.hardcraft.init;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.suchgo.hardcraft.HardCraft.MODID;

public class BlockInit {
    // Create a Deferred Register to hold Blocks which will all be registered under the "hardcraft" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    // Creates a new Block with the id "hardcraft:bush_sticks_block"
    public static final RegistryObject<Block> BUSH_STICKS_BLOCK = BLOCKS.register("bush_sticks_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).mapColor(MapColor.WOOD)));

    // Creates a new Ore block with the id "hardcraft:silver_ore"
    public static final RegistryObject<DropExperienceBlock> SILVER_ORE = BLOCKS.register("silver_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE), UniformInt.of(4, 7)));

    // Creates a new Ore block with the id "hardcraft:deepslate_silver_ore"
    public static final RegistryObject<DropExperienceBlock> DEEPSLATE_SILVER_ORE = BLOCKS.register("deepslate_silver_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(SILVER_ORE.get()), UniformInt.of(4, 7)));
}
