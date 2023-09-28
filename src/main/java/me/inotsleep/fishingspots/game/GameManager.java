package me.inotsleep.fishingspots.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameManager {
    static List<Game> games = new ArrayList<>();
    private static List<Game> toRemove = new ArrayList<>();

    public static void tick() {
        games.forEach(Game::tick);
        toRemove.forEach(game -> games.remove(game));
        toRemove.clear();
    }

    public static void addGame(Game game) {
        games.add(game);
    }
    public static void removeGame(Game game) {
        toRemove.add(game);
    }
    public static Game getGame(Player player) {
        Optional<Game> oGame = games.stream().filter(game -> game.player == player).findFirst();
        return oGame.orElse(null);
    }

    public void clear() {
        games = new ArrayList<>();
    }

}
