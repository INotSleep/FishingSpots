package me.inotsleep.fishingspots.spots;

import me.inotsleep.fishingspots.FishingSpots;
import me.inotsleep.fishingspots.utils.Pair;
import me.inotsleep.utils.AbstractConfig;
import me.inotsleep.utils.AbstractPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpotsEffectConfig extends AbstractConfig {
    public SpotsEffectConfig(AbstractPlugin plugin) {
        super(plugin, "spotsEffects.yml", false);
    }

    @Override
    public void addDefaults() {
        header= " ======================================= " +
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

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("radius", 0.5);
        map.put("particle", "star1");
        map.put("particleCount", 10);
        list.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("radius", 0.9);
        map1.put("particle", "star1");
        map1.put("particleCount", 30);
        list.add(map1);
        addDefault("common.circle", list);
        addDefault("common.randomParticle", "randomParticle1");
        addDefault("common.bobberToCenterMaxDistance", 1.5d);
        addDefault("common.line.restore.min", 0.03);
        addDefault("common.line.restore.max", 0.05);
        addDefault("common.line.damage.min", 0.2);
        addDefault("common.line.damage.max", 0.23);
        addDefault("common.catching.progress.min", 0.3);
        addDefault("common.catching.progress.max", 0.4);
        addDefault("common.catching.regress.min", 0.02);
        addDefault("common.catching.regress.max", 0.03);
        List<Map<String, Object>> list1 = new ArrayList<>();
        Map<String, Object> map111 = new HashMap<>();
        map111.put("radius", 0.5);
        map111.put("particle", "star1");
        map111.put("particleCount", 10);
        list1.add(map111);
        Map<String, Object> map11 = new HashMap<>();
        map11.put("radius", 0.9);
        map11.put("particle", "star1");
        map11.put("particleCount", 30);
        list1.add(map11);
        addDefault("uncommon.circle", list1);
        addDefault("uncommon.randomParticle", "randomParticle2");
        addDefault("uncommon.bobberToCenterMaxDistance", 1.5d);
        addDefault("uncommon.line.restore.min", 0.03);
        addDefault("uncommon.line.restore.max", 0.05);
        addDefault("uncommon.line.damage.min", 0.2);
        addDefault("uncommon.line.damage.max", 0.23);
        addDefault("uncommon.catching.progress.min", 0.3);
        addDefault("uncommon.catching.progress.max", 0.4);
        addDefault("uncommon.catching.regress.min", 0.02);
        addDefault("uncommon.catching.regress.max", 0.03);
        List<Map<String, Object>> list2 = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("radius", 0.5);
        map2.put("particle", "star1");
        map2.put("particleCount", 10);
        list2.add(map2);
        Map<String, Object> map12 = new HashMap<>();
        map12.put("radius", 0.9);
        map12.put("particle", "star1");
        map12.put("particleCount", 30);
        list2.add(map12);
        addDefault("rare.circle", list2);
        addDefault("rare.randomParticle", "randomParticle3");
        addDefault("rare.bobberToCenterMaxDistance", 1.5d);
        addDefault("rare.line.restore.min", 0.03);
        addDefault("rare.line.restore.max", 0.05);
        addDefault("rare.line.damage.min", 0.2);
        addDefault("rare.line.damage.max", 0.23);
        addDefault("rare.catching.progress.min", 0.3);
        addDefault("rare.catching.progress.max", 0.4);
        addDefault("rare.catching.regress.min", 0.02);
        addDefault("rare.catching.regress.max", 0.03);
        List<Map<String, Object>> list3 = new ArrayList<>();
        Map<String, Object> map3 = new HashMap<>();
        map3.put("radius", 0.5);
        map3.put("particle", "star1");
        map3.put("particleCount", 10);
        list3.add(map3);
        Map<String, Object> map13 = new HashMap<>();
        map13.put("radius", 0.9);
        map13.put("particle", "star1");
        map13.put("particleCount", 30);
        list3.add(map13);
        addDefault("epic.circle", list3);
        addDefault("epic.randomParticle", "randomParticle4");
        addDefault("epic.bobberToCenterMaxDistance", 1.5d);
        addDefault("epic.line.restore.min", 0.03);
        addDefault("epic.line.restore.max", 0.05);
        addDefault("epic.line.damage.min", 0.2);
        addDefault("epic.line.damage.max", 0.23);
        addDefault("epic.catching.progress.min", 0.3);
        addDefault("epic.catching.progress.max", 0.4);
        addDefault("epic.catching.regress.min", 0.02);
        addDefault("epic.catching.regress.max", 0.03);
        List<Map<String, Object>> list4 = new ArrayList<>();
        Map<String, Object>map4 = new HashMap<>();
        map4.put("radius", 0.5);
        map4.put("particle", "star1");
        map4.put("particleCount", 10);
        list4.add(map4);
        Map<String, Object>map14 = new HashMap<>();
        map14.put("radius", 0.9);
        map14.put("particle", "star1");
        map14.put("particleCount", 30);
        list4.add(map14);
        addDefault("legendary.circle", list4);
        addDefault("legendary.randomParticle", "randomParticle5");
        addDefault("legendary.bobberToCenterMaxDistance", 1.5d);
        addDefault("legendary.line.restore.min", 0.03);
        addDefault("legendary.line.restore.max", 0.05);
        addDefault("legendary.line.damage.min", 0.2);
        addDefault("legendary.line.damage.max", 0.23);
        addDefault("legendary.catching.progress.min", 0.3);
        addDefault("legendary.catching.progress.max", 0.4);
        addDefault("legendary.catching.regress.min", 0.02);
        addDefault("legendary.catching.regress.max", 0.03);
    }

    @Override
    public void doReloadConfig() {
        config.getKeys(false).forEach(key -> {
            ConfigurationSection section = config.getConfigurationSection(key);

            try {
                List<Map<String, Object>> circles = (List<Map<String, Object>>) section.getList("circle");
                if (circles == null) throw new ClassCastException();
                SpotsManager.effects.put(key, new SpotEffect(section.getString("randomParticle"), circles, key, section.getDouble("bobberToCenterMaxDistance"), new Pair(section.getDouble("line.restore.min"), section.getDouble("line.restore.max")), new Pair(section.getDouble("line.damage.min"), section.getDouble("line.damage.max")), new Pair(section.getDouble("catching.progress.min"), section.getDouble("catching.progress.max")), new Pair(section.getDouble("catching.regress.min"), section.getDouble("catching.regress.max"))));
            } catch (ClassCastException | NullPointerException e) {
                FishingSpots.getInstance().getLogger().severe("Invalid spotsEffects.yml configuration. Please save and recreate this file!");
                e.printStackTrace();
                Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
            }
        });
    }

    @Override
    public void doSave() {

    }
}
