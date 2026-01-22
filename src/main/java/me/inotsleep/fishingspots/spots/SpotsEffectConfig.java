package me.inotsleep.fishingspots.spots;

import com.inotsleep.insutils.api.config.Header;
import com.inotsleep.insutils.api.config.Path;
import com.inotsleep.insutils.api.particle.Animation;
import com.inotsleep.insutils.spi.config.Config;
import me.inotsleep.fishingspots.utils.Pair;
import me.inotsleep.fishingspots.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Header({
        " ======================================= ",
        " Effects config",
        " Here you can edit:",
        " Particles of rarities:",
        "  - circle",
        "  - random particles",
        " Rarity bobber radius, needed for catch",
        " Line damage and restore",
        " Catch progress and regress (uncatching)",
        " ======================================= ",
        " CAUTION!",
        " Line restore and catching regress applied every tick,",
        " Line damage and catching progress applied every player click",
        " If catching progress hits 1, player catch fish/treasure",
        " If catching progress hits -1, player will lost his fish/treasure",
        " If line damage hits 1, player will lost his fish/treasure",
        " and fishing rod will get damaged",
        " = YOU MUST CONFIGURE THIS BEFORE USING IN PRODUCTION =",
        " = CURRENT VALUES IS COPY-PASTED! ="
})
public class SpotsEffectConfig extends Config {
    @Path("effects")
    public static Map<String, SpotEffect> effects;

    @Path("animations")
    public static Map<String, Animation> animations;
    public SpotsEffectConfig(File baseDir) {
        super(baseDir, "spotsEffects.yml");
        saveDefaults();
    }

    public void saveDefaults() {
        effects = new HashMap<>();
        effects.put("common", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        effects.put("uncommon", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        effects.put("rare", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        effects.put("epic", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        effects.put("legendary", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        animations = new HashMap<>();
        animations.put("common", Utils.createDefaultAnimation());
        animations.put("uncommon", Utils.createDefaultAnimation());
        animations.put("rare", Utils.createDefaultAnimation());
        animations.put("epic", Utils.createDefaultAnimation());
        animations.put("legendary", Utils.createDefaultAnimation());
    }
}
