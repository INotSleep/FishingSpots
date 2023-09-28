package me.inotsleep.fishingspots.commands;

import me.inotsleep.fishingspots.FishingSpots;
import me.inotsleep.fishingspots.game.DropItems;
import me.inotsleep.fishingspots.spots.Spot;
import me.inotsleep.fishingspots.spots.SpotsManager;
import me.inotsleep.utils.AbstractCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MainCommand extends AbstractCommand {
    public MainCommand() {
        super("fishingspots", "fishingspots");
    }

    @Override
    public void toExecute(CommandSender commandSender, String s, String[] args) {
        if (args.length == 0) {
            if (commandSender.hasPermission("fishingspots.version")) {
                commandSender.sendMessage("/==============\n" +
                        "| FishingSpots v"+FishingSpots.getInstance().getDescription().getVersion() + "\n" +
                        "| Authors: " + String.join(" ", FishingSpots.getInstance().getDescription().getAuthors()) + "\n" +
                        "\\==============");
            }
        } else {
            switch(args[0].toLowerCase()) {
                case "createitem": {
                    if (commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        if (!player.hasPermission("fishingspots.admin.createItem")) {
                            player.sendMessage(FishingSpots.messages.getString("messages.noPermission"));
                            return;
                        }
                        if (args.length != 2) {
                            player.sendMessage("You need to specify id of item!");
                            return;
                        }
                        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                            player.sendMessage("You need to hold item in hand!");
                            return;
                        }
                        FishingSpots.rewardsConfig.items.put(args[1], new DropItems(player.getInventory().getItemInMainHand().clone(), 1, 1));
                        FishingSpots.rewardsConfig.save();
                        player.sendMessage("You successfully added item.");
                    } else {
                        commandSender.sendMessage("Command is Player only");
                    }
                    break;
                }
                case "reload": {
                    if (!commandSender.hasPermission("fishingspots.admin.reload")) {
                        commandSender.sendMessage(FishingSpots.messages.getString("messages.noPermission"));
                        return;
                    }
                    FishingSpots.reload();
                    commandSender.sendMessage("Configs successfully reloaded.");
                    break;
                }
                case "spawnspot": {
                    if (commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        if (!player.hasPermission("fishingspots.admin.spawnSpot")) {
                            player.sendMessage(FishingSpots.messages.getString("messages.noPermission"));
                            return;
                        }
                        Block block = player.getWorld().getHighestBlockAt(player.getLocation());
                        if (!FishingSpots.config.allowedBlocks.contains(block.getType())) {
                            player.sendMessage("You must be above one of allowed blocks!");
                            return;
                        }
                        try {
                            Location location = player.getLocation();
                            location.setY(block.getY()+1.1d);
                            if (args.length == 2) SpotsManager.addSpot(new Spot(args[1], location));
                            else SpotsManager.addSpot(new Spot(location));

                            player.sendMessage("Successfully spawned new spot");
                        } catch (IllegalStateException e) {
                            player.sendMessage("Rarity " + args[1] + " do not exists");
                        }
                    } else {
                        commandSender.sendMessage("Command is Player only");
                    }
                    break;
                }
            }
        }
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length != 1) return null;
        List<String> list =  new ArrayList<>();
        if (sender.hasPermission("fishingspots.admin.createItem") && "createitem".startsWith(args[0])) list.add("createitem");
        if (sender.hasPermission("fishingspots.admin.reload") && "reload".startsWith(args[0])) list.add("reload");
        if (sender.hasPermission("fishingspots.admin.spawnSpot") && "spawnspot".startsWith(args[0])) list.add("spawnspot");
        return list;
    }
}
