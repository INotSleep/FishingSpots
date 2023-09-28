package me.inotsleep.fishingspots.utils;

import me.inotsleep.fishingspots.Config;
import me.inotsleep.fishingspots.FishingSpots;
import me.inotsleep.fishingspots.game.GameManager;
import me.inotsleep.fishingspots.spots.Spot;
import me.inotsleep.fishingspots.spots.SpotsManager;
import me.inotsleep.utils.AbstractPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskManager {

    private final AbstractPlugin plugin;
    Config config;
    int ticksPerPlaceSpawn;
    double min;
    double max;
    int maxAttempts;
    public BukkitRunnable spawnTask;
    public BukkitRunnable tickTask;

    public TaskManager(AbstractPlugin plugin) {
        this.plugin = plugin;
        reloadValues();
    }

    public void reloadValues() {
        if (spawnTask != null) spawnTask.cancel();
        if (tickTask != null) tickTask.cancel();
        config = FishingSpots.config;
        ticksPerPlaceSpawn = config.config.getInt("settings.ticksPerPlaceSpawn");
        min = config.config.getDouble("settings.spawnRange.min");
        max = config.config.getDouble("settings.spawnRange.max");
        maxAttempts = config.config.getInt("settings.waterFindAttempts");
        spawnTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    if (config.config.getDouble("settings.placeSpawnChance")/100 > Math.random()) {
                        int attempts = 0;

                        while(attempts < maxAttempts) {
                            attempts ++;

                            double x = Utils.random(min*-1, max);
                            double z = Utils.random(min*-1, max);

                            Location location = player.getLocation().add(x, 0, z);
                            Block block = player.getWorld().getHighestBlockAt(location);
                            if (block.getType() != Material.WATER) continue;

                            location.setY(block.getY()+1.1d);
                            SpotsManager.addSpot(new Spot(location));

                            return;
                        }
                    }
                });
            }
        };

        spawnTask.runTaskTimer(plugin, 0, ticksPerPlaceSpawn);
        tickTask = new BukkitRunnable() {
            @Override
            public void run() {
                FishingSpots.spotsManager.draw();
                GameManager.tick();
            }
        };
        tickTask.runTaskTimer(plugin, 0, 1);

    }
}
