package io.github.suchgo.hardcraft.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
}
