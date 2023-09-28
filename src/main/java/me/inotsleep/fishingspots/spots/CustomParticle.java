package me.inotsleep.fishingspots.spots;

import org.bukkit.Particle;

public class CustomParticle {
    Particle particle;
    Object data;
    public CustomParticle (Particle particle, Object data) {
        this.particle = particle;
        this.data = data;
    }
}
