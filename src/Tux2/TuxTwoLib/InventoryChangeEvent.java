package Tux2.TuxTwoLib;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class InventoryChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    int slot = -1;
    boolean armor = false;
    ItemStack[] items = null;
    Player player;

    public InventoryChangeEvent(final Player player, final ItemStack... items) {
        this.items = items;
        this.player = player;
    }

    public InventoryChangeEvent(final Player player, final int slot, final ItemStack... items) {
        this.items = items;
        this.slot = slot;
        this.player = player;
    }

    public InventoryChangeEvent(final Player player, final int slot, final boolean armor, final ItemStack... items) {
        this.items = items;
        this.slot = slot;
        this.armor = armor;
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return InventoryChangeEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return InventoryChangeEvent.handlers;
    }

    public int getSlot() {
        return this.slot;
    }

    public boolean isArmor() {
        return this.armor;
    }

    public ItemStack[] getItems() {
        return this.items;
    }

    public Player getPlayer() {
        return this.player;
    }
}
