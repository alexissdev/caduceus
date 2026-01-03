package dev.alexissdev.caduceus.plugin.listener;

import dev.alexissdev.caduceus.api.user.update.UserStatisticUpdater;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.inject.Inject;

import static dev.alexissdev.caduceus.api.user.update.UserStatisticUpdater.Action;
import static dev.alexissdev.caduceus.api.user.update.UserStatisticUpdater.Type;

/**
 * Event listener that handles updates to player kill and death statistics when a player dies in the game.
 * Implements the Bukkit {@link Listener} interface to listen for {@link PlayerDeathEvent}.
 * <p>
 * Responsibilities:
 * - Increments the death count for the player who died.
 * - If the player was killed by another player, increments the kill count for the killer.
 * - Interacts with {@link UserStatisticUpdater} to update the relevant statistic metrics for both kills and deaths.
 * <p>
 * Dependencies:
 * - An instance of the {@link UserStatisticUpdater} is injected and used to manage updates to the player's statistics.
 * <p>
 * Behavior:
 * - When a {@link PlayerDeathEvent} occurs:
 *   - The player's death count is incremented by 1.
 *   - If the player was killed by another player, the killer's kill count is incremented by 1.
 */

public class PlayerDeathListener
        implements Listener {

    @Inject
    private UserStatisticUpdater userStatisticUpdater;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player possibleKiller = player.getKiller();
        if (possibleKiller != null) {
            userStatisticUpdater.update(possibleKiller.getUniqueId().toString(), Type.KILLS, Action.INCREMENT, 1);
        }

        userStatisticUpdater.update(player.getUniqueId().toString(), Type.DEATHS, Action.INCREMENT, 1);
    }
}
