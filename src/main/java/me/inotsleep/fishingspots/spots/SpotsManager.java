package me.inotsleep.fishingspots.spots;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpotsManager {
    public static List<Spot> spots = new ArrayList<>();
    static Map<String, SpotEffect> effects = new HashMap<>();
    static Map<String, CustomParticle> particles = new HashMap<>();
    static List<Spot> toRemove = new ArrayList<>();
    public void draw() {
        spots.forEach(Spot::draw);
        toRemove.forEach(spot -> spots.remove(spot));
        toRemove.clear();
    }
    public static void addSpot(Spot spot) {
        spots.add(spot);
    }
    public static void removeSpot(Spot spot) {
        toRemove.add(spot);
    }
    public static Spot findSpot(Location location) {
        try {
            return SpotsManager.spots.stream().filter(spotToCheck -> spotToCheck.checkLocation(location)).findFirst().orElse(null);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void clear() {
        spots = new ArrayList<>();
    }
}
