package me.inotsleep.fishingspots;

import me.inotsleep.utils.AbstractConfig;
import me.inotsleep.utils.AbstractPlugin;
import org.bukkit.Bukkit;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Messages extends AbstractConfig {
    public static Map<Double, String> colors;
    public Messages(AbstractPlugin plugin) {
        super(plugin, "messages.yml", false);
    }

    @Override
    public void addDefaults() {
        header= "progress - progress bar, that will show at game in actionbar\n" +
                "    char - char settings" +
                "        background - background char (will not change color when line stressed)\n" +
                "        main - main char (will change color when line stressed))\n" +
                "        current - will show progress\n" +
                "    length - length of progress bar\n" +
                "    format - format of progress bar, where {0} is progress bar (not included in length)\n" +
                "    hookDamageColors - colors, based on line damage (double|color)\n" +
                "game - messages of game (actionbar)" +
                "    start - message when game started" +
                "    loose - messages when player looses fish" +
                "        line - message when line is broken" +
                "        loose - message when fish escaped" +
                "        noFishingRod - message when player switched item and bobber do not exists" +
                "    win - message when player catches fish" +
                "messages - regular chat messages from commands";

        addDefault("progress.char.background", "&8█");
        addDefault("progress.char.main", "█");
        addDefault("progress.char.current", "&b❮");
        addDefault("progress.length", 20);
        addDefault("progress.format", "&a☑&r {0} &4☒");
        addDefault("progress.hookDamageColors", Arrays.asList("0.0|&7", "0.25|&e", "0.5|&6", "0.75|&4"));
        addDefault("game.start", "Something on a hook...");
        addDefault("game.loose.line", "Crap! The fishing line broke.");
        addDefault("game.loose.loose", "The fish escaped :(");
        addDefault("game.loose.noFishingRod", "You put down your fishing rod and let go of the fish.");
        addDefault("game.win", "You catch the fish!");
        addDefault("messages.noPermission", "&4&lYou don't have permission to perform this command.");
    }

    @Override
    public void doReloadConfig() {
        colors = new HashMap<>();
        config.getStringList("progress.hookDamageColors").forEach(s -> {
            String[] str = s.split("\\|");
            if (str.length <=1) {
                FishingSpots.getInstance().getLogger().severe("Invalid messages.yml configuration. Colors must have format: 'damage(double)|color(any)'!");
                Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
                return;
            }
            try {
                colors.put(Double.parseDouble(str[0]), str[1]);
            } catch (NumberFormatException e) {
                FishingSpots.getInstance().getLogger().severe("Invalid messages.yml configuration. Colors must have format: 'damage(double)|color(any)'!");
                Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
            }
        });
    }

    public static String getColor(double damage) {
        AtomicReference<Double> min = new AtomicReference<>();
        AtomicReference<Double> key = new AtomicReference<>(0d);
        colors.keySet().forEach(d -> {
            min.set(min.get() == null ? Math.abs(d-damage) : Math.min(min.get(), Math.abs(d-damage)));
            key.set(min.get() == Math.abs(d-damage) ? d : key.get());
        });
        return colors.get(key.get());
    }

    @Override
    public void doSave() {

    }
}
