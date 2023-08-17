package io.github.suchgo.hardcraft.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static io.github.suchgo.hardcraft.HardCraft.MODID;

public class CreativeTabInit {
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "hardcraft" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Create list of items for "hardcraft:main_tab"
    public static final List<Supplier<? extends ItemLike>> MAIN_TAB_ITEMS = new ArrayList<>();

    // Creates a creative tab with the id "hardcraft:main_tab", that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("main_tab", () -> CreativeModeTab.builder()
            .title(Component.literal("HardCraft"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemInit.BUSH_STICK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                MAIN_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()));
            }).build());

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        MAIN_TAB_ITEMS.add(itemLike);
        return itemLike;
    }
}
