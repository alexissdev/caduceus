package dev.alexissdev.caduceus.plugin.service;

import dev.alexissdev.caduceus.api.service.Service;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.util.Set;
import java.util.logging.Logger;

public class ListenerLoaderService
        implements Service {

    @Inject
    private Set<Listener> listenerList;
    @Inject
    private Plugin plugin;
    @Inject
    private Logger logger;

    @Override
    public void start() {
        listenerList.forEach(listener -> {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
            logger.info("Registered event listener: " + listener.getClass().getSimpleName());
        });
    }
}
