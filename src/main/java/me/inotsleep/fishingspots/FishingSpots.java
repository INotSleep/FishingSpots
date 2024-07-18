package me.inotsleep.fishingspots;

import me.inotsleep.fishingspots.commands.MainCommand;
import me.inotsleep.fishingspots.game.GameManager;
import me.inotsleep.fishingspots.game.RewardsConfig;
import me.inotsleep.fishingspots.spots.SpotsEffectConfig;
import me.inotsleep.fishingspots.spots.SpotsManager;
import me.inotsleep.fishingspots.utils.Listeners;
import me.inotsleep.fishingspots.utils.TaskManager;
import me.inotsleep.utils.AbstractPlugin;
import me.inotsleep.utils.particle.Animation;
import me.inotsleep.utils.particle.compiler.Compiler;
import org.bukkit.Bukkit;

import java.util.HashMap;

public final class FishingSpots extends AbstractPlugin {
    public static Config config;
    public static SpotsEffectConfig spotsEffectConfig;
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
        spotsEffectConfig = new SpotsEffectConfig(this);
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        taskManager = new TaskManager(this);
        messages = new Messages(this);
        rewardsConfig = new RewardsConfig(this);

        reload();
        new MainCommand();
    }

    public static void reload() {
        spotsManager.clear();
        gameManager.clear();
        messages.reload();
        config.reload();
        spotsEffectConfig.reload();
        taskManager.reloadValues();
        rewardsConfig.reload();

        SpotsEffectConfig.effects.forEach((k, v) -> {
            Animation animation = SpotsEffectConfig.animations.get(k);
            if (animation == null) {
                FishingSpots.printError("Effect "+k+" does not having animation!", true);
            }
            v.setAnimation(new Compiler(animation).compile());
        });
        Messages.damageColors = new HashMap<>();

        Messages.rawColors.forEach(s -> {
            String[] str = s.split("\\|");
                    if (str.length <=1) {
                        FishingSpots.getInstance().getLogger().severe("Invalid messages.yml configuration. Colors must have format: 'damage(double)|color(any)'!");
                        Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
                        return;
                    }
                    try {
                        Messages.damageColors.put(Double.parseDouble(str[0]), str[1]);
                    } catch (NumberFormatException e) {
                        FishingSpots.getInstance().getLogger().severe("Invalid messages.yml configuration. Colors must have format: 'damage(double)|color(any)'!");
                        Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
                    }
        });
    }
}
