package me.inotsleep.fishingspots.utils;

import me.inotsleep.utils.config.Serializable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class Pair implements Serializable {
    double k;
    double v;

    public static Pair deserialize(ConfigurationSection section) {
        return new Pair(section.getDouble("min"), section.getDouble("max"));
    }

    public double getK() {
        return k;
    }

    public double getV() {
        return v;
    }

    public void setK(double k) {
        this.k = k;
    }

    public void setV(double v) {
        this.v = v;
    }

    public Pair(double k, double v) {
        this.k = k;
        this.v = v;
    }

    public double generate() {
        return Utils.random(k, v);
    }


    @Override
    public ConfigurationSection serialize() {
        ConfigurationSection section = new YamlConfiguration();
        section.set("min", k);
        section.set("max", v);
        return section;
    }
}
