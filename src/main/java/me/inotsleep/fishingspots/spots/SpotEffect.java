package me.inotsleep.fishingspots.spots;

import com.inotsleep.insutils.api.config.Path;
import com.inotsleep.insutils.api.particle.compiled.CompiledAnimation;
import com.inotsleep.insutils.spi.config.SerializableObject;
import me.inotsleep.fishingspots.utils.Pair;
import org.bukkit.Location;

public class SpotEffect extends SerializableObject {
    CompiledAnimation animation;

    @Path("radius")
    double radius;

    @Path("lineDamage")
    Pair lineDamage;

    @Path("lineRestore")
    Pair lineRestore;

    @Path("catchingProgress")
    Pair catchingProgress;

    @Path("catchingRegress")
    Pair catchingRegress;

    public SpotEffect() {}

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
}
