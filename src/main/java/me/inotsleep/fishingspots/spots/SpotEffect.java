package me.inotsleep.fishingspots.spots;

import me.inotsleep.fishingspots.utils.Pair;
import me.inotsleep.utils.config.Serializable;
import me.inotsleep.utils.particle.compiled.CompiledAnimation;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class SpotEffect implements Serializable {
    CompiledAnimation animation;
    double radius;
    Pair lineDamage;
    Pair lineRestore;
    Pair catchingProgress;

    public Pair getLineDamage() {
        return lineDamage;
    }

    public Pair getLineRestore() {
        return lineRestore;
    }

    public Pair getCatchingProgress() {
        return catchingProgress;
    }

    public Pair getCatchingRegress() {
        return catchingRegress;
    }

    Pair catchingRegress;

    public SpotEffect(double radius, Pair lineRestore, Pair lineDamage, Pair catchingProgress, Pair catchingRegress) {
        this.catchingProgress = catchingProgress;
        this.catchingRegress = catchingRegress;
        this.lineDamage = lineDamage;
        this.lineRestore = lineRestore;
        this.radius = radius;
    }

    public void setAnimation(CompiledAnimation animation) {
        this.animation = animation;
    }

    public void draw(int tick, Location location) {
        animation.drawFrame(location.getWorld(), location.getX(), location.getY(), location.getZ(), tick%animation.frames.size());
    }

    @Override
    public ConfigurationSection serialize() {
        ConfigurationSection section = new YamlConfiguration();

        section.set("catchingProgress", catchingProgress.serialize());
        section.set("catchingRegress", catchingRegress.serialize());
        section.set("lineDamage", lineDamage.serialize());
        section.set("lineRestore", lineRestore.serialize());
        section.set("radius", radius);


        return section;
    }

    public static SpotEffect deserialize(ConfigurationSection section) {
        return new SpotEffect(section.getDouble("radius"), Pair.deserialize(section.getConfigurationSection("lineRestore")), Pair.deserialize(section.getConfigurationSection("lineDamage")), Pair.deserialize(section.getConfigurationSection("catchingProgress")), Pair.deserialize(section.getConfigurationSection("catchingRegress")));
    }
}
