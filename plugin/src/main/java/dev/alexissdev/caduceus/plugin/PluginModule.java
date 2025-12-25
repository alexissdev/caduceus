package dev.alexissdev.caduceus.plugin;

import dev.alexissdev.caduceus.api.APIModule;
import dev.alexissdev.caduceus.plugin.http.HttpConfigurationProvider;
import dev.alexissdev.caduceus.plugin.listener.module.ListenerModule;
import dev.alexissdev.caduceus.plugin.service.module.ServiceModule;
import org.bukkit.plugin.Plugin;
import team.unnamed.inject.AbstractModule;

import java.util.logging.Logger;

public class PluginModule
        extends AbstractModule {

    private final Plugin plugin;

    public PluginModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);
        bind(Logger.class).toInstance(plugin.getLogger());
        install(new APIModule(HttpConfigurationProvider.provideConfiguration(plugin)));

        install(new ListenerModule());
        install(new ServiceModule());
    }
}
