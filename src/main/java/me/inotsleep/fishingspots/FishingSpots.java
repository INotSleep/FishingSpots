package me.inotsleep.fishingspots;

import me.inotsleep.fishingspots.commands.MainCommand;
import me.inotsleep.fishingspots.game.GameManager;
import me.inotsleep.fishingspots.game.RewardsConfig;
import me.inotsleep.fishingspots.spots.ParticlesConfig;
import me.inotsleep.fishingspots.spots.SpotsEffectConfig;
import me.inotsleep.fishingspots.spots.SpotsManager;
import me.inotsleep.fishingspots.utils.Listeners;
import me.inotsleep.fishingspots.utils.TaskManager;
import me.inotsleep.utils.AbstractPlugin;
import org.bukkit.Bukkit;

public final class FishingSpots extends AbstractPlugin {
    public static Config config;
    public static SpotsEffectConfig spotsEffectConfig;
    public static ParticlesConfig particlesConfig;
    public static SpotsManager spotsManager;
    public static GameManager gameManager;
    public static TaskManager taskManager;
    public static Messages messages;
    public static RewardsConfig rewardsConfig;

    @Override
    public void doDisable() {

    }

    @Override
    public void doEnable() {
        spotsManager = new SpotsManager();
        gameManager = new GameManager();
        config = new Config(this);
        particlesConfig = new ParticlesConfig(this);
        spotsEffectConfig = new SpotsEffectConfig(this);
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        taskManager = new TaskManager(this);
        messages = new Messages(this);
        rewardsConfig = new RewardsConfig(this);
        new MainCommand();
    }

    public static void reload() {
        spotsManager.clear();
        gameManager.clear();
        messages.reloadConfig();
        config.reloadConfig();
        particlesConfig.reloadConfig();
        spotsEffectConfig.reloadConfig();
        taskManager.reloadValues();
        rewardsConfig.reloadConfig();
    }
}
