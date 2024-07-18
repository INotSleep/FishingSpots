package me.inotsleep.fishingspots.game;

import me.inotsleep.fishingspots.FishingSpots;
import me.inotsleep.fishingspots.Messages;
import me.inotsleep.fishingspots.spots.Spot;
import me.inotsleep.fishingspots.spots.SpotEffect;
import me.inotsleep.fishingspots.spots.SpotsManager;
import me.inotsleep.fishingspots.utils.Utils;
import me.inotsleep.utils.MessageUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Game {
    Player player;
    Spot spot;
    SpotEffect effect;
    double line = 0;
    double catching = 0;
    FishHook hook;
    boolean end = false;

    public Game(Player player, Spot spot, FishHook hook) {
        this.hook = hook;
        this.player = player;
        this.spot = spot;
        this.effect = spot.getEffect();
    }
    public void tick() {
        if (end) return;
        if (!player.isOnline()) {
            stopGame(Reason.UNKNOWN);
            return;
        }
        if (hook.isDead()) {
            stopGame(Reason.UNKNOWN);
            return;
        }
        line -= effect.getLineRestore().generate();
        line = Math.max(line, 0);
        catching -= effect.getCatchingRegress().generate();
        catching = Math.max(catching, -1);
        if (catching <= -1) {
            stopGame(Reason.LOOSE);
        }
        print();
    }

    public void click() {
        if (end) return;
        line += effect.getLineDamage().generate();
        line = Math.min(line, 1);
        catching += effect.getCatchingProgress().generate();
        catching = Math.min(catching, 1);
        if (line >= 1) {
            stopGame(Reason.LINE);
        }
        if (catching >= 1) {
            stopGame(Reason.CATCH);
        }
    }

    public void stopGame(Reason reason) {
        end = true;
        switch (reason) {
            case LINE: {
                this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', Messages.looseLine)));
                damageHook();
                break;
            }
            case CATCH: {
                this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', Messages.win)));
                DropItems dropItem = null;
                try {
                    dropItem = FishingSpots.rewardsConfig.items.get(Utils.generateRarity(FishingSpots.rewardsConfig.rarities.get(spot.getRarity())));
                } catch (Exception e) {
                    FishingSpots.getInstance().getLogger().severe("Invalid rewards.yml configuration. Rarities do not have 100% rarity!");
                    Bukkit.getPluginManager().disablePlugin(FishingSpots.getInstance());
                }
                if (dropItem == null) return;
                ItemStack stack = dropItem.itemStack.clone();
                stack.setAmount((int) Utils.random(dropItem.min, dropItem.max));
                Item item = (Item) hook.getWorld().spawnEntity(hook.getLocation(), EntityType.DROPPED_ITEM);
                item.setItemStack(stack);
                hook.setHookedEntity(item);
                hook.pullHookedEntity();
                damageHook();
                break;
            }
            case LOOSE: {
                this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', Messages.looseLoose)));
                damageHook();
                break;
            }
            case UNKNOWN: {
                if (player.isOnline()) this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', Messages.looseNoFishingRod)));
                break;
            }
        }

        SpotsManager.removeSpot(spot);
        GameManager.removeGame(this);

    }

    void damageHook() {
        ItemStack stack = player.getInventory().getItemInMainHand();
        stack = stack.getType() == Material.FISHING_ROD ? stack : player.getInventory().getItemInOffHand();
        Damageable meta = (Damageable) stack.getItemMeta();
        meta.setDamage(meta.hasDamage() ? meta.getDamage()+1 : 1);
        stack.setItemMeta((ItemMeta) meta);
        hook.remove();
    }

    private void print() {
        int length = Messages.length;
        int progress = (int) (length - Math.floor(length *(catching+1)/2));
        String main = ChatColor.translateAlternateColorCodes('&', Messages.charMain);
        String bkg = ChatColor.translateAlternateColorCodes('&', Messages.charBackground);
        String curr = ChatColor.translateAlternateColorCodes('&', Messages.charCurrent);
        String color = Messages.getColor(line);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i<length; i++) {
            if (progress > 0) {
                progress--;
                str.append(progress == 0 ? "&r" + curr : color + main);
            } else str.append("&r"+bkg);
        }
        if (!player.isOnline()) {
            stopGame(Reason.UNKNOWN);
            return;
        }
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', MessageUtil.parsePlaceholders(Messages.format, str.toString()))));
    }

    public enum Reason {
        LINE, CATCH, LOOSE, UNKNOWN
    }
}
