package net.Usebess.FirstMod.item;

import net.Usebess.FirstMod.FirstMod;
import net.Usebess.FirstMod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FIRSTMODE_TAB = CREATIVE_MODE_TABS.register("firstmod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SnowballGunItem.get()))
                    .title(Component.translatable("creativetab.firstmod_tab"))
                    .displayItems(((pParameters, pOutput) ->
                    {
                        pOutput.accept(ModItems.SnowballGunItem.get());
                        pOutput.accept(ModBlocks.SOLAR_PANEL.get());
                    }))
                    .build());

    public static void Register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
