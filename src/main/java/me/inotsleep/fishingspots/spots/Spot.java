package me.inotsleep.fishingspots.spots;

import me.inotsleep.fishingspots.FishingSpots;
import me.inotsleep.fishingspots.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Spot {
    Location location;
    SpotEffect effect;
    String rarity;
    boolean locked = false;
    int tick = 0;

    public Spot(String rarity, Location location) throws IllegalArgumentException {
        effect = SpotsManager.effects.get(rarity);
        this.rarity = rarity;
        this.location = location;
    }

    public Spot(Location location) {
        String rarity = null;
        try {
            rarity = Utils.generateRarity(FishingSpots.config.rarities);
        } catch (Exception e) {
            FishingSpots.getInstance().getLogger().severe("Invalid config.yml configuration. Rarities do not have 100% rarity!");
            Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
        }
        if (rarity == null) return;
        effect = SpotsManager.effects.get(rarity);
        if (effect == null) {
            FishingSpots.getInstance().getLogger().severe("Rarity " + rarity + "does not have effect! You must create it in spotsEffects.yml");
            Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
        }
        this.rarity = rarity;
        this.location = location;
    }

    public void draw() {
        effect.draw(tick, location);
        tick++;
    }

    public boolean checkLocation(Location location) {
        return location.getWorld() == this.location.getWorld() && effect.radius >= location.distance(this.location) && !locked;
    }

    public SpotEffect getEffect() {
        return effect;
    }
    public String getRarity() {
        return rarity;
    }
    public void lock() {
        locked = true;
    }


}
