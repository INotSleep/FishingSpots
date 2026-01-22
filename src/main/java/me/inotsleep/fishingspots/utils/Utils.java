package me.inotsleep.fishingspots.utils;

import com.inotsleep.insutils.api.particle.Animation;
import com.inotsleep.insutils.api.particle.Frame;
import com.inotsleep.insutils.api.particle.Parameter;
import me.inotsleep.fishingspots.FishingSpots;
import org.bukkit.Material;
import org.bukkit.Particle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Utils {
    public static double random(double min, double max) {
        return min + Math.random() * (max-min);
    }

    public static String generateRarity(Map<Double, String> rarityTable, boolean a) throws IllegalArgumentException {
        for (Double v : rarityTable.keySet().stream().sorted((f, s) -> (int) (f-s)).collect(Collectors.toList())) {
            if (Math.random()<=v/100) return rarityTable.get(v);
        }
        throw new IllegalArgumentException("Rarity table don't have 100% key!");
    }

    public static String generateRarity(Map<String, Double> rarityTable) {
        Map<Double, String> rarityTableCopy = new HashMap<>();
        rarityTable.forEach((k, v) -> rarityTableCopy.put(v, k));
        return generateRarity(rarityTableCopy, true);
    }

    public static String parsePlaceholders(String str, String ...strings) {
        for (int i = 0; i < strings.length; i++) {
            str = str.replace("{"+i+"}", strings[i]);
        }
        return str;
    }

    public static String getColor(double damage) {
        AtomicReference<Double> min = new AtomicReference<>();
        AtomicReference<Double> key = new AtomicReference<>(0d);
        FishingSpots.config.damageColors.keySet().forEach(d -> {
            min.set(min.get() == null ? Math.abs(d-damage) : Math.min(min.get(), Math.abs(d-damage)));
            key.set(min.get() == Math.abs(d-damage) ? d : key.get());
        });
        return FishingSpots.config.damageColors.get(key.get());
    }

    public static Animation createDefaultAnimation() {
        Animation animation = new Animation(new HashMap<String, Frame>());
        animation.frames.put("0", new Frame(new HashMap<String, Parameter>(), 60, "0.0, 0.0, 1, 1"));
        animation.frames.put("1", new Frame(new HashMap<String, Parameter>(), 0, "1.0, 0.0, 1, 1"));
        animation.frames.get("0").parameters.put("0", new Parameter(10.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Parameter.Type.circle, 0.0, 0.0, 0.0, Particle.DUST, Material.DIAMOND, 255, 255, 255, 1.0));
        animation.frames.get("0").parameters.put("1", new Parameter(10.0, -2.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Parameter.Type.point, 0.0, 0.0, 0.0, Particle.DUST, Material.DIAMOND, 255, 255, 255, 0.0));
        animation.frames.get("0").parameters.put("2", new Parameter(10.0, 2.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Parameter.Type.point, 0.0, 0.0, 0.0, Particle.DUST, Material.DIAMOND, 255, 255, 255, 0.0));
        animation.frames.get("0").parameters.put("3", new Parameter(10.0, 0.0, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Parameter.Type.circle, 0.0, 0.0, 0.0, Particle.DUST, Material.DIAMOND, 100, 100, 100, 0.5));
        animation.frames.get("1").parameters.put("0", new Parameter(10.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Parameter.Type.circle, 0.0, 0.0, 0.0, Particle.DUST, Material.DIAMOND, 255, 255, 255, 2.0));
        animation.frames.get("1").parameters.put("1", new Parameter(10.0, -2.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Parameter.Type.point, 0.0, 360.0, 0.0, Particle.DUST, Material.DIAMOND, 255, 255, 255, 0.0));
        animation.frames.get("1").parameters.put("2", new Parameter(10.0, 2.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Parameter.Type.point, 0.0, 360.0, 0.0, Particle.DUST, Material.DIAMOND, 255, 255, 255, 0.0));
        return animation;
    }

}
