package dev.alexissdev.caduceus.plugin.spawn.teleporter;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface SpawnTeleporterService {

    /**
     * Teleports the specified player to the default spawn location.
     *
     * @param player the player to teleport, must not be null
     */

    default void teleport(@NotNull Player player) {
        teleport(player, false);
    }

    /**
     * Teleports the specified player to the default spawn location.
     * Optionally, a message can be displayed to the player upon teleportation.
     *
     * @param player the player to teleport, must not be null
     * @param message whether to display a message to the player upon teleportation
     */

    void teleport(@NotNull Player player, boolean message);
}
