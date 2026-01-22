package me.inotsleep.fishingspots;

import com.inotsleep.insutils.api.config.Header;
import com.inotsleep.insutils.api.config.Path;
import com.inotsleep.insutils.spi.config.Config;
import com.inotsleep.insutils.spi.config.SerializableObject;
import org.bukkit.Material;

import java.io.File;
import java.util.*;


@Header({
        "settings:\n",
        "    ticksPerPlaceSpawn - ticks between trying to spawn a fishing spot",
        "    placeSpawnChance - chance to create fishing spot (per player)",
        "    waterFindAttempts - number of attempts to find place for spawn",
        "    rarityChances - rarity and his chances. There must always be 100% rarity!!",
        "    spawnRange - range from player to spawn fishing spot"
})
public class MainConfig extends Config {
    public Map<Double, String> damageColors;

    @Path("settings")
    public Settings settings = new Settings();

    @Path("progress")
    public Progress progress = new Progress();

    public static class Settings extends SerializableObject {

        @Path("rarityChances")
        public Map<String, Double> rarities = Map.of(
                "common", 100d,
                "uncommon", 30d,
                "rare", 10d,
                "epic", 3d,
                "legendary", 0.1d
        );

        @Path("allowedBlocks")
        public List<Material> allowedBlocks = Collections.singletonList(Material.WATER);

        @Path("ticksPerPlaceSpawn")
        public int ticksPerPlaceSpawn = 20;

        @Path("placeSpawnChance")
        public double placeSpawnChance = 0.1d;

        @Path("waterFindAttempts")
        public int waterFindAttempts = 3;

        @Path("spawnRange")
        public SpawnRange spawnRange = new SpawnRange();
    }

    public static class SpawnRange extends SerializableObject {

        @Path("min")
        public double min = 20d;

        @Path("max")
        public double max = 50d;
    }

    public static class Progress extends SerializableObject {

        @Path("char")
        public ProgressChars chars = new ProgressChars();

        @Path("length")
        public int length = 20;

        @Path("format")
        public String format = "<green>☑</green> {0} <dark_red>☒</dark_red>";

        @Path("hookDamageColors")
        public List<String> rawColors = Arrays.asList("0.0|<gray>", "0.25|<yellow>", "0.5|<gold>", "0.75|<dark_red>");
    }

    public static class ProgressChars extends SerializableObject {

        @Path("background")
        public String background = "<dark_gray>█</dark_gray>";

        @Path("main")
        public String main = "█";

        @Path("current")
        public String current = "<aqua>❮</aqua>";
    }

    MainConfig(File baseDir) {
        super(baseDir, "config.yml");
    }
}
