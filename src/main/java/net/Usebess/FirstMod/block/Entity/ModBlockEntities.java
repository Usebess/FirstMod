package net.Usebess.FirstMod.block.Entity;

import net.Usebess.FirstMod.FirstMod;
import net.Usebess.FirstMod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FirstMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_BE = BLOCK_ENTITIES.register("solar_panel_be", () ->
            BlockEntityType.Builder.of(SolarPanelBlockEntity::new,
                    ModBlocks.SOLAR_PANEL.get()).build(null));

    public static void register (IEventBus eventBus){

        BLOCK_ENTITIES.register(eventBus);
    }
}
