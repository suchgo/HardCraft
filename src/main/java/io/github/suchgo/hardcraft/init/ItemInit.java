package io.github.suchgo.hardcraft.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.suchgo.hardcraft.HardCraft.MODID;

public class ItemInit {
    // Create a Deferred Register to hold Items which will all be registered under the "hardcraft" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Creates a new resource item with the id "hardcraft:bush_stick_item"
    public static final RegistryObject<Item> BUSH_STICK_ITEM = ITEMS.register("bush_stick_item", () -> new Item(new Item.Properties().stacksTo(16)));
    // Creates a new BlockItem with the id "hardcraft:bush_sticks_block"
    public static final RegistryObject<BlockItem> BUSH_STICKS_BLOCK_ITEM = ITEMS.register("bush_sticks_block", () -> new BlockItem(BlockInit.BUSH_STICKS_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
}
