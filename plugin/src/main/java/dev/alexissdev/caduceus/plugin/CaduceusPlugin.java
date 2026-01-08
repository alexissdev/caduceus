package dev.alexissdev.caduceus.plugin;

import dev.alexissdev.caduceus.api.service.Service;
import dev.alexissdev.caduceus.plugin.api.CaduceusAPI;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.Injector;

import javax.inject.Inject;

/**
 * Main plugin class for the Caduceus plugin, extending the {@link JavaPlugin} class.
 * This class is responsible for managing the plugin's lifecycle and its interactions
 * with the Bukkit framework, including initialization and shutdown behavior.
 * <p>
 * The plugin utilizes dependency injection for managing services through
 * the {@link Injector} framework. During the plugin's
 * startup process, the required dependencies are injected, and the configured
 * service is started. At shutdown, the service is stopped to ensure proper
 * cleanup of resources.
 * <p>
 * Key functionalities include:
 * - Dependency management via {@link PluginModule}
 * - Lifecycle management of a bound service
 * - Integration with Bukkit's {@link JavaPlugin} lifecycle methods
 */

public class CaduceusPlugin
        extends JavaPlugin {

    @Inject
    private Service service;

    @Getter
    @Inject
    private CaduceusAPI caduceusAPI;

    @Override
    public void onEnable() {
        Injector injector = Injector.create(new PluginModule(this));
        injector.injectMembers(this);

        service.start();
    }

    @Override
    public void onDisable() {
        service.stop();
    }

}
