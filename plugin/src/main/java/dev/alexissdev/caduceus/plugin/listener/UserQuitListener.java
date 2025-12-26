package dev.alexissdev.caduceus.plugin.listener;

import dev.alexissdev.caduceus.api.user.User;
import dev.alexissdev.caduceus.api.user.sync.UserSyncService;
import dev.alexissdev.storage.ModelService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event listener that handles user data synchronization and cleanup when a player quits the server.
 * Implements the Bukkit {@link Listener} interface to listen for {@link PlayerQuitEvent}.
 * <p>
 * Responsibilities:
 * - Synchronizes user data with an external system or database when a player quits.
 * - Cleans up and deletes the user's data locally after successful synchronization.
 * - Logs any errors encountered during the synchronization or cleanup process.
 */

public class UserQuitListener
        implements Listener {

    @Inject
    private Logger logger;
    @Inject
    private UserSyncService userSyncService;
    @Inject
    private ModelService<User> userModelService;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = userModelService.findSync(player.getUniqueId().toString());
        if (user == null) {
            throw new IllegalStateException("User not found for player " + player.getName());
        }

        userSyncService.sync(user)
                .thenAccept((result) -> {
                    userModelService.deleteSync(player.getUniqueId().toString());
                })
                .exceptionally(throwable -> {
                    logger.log(Level.SEVERE, "Failed to sync user data for player " + user.getUsername(), throwable);
                    return null;
                });
    }
}
