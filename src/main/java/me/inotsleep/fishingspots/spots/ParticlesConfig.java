package me.inotsleep.fishingspots.spots;

import me.inotsleep.fishingspots.FishingSpots;
import me.inotsleep.utils.AbstractConfig;
import me.inotsleep.utils.AbstractPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class ParticlesConfig extends AbstractConfig {
    public ParticlesConfig(AbstractPlugin plugin) {
        super(plugin, "particles.yml", false);
    }

    @Override
    public void addDefaults() {
        header= "Here you can create particles, that you can specife in spotsEffects.yml\n" +
                "All particles types you can find here: https://jd.papermc.io/paper/1.16/org/bukkit/Particle.html\n" +
                "If you use other version, just type in google: Paper particle javadoc VERSION\n" +
                "Some of particles can have data type:\n" +
                "REDSTONE - DustOptions (settings.r, .g, .b)\n" +
                "ITEM_CRACK - Item (settings.item)\n" +
                "BLOCK_CRACK, BLOCK_DUST, FALLING_DUST - Block (settings.block)\n" +
                "Examples shown here \\/";


        addDefault("star1.type", "END_ROD");

        addDefault("randomParticle1.type", "REDSTONE");
        addDefault("randomParticle1.settings.r", 255);
        addDefault("randomParticle1.settings.g", 255);
        addDefault("randomParticle1.settings.b", 255);

        addDefault("randomParticle2.type", "ITEM_CRACK");
        addDefault("randomParticle2.settings.item", "DIAMOND");

        addDefault("randomParticle3.type", "BLOCK_CRACK");
        addDefault("randomParticle3.settings.block", "bedrock");

        addDefault("randomParticle4.type", "REDSTONE");
        addDefault("randomParticle4.settings.r", 128);
        addDefault("randomParticle4.settings.g", 0);
        addDefault("randomParticle4.settings.b", 128);

        addDefault("randomParticle5.type", "REDSTONE");
        addDefault("randomParticle5.settings.r", 255);
        addDefault("randomParticle5.settings.g", 165);
        addDefault("randomParticle5.settings.b", 0);
    }

    @Override
    public void doReloadConfig() {
        config.getKeys(false).forEach(key -> {
            ConfigurationSection section = config.getConfigurationSection(key);
            try {
                Particle particle = Particle.valueOf(section.getString("type").toUpperCase());
                CustomParticle particle1;
                if (particle.getDataType().equals(ItemStack.class)) {
                    Material material = Material.matchMaterial(section.getString("settings.item"));
                    if (!material.isItem()) {
                        FishingSpots.getInstance().getLogger().severe("Invalid particles.yml configuration. Particle " + key + " has item parameter that no item");
                        Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
                        return;
                    }
                    particle1 = new CustomParticle(particle, new ItemStack(material));
                } else if (particle.getDataType().equals(BlockData.class)) {
                    BlockData data = Bukkit.getServer().createBlockData(Material.matchMaterial(section.getString("settings.block")));
                    particle1 = new CustomParticle(particle, data);
                } else if (particle.getDataType().equals(Particle.DustOptions.class)) {
                    Color options = Color.fromRGB(section.getInt("settings.r"), section.getInt("settings.g"), section.getInt("settings.b"));
                    particle1 = new CustomParticle(particle, new Particle.DustOptions(options, 1));
                } else {
                    particle1 = new CustomParticle(particle, null);
                }
                SpotsManager.particles.put(key, particle1);
            } catch (NullPointerException e) {
                FishingSpots.getInstance().getLogger().severe("Invalid particles.yml configuration. Please save and recreate this file!");
                Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
            }
        });
    }

    @Override
    public void doSave() {

    }
}
