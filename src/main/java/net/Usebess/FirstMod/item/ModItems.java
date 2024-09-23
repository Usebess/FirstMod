package net.Usebess.FirstMod.item;

import net.Usebess.FirstMod.FirstMod;
import net.Usebess.FirstMod.item.Custom.SnowballGunItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    public static final RegistryObject<Item> SnowballGunItem = ITEMS.register("snowball_gun",
            () -> new SnowballGunItem(new Item.Properties()
                    .durability(100)));

    public static void Register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
