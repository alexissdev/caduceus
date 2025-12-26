package dev.alexissdev.caduceus.plugin.spawn.teleporter;

import dev.alexissdev.caduceus.plugin.spawn.SpawnContainer;
import me.yushust.message.MessageHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * The DefaultSpawnTeleporterService is an implementation of the SpawnTeleporterService
 * interface, responsible for teleporting a player to the default spawn location
 * as defined in a SpawnContainer.
 * <p>
 * This class uses dependency injection to manage its dependencies, such as a
 * MessageHandler for sending messages to players and a SpawnContainer for obtaining
 * the spawn location.
 * <p>
 * The teleportation process is controlled through the `teleport` method, which
 * checks if a valid spawn location exists in the SpawnContainer. If a valid location
 * is found, the player is teleported to the cloned location. Otherwise, the player
 * receives a message (if enabled) indicating that the spawn location does not exist.
 * <p>
 * Messages can also be displayed to the player to indicate the result of the teleportation
 * operation, using keys such as "spawn.not-exists" and "spawn.teleported".
 */

public class DefaultSpawnTeleporterService
        implements SpawnTeleporterService {

    @Inject
    private MessageHandler messageHandler;
    @Inject
    private SpawnContainer spawnContainer;

    @Override
    public void teleport(@NotNull Player player, boolean message) {
        Location location = spawnContainer.getLocation();
        if (location == null) {
            if (message) {
                messageHandler.send(player, "spawn.not-exists");
            }
            return;
        }

        player.teleport(location.clone());
        if (message) {
            messageHandler.send(player, "spawn.teleported");
        }
    }
}
