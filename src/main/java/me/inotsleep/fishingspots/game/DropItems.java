package me.inotsleep.fishingspots.game;

import me.inotsleep.fishingspots.Config;
import me.inotsleep.utils.config.Serializable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class DropItems implements Serializable {
    ItemStack itemStack;
    int min;
    int max;
    public DropItems(ItemStack itemStack, int min, int max) {
        this.itemStack = itemStack;
        this.min = min;
        this.max = max;
    }

    @Override
    public ConfigurationSection serialize() {
        ConfigurationSection section = new YamlConfiguration();
        section.set("item", itemStack);
        section.set("min", min);
        section.set("max", max);

        return section;
    }

    public static DropItems deserialize(ConfigurationSection section) {
        return new DropItems(section.getItemStack("item"), section.getInt("min"), section.getInt("max"));
    }
}
