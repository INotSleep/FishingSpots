package me.inotsleep.fishingspots.utils;

import me.inotsleep.fishingspots.game.Game;
import me.inotsleep.fishingspots.game.GameManager;
import me.inotsleep.fishingspots.spots.Spot;
import me.inotsleep.fishingspots.spots.SpotsManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class Listeners implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onFish(PlayerFishEvent event) {
        if (GameManager.getGame(event.getPlayer()) != null) {
            if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH || event.getState() == PlayerFishEvent.State.REEL_IN) {
                event.setCancelled(true);
                GameManager.getGame(event.getPlayer()).click();
            } else if (event.getState() == PlayerFishEvent.State.BITE) event.setCancelled(true);
            return;
        }
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

        Location pos = event.getHook().getLocation();
        Spot spot = SpotsManager.findSpot(pos);
        if (spot == null) return;

        event.setCancelled(true);
        spot.lock();

        GameManager.addGame(new Game(event.getPlayer(), spot, event.getHook()));
    }
}
