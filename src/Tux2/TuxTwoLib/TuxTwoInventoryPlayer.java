package Tux2.TuxTwoLib;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftInventoryPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_11_R1.PlayerInventory;

public class TuxTwoInventoryPlayer extends CraftInventoryPlayer {
    public TuxTwoInventoryPlayer(final PlayerInventory inventory) {
        super(inventory);
    }

    public TuxTwoInventoryPlayer(final CraftInventoryPlayer inventory) {
        super(inventory.getInventory());
    }

    @Override
    public void setArmorContents(final ItemStack[] items) {
        super.setArmorContents(items);
        final InventoryChangeEvent eventcall = new InventoryChangeEvent((Player) this.getHolder(), 0, true, items);
        Bukkit.getServer().getPluginManager().callEvent(eventcall);
    }

    @Override
    public void setBoots(final ItemStack boots) {
        super.setBoots(boots);
        final InventoryChangeEvent eventcall = new InventoryChangeEvent((Player) this.getHolder(), 0, true, boots);
        Bukkit.getServer().getPluginManager().callEvent(eventcall);
    }

    @Override
    public void setChestplate(final ItemStack chestplate) {
        super.setChestplate(chestplate);
        final InventoryChangeEvent eventcall = new InventoryChangeEvent((Player) this.getHolder(), 2, true, chestplate);
        Bukkit.getServer().getPluginManager().callEvent(eventcall);
    }

    @Override
    public void setHelmet(final ItemStack helmet) {
        super.setHelmet(helmet);
        final InventoryChangeEvent eventcall = new InventoryChangeEvent((Player) this.getHolder(), 3, true, helmet);
        Bukkit.getServer().getPluginManager().callEvent(eventcall);
    }

    @Override
    public void setLeggings(final ItemStack leggings) {
        super.setLeggings(leggings);
        final InventoryChangeEvent eventcall = new InventoryChangeEvent((Player) this.getHolder(), 1, true, leggings);
        Bukkit.getServer().getPluginManager().callEvent(eventcall);
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(final ItemStack... items) {
        final InventoryChangeEvent eventcall = new InventoryChangeEvent((Player) this.getHolder(), items);
        Bukkit.getServer().getPluginManager().callEvent(eventcall);
        return super.addItem(items);
    }

    @Override
    public void setContents(final ItemStack[] items) {
        super.setContents(items);
        final InventoryChangeEvent eventcall = new InventoryChangeEvent((Player) this.getHolder(), 0, items);
        Bukkit.getServer().getPluginManager().callEvent(eventcall);
    }

    @Override
    public void setItem(final int index, final ItemStack item) {
        super.setItem(index, item);
        final InventoryChangeEvent eventcall = new InventoryChangeEvent((Player) this.getHolder(), index, item);
        Bukkit.getServer().getPluginManager().callEvent(eventcall);
    }
}
