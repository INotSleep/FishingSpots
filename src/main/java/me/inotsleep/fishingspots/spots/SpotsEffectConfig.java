package me.inotsleep.fishingspots.spots;

import me.inotsleep.fishingspots.utils.Pair;
import me.inotsleep.utils.config.AbstractConfig;
import me.inotsleep.utils.AbstractPlugin;
import me.inotsleep.utils.config.Path;
import me.inotsleep.utils.particle.Animation;

import java.util.HashMap;
import java.util.Map;

public class SpotsEffectConfig extends AbstractConfig {
    @Path(path = "effects")
    public static Map<String, SpotEffect> effects;

    @Path(path = "animations")
    public static Map<String, Animation> animations;
    public SpotsEffectConfig(AbstractPlugin plugin) {
        super(plugin, "spotsEffects.yml");
    }

    @Override
    public String getHeader() {
        return " ======================================= " +
                "\n Effects config" +
                "\n Here you can edit:" +
                "\n Particles of rarities:" +
                "\n  - circle" +
                "\n  - random particles" +
                "\n Rarity bobber radius, needed for catch" +
                "\n Line damage and restore" +
                "\n Catch progress and regress (uncatching)" +
                "\n ======================================= " +
                "\n CAUTION!" +
                "\n Line restore and catching regress applied every tick," +
                "\n Line damage and catching progress applied every player click" +
                "\n If catching progress hits 1, player catch fish/treasure" +
                "\n If catching progress hits -1, player will lost his fish/treasure" +
                "\n If line damage hits 1, player will lost his fish/treasure" +
                "\n and fishing rod will get damaged" +
                "\n = YOU MUST CONFIGURE THIS BEFORE USING IN PRODUCTION =" +
                "\n = CURRENT VALUES IS COPY-PASTED! =";
    }

    @Override
    public void saveDefaults() {
        effects = new HashMap<>();
        effects.put("common", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        effects.put("uncommon", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        effects.put("rare", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        effects.put("epic", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        effects.put("legendary", new SpotEffect(3d, new Pair(0.03, 0.05), new Pair(0.2, 0.23), new Pair(0.3, 0.4), new Pair(0.02, 0.03)));
        animations = new HashMap<>();
        animations.put("common", Animation.createNew());
        animations.put("uncommon", Animation.createNew());
        animations.put("rare", Animation.createNew());
        animations.put("epic", Animation.createNew());
        animations.put("legendary", Animation.createNew());
    }
}
