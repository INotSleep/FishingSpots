package me.inotsleep.fishingspots.utils;

import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static double random(double min, double max) {
        return min + Math.random() * (max-min);
    }

    public static String generateRarity(Map<Double, String> rarityTable) throws Exception {
        for (Double v : rarityTable.keySet().stream().sorted((f, s) -> (int) (f-s)).collect(Collectors.toList())) {
            if (Math.random()<=v/100) return rarityTable.get(v);
        }
        throw new Exception("Rarity table don't have 100% key!");
    }
}
