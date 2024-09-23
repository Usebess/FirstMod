package net.Usebess.FirstMod.block;

import net.Usebess.FirstMod.FirstMod;
import net.Usebess.FirstMod.block.Custom.SolarPanelBlock;
import net.Usebess.FirstMod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);

    public static final RegistryObject<Block> SOLAR_PANEL = RegisterBlock("solar_panel", () -> new SolarPanelBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER)));

    private static <T extends Block> RegistryObject<T> RegisterBlock (String name, Supplier <T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        RegisterBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> RegisterBlockItem(String name, RegistryObject<T> block)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void Register(IEventBus eventBus)
    {
     BLOCKS.register(eventBus);
    }
}
