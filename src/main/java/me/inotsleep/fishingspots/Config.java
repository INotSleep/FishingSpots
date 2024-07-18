package me.inotsleep.fishingspots;

import me.inotsleep.utils.config.AbstractConfig;
import me.inotsleep.utils.AbstractPlugin;
import me.inotsleep.utils.config.Path;
import org.bukkit.Material;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config extends AbstractConfig {
    @Path(path = "settings.rarityChances")
    public Map<String, Double> rarities;

    @Path(path = "settings.allowedBlocks")
    public List<String> allowedBlocks = Collections.singletonList("WATER");

    @Path(path="settings.ticksPerPlaceSpawn")
    public static int ticksPerPlaceSpawn = 20;
    @Path(path="settings.placeSpawnChance")
    public static double placeSpawnChance = 0.1d;
    @Path(path="settings.waterFindAttempts")
    public static int waterFindAttempts = 3;
    @Path(path="settings.spawnRange.min")
    public static double spawnRangeMin = 20d;
    @Path(path="settings.spawnRange.max")
    public static double spawnRangeMax = 50d;

    public Config(AbstractPlugin plugin) {
        super(plugin, "config.yml");
    }

    @Override
    public String getHeader() {
        return "settings:\n" +
                "    ticksPerPlaceSpawn - ticks beetwen trying to spawn a fishing spot\n" +
                "    placeSpawnChance - chance to create fishing spot (per player)\n" +
                "    waterFindAttempts - number of attempts to find place for spawn\n" +
                "    rarityChances - rarity and his chances. There must always be 100% rarity!!\n" +
                "    spawnRange - range from player to spawn fishing spot";
    }

    @Override
    public void saveDefaults() {
        rarities = new HashMap<>();

        rarities.put("common", 100d);
        rarities.put("uncommon", 30d);
        rarities.put("rare", 10d);
        rarities.put("epic", 3d);
        rarities.put("legendary", 0.1d);
    }
}
