package net.Usebess.FirstMod.Screen;

import net.Usebess.FirstMod.block.Entity.SolarPanelBlockEntity;
import net.Usebess.FirstMod.block.ModBlocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class SolarPanelMenu extends AbstractContainerMenu {

    public final SolarPanelBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 1;

    public SolarPanelMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData){

        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(1));
    }

    public SolarPanelMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData Data){

        super(ModMenuTypes.SOLAR_PANEL_MENU.get(), pContainerId);
        checkContainerSize(inv, 1);
        blockEntity = ((SolarPanelBlockEntity)entity);
        this.level = inv.player.level();
        this.data = Data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 80, 36));
        });

        addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {

        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT){

            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
            + TE_INVENTORY_SLOT_COUNT, true)) return ItemStack.EMPTY;
        }
        else
        if(pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT){

            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) return ItemStack.EMPTY;
        }
        else {
            return ItemStack.EMPTY;
        }

        sourceSlot.onTake(pPlayer, sourceStack);

        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, ModBlocks.SOLAR_PANEL.get());
    }

    private void addPlayerInventory(Inventory playerInventory){

        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }
    }

    private void addPlayerHotbar(Inventory playerInventory){
        for (int i = 0; i < 9; ++i)
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
    }
}
