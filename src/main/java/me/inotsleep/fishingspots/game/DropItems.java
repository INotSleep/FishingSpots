package me.inotsleep.fishingspots.game;

import org.bukkit.inventory.ItemStack;

public class DropItems {
    ItemStack itemStack;
    int min;
    int max;
    public DropItems(ItemStack itemStack, int min, int max) {
        this.itemStack = itemStack;
        this.min = min;
        this.max = max;
    }
}
