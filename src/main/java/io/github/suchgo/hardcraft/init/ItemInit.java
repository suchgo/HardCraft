package io.github.suchgo.hardcraft.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.suchgo.hardcraft.HardCraft.MODID;
import static io.github.suchgo.hardcraft.init.CreativeTabInit.addToTab;

public class ItemInit {
    // Create a Deferred Register to hold Items which will all be registered under the "hardcraft" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Creates a new resource item with the id "hardcraft:bush_stick_item"
    public static final RegistryObject<Item> BUSH_STICK_ITEM = addToTab(ITEMS.register("bush_stick_item", () -> new Item(new Item.Properties().stacksTo(16))));

    // Creates a new BlockItem with the id "hardcraft:bush_sticks_block"
    public static final RegistryObject<BlockItem> BUSH_STICKS_BLOCK_ITEM = addToTab(ITEMS.register("bush_sticks_block", () -> new BlockItem(BlockInit.BUSH_STICKS_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON))));

    // Creates a new axe item with the id "hardcraft:primitive_axe"
    public static final RegistryObject<AxeItem> PRIMITIVE_AXE = addToTab(ITEMS.register("primitive_axe", () -> new AxeItem(Tiers.WOOD, 2, -3.4f, new Item.Properties().durability(50))));

    // Creates a new BlockItem with the id "hardcraft:silver_ore"
    public static final RegistryObject<BlockItem> SILVER_ORE_ITEM = addToTab(ITEMS.register("silver_ore", () -> new BlockItem(BlockInit.SILVER_ORE.get(), new Item.Properties())));

    // Creates a new BlockItem with the id "hardcraft:silver_ore"
    public static final RegistryObject<BlockItem> DEEPSLATE_SILVER_ORE_ITEM = addToTab(ITEMS.register("deepslate_silver_ore", () -> new BlockItem(BlockInit.DEEPSLATE_SILVER_ORE.get(), new Item.Properties())));
}
