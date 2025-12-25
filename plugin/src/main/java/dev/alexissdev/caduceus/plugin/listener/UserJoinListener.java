package dev.alexissdev.caduceus.plugin.listener;

import dev.alexissdev.caduceus.api.user.User;
import dev.alexissdev.caduceus.api.user.economy.UserEconomy;
import dev.alexissdev.caduceus.api.user.loader.UserLoaderService;
import dev.alexissdev.caduceus.api.user.statistic.UserStatistic;
import dev.alexissdev.storage.ModelService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.util.logging.Logger;

import static dev.alexissdev.caduceus.api.user.User.DEFAULT_LANGUAGE;

/**
 * Event listener that handles user data loading and persistence when a player joins the server.
 * Implements the Bukkit {@link Listener} interface to listen for {@link PlayerJoinEvent}.
 * <p>
 * Responsibilities:
 * - Loads the user data for the joining player from an external data source using {@link UserLoaderService}.
 * - Saves the loaded {@link User} data synchronously with the help of {@link ModelService}.
 * - Logs any errors that occur during user data loading or saving operations using {@link Logger}.
 */

public class UserJoinListener
        implements Listener {

    @Inject
    private UserLoaderService userLoaderService;
    @Inject
    private ModelService<User> userModelService;
    @Inject
    private Logger logger;
    @Inject
    private Plugin plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        userLoaderService.loadById(player.getUniqueId())
                .thenAccept(userModelService::saveSync)
                .exceptionally(exception -> {
                    plugin.getServer().getScheduler().runTask(plugin, () -> {
                        User newUser = User.builder()
                                .id(player.getUniqueId().toString())
                                .name(player.getName())
                                .language(DEFAULT_LANGUAGE)
                                .statistic(UserStatistic.initial())
                                .economy(UserEconomy.initial())
                                .build();
                        userModelService.saveSync(newUser);

                        logger.info("Register new user " + player.getName() + " with id " + newUser.getId());
                    });
                    return null;
                });
    }
}
