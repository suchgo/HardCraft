package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.item.BandageItem;
import io.github.suchgo.hardcraft.item.FuelItem;
import io.github.suchgo.hardcraft.item.MetalDetectorItem;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.suchgo.hardcraft.HardCraft.MODID;
import static io.github.suchgo.hardcraft.init.CreativeTabInit.addToTab;

public class ItemInit {
    // Deferred Register to hold Items which will all be registered under the "hardcraft" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Items/Resources
    public static final RegistryObject<Item> BUSH_STICK_ITEM = addToTab(ITEMS.register("bush_stick", () -> new Item(new Item.Properties().stacksTo(16))));
    public static final RegistryObject<Item> GRASS_THREAD_ITEM = addToTab(ITEMS.register("grass_thread", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> PEAT_BRICK_ITEM = addToTab(ITEMS.register("peat_brick", () -> new FuelItem(new Item.Properties(), 200)));
    public static final RegistryObject<Item> RAW_SILVER_ITEM = addToTab(ITEMS.register("raw_silver", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> SILVER_INGOT_ITEM = addToTab(ITEMS.register("silver_ingot", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> BANDAGE_ITEM = addToTab(ITEMS.register("bandage", () -> new BandageItem(new Item.Properties())));
    public static final RegistryObject<Item> PLANTAIN_LEAF_ITEM = addToTab(ITEMS.register("plantain_leaf", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> PLANTAIN_SEEDS_ITEM = addToTab(ITEMS.register("plantain_seeds", () -> new ItemNameBlockItem(BlockInit.PLANTAIN_BLOCK.get(), new Item.Properties())));

    // Tools
    public static final RegistryObject<Item> PRIMITIVE_AXE = addToTab(ITEMS.register("primitive_axe", () -> new AxeItem(Tiers.WOOD, 2, -3.4f, new Item.Properties().durability(50))));
    public static final RegistryObject<Item> METAL_DETECTOR_ITEM = addToTab(ITEMS.register("metal_detector", () -> new MetalDetectorItem(new Item.Properties().durability(64))));

    // Food
    public static final RegistryObject<Item> KOHLRABI_ITEM = addToTab(ITEMS.register("kohlrabi", () -> new Item(new Item.Properties().food(FoodPropertiesInit.KOHLRABI))));

    // Blocks
    public static final RegistryObject<BlockItem> BUSH_STICKS_BLOCK_ITEM = addToTab(ITEMS.register("bush_sticks_block", () -> new BlockItem(BlockInit.BUSH_STICKS_BLOCK.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> BUSH_STICKS_STAIRS_ITEM = addToTab(ITEMS.register("bush_sticks_stairs", () -> new BlockItem(BlockInit.BUSH_STICKS_STAIRS.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> BUSH_STICKS_SLAB_ITEM = addToTab(ITEMS.register("bush_sticks_slab", () -> new BlockItem(BlockInit.BUSH_STICKS_SLAB.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> BUSH_STICKS_FENCE_ITEM = addToTab(ITEMS.register("bush_sticks_fence", () -> new BlockItem(BlockInit.BUSH_STICKS_FENCE.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> BUSH_STICKS_FENCE_GATE_ITEM = addToTab(ITEMS.register("bush_sticks_fence_gate", () -> new BlockItem(BlockInit.BUSH_STICKS_FENCE_GATE.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> BUSH_STICKS_WALL_ITEM = addToTab(ITEMS.register("bush_sticks_wall", () -> new BlockItem(BlockInit.BUSH_STICKS_WALL.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> BUSH_STICKS_DOOR_ITEM = addToTab(ITEMS.register("bush_sticks_door", () -> new BlockItem(BlockInit.BUSH_STICKS_DOOR.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> BUSH_STICKS_TRAPDOOR_ITEM = addToTab(ITEMS.register("bush_sticks_trapdoor", () -> new BlockItem(BlockInit.BUSH_STICKS_TRAPDOOR.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> BUSH_STICKS_PRESSURE_PLATE_ITEM = addToTab(ITEMS.register("bush_sticks_pressure_plate", () -> new BlockItem(BlockInit.BUSH_STICKS_PRESSURE_PLATE.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> BUSH_STICKS_BUTTON_ITEM = addToTab(ITEMS.register("bush_sticks_button", () -> new BlockItem(BlockInit.BUSH_STICKS_BUTTON.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> WILD_BUSH_BLOCK_ITEM = addToTab(ITEMS.register("wild_bush", () -> new BlockItem(BlockInit.WILD_BUSH_BLOCK.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> ROCK_BLOCK_ITEM = addToTab(ITEMS.register("rock", () -> new BlockItem(BlockInit.ROCK_BLOCK.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> SILVER_ORE_ITEM = addToTab(ITEMS.register("silver_ore", () -> new BlockItem(BlockInit.SILVER_ORE.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> DEEPSLATE_SILVER_ORE_ITEM = addToTab(ITEMS.register("deepslate_silver_ore", () -> new BlockItem(BlockInit.DEEPSLATE_SILVER_ORE.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> SOUND_BLOCK_ITEM = addToTab(ITEMS.register("sound_block", () -> new BlockItem(BlockInit.SOUND_BLOCK.get(), new Item.Properties())));
    public static final RegistryObject<BlockItem> GEM_EMPOWERING_STATION_ITEM = addToTab(ITEMS.register("gem_empowering_station", () -> new BlockItem(BlockInit.GEM_EMPOWERING_STATION_BLOCK.get(), new Item.Properties())));
}
