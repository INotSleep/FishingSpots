package me.inotsleep.fishingspots.spots;

import com.inotsleep.insutils.api.logging.LoggingManager;
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

    int lifeTime;

    public Spot(String rarity, Location location) throws IllegalArgumentException {
        effect = SpotsEffectConfig.effects.get(rarity);
        this.rarity = rarity;
        this.location = location;
        saveLifeTime();
    }

    public Spot(Location location) {
        String rarity = null;
        try {
            rarity = Utils.generateRarity(FishingSpots.config.settings.rarities);
        } catch (Exception e) {
            LoggingManager.error("Invalid config.yml configuration. Rarities do not have 100% rarity!");
            Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
        }
        if (rarity == null) return;
        effect = SpotsEffectConfig.effects.get(rarity);
        if (effect == null) {
            LoggingManager.error("Rarity " + rarity + "does not have effect! You must create it in spotsEffects.yml");
            Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
        }
        this.rarity = rarity;
        this.location = location;
        saveLifeTime();
    }

    private void saveLifeTime() {
        lifeTime = FishingSpots.config.settings.lifeTime.getOrDefault(rarity, 60 * 20);
    }

    private void checkRemovable() {
        if (locked) return;
        if (tick>=lifeTime) SpotsManager.removeSpot(this);
    }

    public void draw() {
        effect.draw(tick, location);
        tick++;
        checkRemovable();
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
