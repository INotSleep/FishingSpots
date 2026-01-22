package me.inotsleep.fishingspots;

import com.inotsleep.insutils.api.logging.LoggingManager;
import com.inotsleep.insutils.api.particle.Animation;
import com.inotsleep.insutils.api.particle.compiler.Compiler;
import com.inotsleep.insutils.spi.plugin.BukkitPlugin;
import me.inotsleep.fishingspots.commands.MainCommand;
import me.inotsleep.fishingspots.game.GameManager;
import me.inotsleep.fishingspots.game.RewardsConfig;
import me.inotsleep.fishingspots.spots.SpotsEffectConfig;
import me.inotsleep.fishingspots.spots.SpotsManager;
import me.inotsleep.fishingspots.utils.Listeners;
import me.inotsleep.fishingspots.utils.TaskManager;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public final class FishingSpots extends BukkitPlugin {
    private static FishingSpots instance;
    public static MainConfig config;
    public static SpotsEffectConfig spotsEffectConfig;
    public static SpotsManager spotsManager;
    public static GameManager gameManager;
    public static TaskManager taskManager;
    public static RewardsConfig rewardsConfig;

    public static @NotNull FishingSpots getInstance() {
        return instance;
    }

    @Override
    public void doDisable() {

    }

    @Override
    public void doLoad() {
        instance = this;
    }

    @Override
    public void doEnable() {
        spotsManager = new SpotsManager();
        gameManager = new GameManager();
        config = new MainConfig(getDataFolder());
        spotsEffectConfig = new SpotsEffectConfig(getDataFolder());
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        taskManager = new TaskManager(this);
        rewardsConfig = new RewardsConfig(getDataFolder());

        reload();
        new MainCommand();
    }

    public static void reload() {
        spotsManager.clear();
        gameManager.clear();
        config.reload();
        spotsEffectConfig.reload();
        taskManager.reloadValues();
        rewardsConfig.reload();

        SpotsEffectConfig.effects.forEach((k, v) -> {
            Animation animation = SpotsEffectConfig.animations.get(k);
            if (animation == null) {
                LoggingManager.error("Effect "+k+" does not having animation!");
                Bukkit.getPluginManager().disablePlugin(instance);
            }
            v.setAnimation(new Compiler(animation).compile());
        });
        config.damageColors = new HashMap<>();

        config.progress.rawColors.forEach(s -> {
            String[] str = s.split("\\|");
                    if (str.length <=1) {
                        LoggingManager.error("Invalid messages.yml configuration. Colors must have format: 'damage(double)|color(any)'!");
                        Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
                        return;
                    }
                    try {
                        config.damageColors.put(Double.parseDouble(str[0]), str[1]);
                    } catch (NumberFormatException e) {
                        LoggingManager.error("Invalid messages.yml configuration. Colors must have format: 'damage(double)|color(any)'!");
                        Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
                    }
        });
    }
}
