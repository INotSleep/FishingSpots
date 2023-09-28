package me.inotsleep.fishingspots;

import me.inotsleep.utils.AbstractConfig;
import me.inotsleep.utils.AbstractPlugin;
import org.bukkit.Material;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Config extends AbstractConfig {
    public Map<Double, String> rarities;
    public List<Material> allowedBlocks;

    public Config(AbstractPlugin plugin) {
        super(plugin, "config.yml", false);
    }

    @Override
    public void addDefaults() {
        header= "settings:\n" +
                "    ticksPerPlaceSpawn - ticks beetwen trying to spawn a fishing spot\n" +
                "    placeSpawnChance - chance to create fishing spot (per player)\n" +
                "    waterFindAttempts - number of attempts to find place for spawn\n" +
                "    rarityChances - rarity and his chances. There must always be 100% rarity!!\n" +
                "    spawnRange - range from player to spawn fishing spot";

        addDefault("settings.ticksPerPlaceSpawn", 20);
        addDefault("settings.placeSpawnChance", 0.1d);
        addDefault("settings.waterFindAttempts", 3);

        addDefault("settings.rarityChances.common", 100d);
        addDefault("settings.rarityChances.uncommon", 30d);
        addDefault("settings.rarityChances.rare", 10d);
        addDefault("settings.rarityChances.epic", 3d);
        addDefault("settings.rarityChances.legendary", 0.1d);

        addDefault("settings.spawnRange.min", 20d);
        addDefault("settings.spawnRange.max", 50d);
        addDefault("settings.allowedBlocks", Collections.singletonList("WATER"));
    }

    @Override
    public void doReloadConfig() {
        rarities = new HashMap<>();
        allowedBlocks = config.getStringList("settings.allowedBlocks").stream().map(Material::matchMaterial).collect(Collectors.toList());

        config.getConfigurationSection("settings.rarityChances").getKeys(false).forEach(key -> rarities.put(config.getDouble("settings.rarityChances."+key), key));
    }

    @Override
    public void doSave() {

    }
}
