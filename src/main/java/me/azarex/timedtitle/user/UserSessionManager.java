package me.azarex.timedtitle.user;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Manages the user's session, loads and unloads their data when they
 * join and leave the game.
 */
public class UserSessionManager implements Listener {

    private final UserLoader userLoader;

    public UserSessionManager(UserLoader userLoader) {
        this.userLoader = userLoader;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        userLoader.load(event.getPlayer());
    }

    @EventHandler
    private void onLeave(PlayerQuitEvent event) {
        userLoader.unload(event.getPlayer());
    }

}
