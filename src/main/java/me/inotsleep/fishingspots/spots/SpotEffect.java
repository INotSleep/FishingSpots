package me.inotsleep.fishingspots.spots;

import me.inotsleep.fishingspots.FishingSpots;
import me.inotsleep.fishingspots.utils.Pair;
import me.inotsleep.fishingspots.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpotEffect {
    CustomParticle randomParticle;
    List<Circle> circles = new ArrayList<>();
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
    public SpotEffect(String randomParticle, List<Map<String, Object>> list, String rarity, double radius, Pair lineRestore, Pair lineDamage, Pair catchingProgress, Pair catchingRegress) {
        this.catchingProgress = catchingProgress;
        this.catchingRegress = catchingRegress;
        this.lineDamage = lineDamage;
        this.lineRestore = lineRestore;
        this.randomParticle = SpotsManager.particles.get(randomParticle);
        this.radius = radius;
        if (this.randomParticle == null) {
            FishingSpots.getInstance().getLogger().severe("Invalid spotsEffects.yml configuration. Effect "+ rarity + " have not existing particle!");
            Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
            return;
        }
        try {
            for (Map<String, Object> stringObjectMap : list) {
                circles.add(new Circle((double) stringObjectMap.get("radius"), SpotsManager.particles.get((String) stringObjectMap.get("particle")), (int) stringObjectMap.get("particleCount")));
            }
        } catch (ClassCastException e) {
            FishingSpots.getInstance().getLogger().severe("Invalid spotsEffects.yml configuration. Please save and recreate this file!");
            Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
        }
    }

    public void draw(int tick,Location location) {
        if (tick%4 == 0) {
            location.getWorld().spawnParticle(randomParticle.particle, location.getX()+Utils.random(-0.3d, 0.3d), location.getY()+Utils.random(0d, 0.3d), location.getZ()+Utils.random(-0.3d, 0.3d), 1, 0d, 0d, 0d, 0d, randomParticle.data);
            circles.forEach(circle -> circle.draw(location));
        }
    }
    
    static class Circle {
        double radius;
        CustomParticle particle;
        int particleCount;
        
        public Circle(double radius, CustomParticle particle, int particleCount) {
            this.particle = particle;
            this.radius = radius;
            this.particleCount = particleCount;
        }

        public void draw(Location location) {
            World world = location.getWorld();
            for (double i = 0; i < 2*Math.PI; i += 2*Math.PI/particleCount) {
                double x = Math.sin(i)*radius;
                double z = Math.cos(i)*radius;
                world.spawnParticle(particle.particle, x+location.getX(), location.getY(), z+location.getZ(), 1, 0d, 0d, 0d, 0d, particle.data);
            }
        }
    }
}
