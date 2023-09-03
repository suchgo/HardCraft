package io.github.suchgo.hardcraft.init;

import io.github.suchgo.hardcraft.HardCraft;
import io.github.suchgo.hardcraft.screen.GemEmpoweringStationMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypeInit {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, HardCraft.MODID);

    public static final RegistryObject<MenuType<GemEmpoweringStationMenu>> GEM_EMPOWERING_MENU = registerMenuType(GemEmpoweringStationMenu::new, "gem_empowering_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
}
