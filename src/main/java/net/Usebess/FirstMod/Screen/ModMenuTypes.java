package net.Usebess.FirstMod.Screen;

import net.Usebess.FirstMod.FirstMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, FirstMod.MOD_ID);

    public static final RegistryObject<MenuType<SolarPanelMenu>> SOLAR_PANEL_MENU =
            registryMenuType("solar_panel_menu", SolarPanelMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registryMenuType(String name, IContainerFactory<T> factory){

        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
