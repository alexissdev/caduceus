package dev.alexissdev.caduceus.plugin.configuration.module;

import dev.alexissdev.caduceus.plugin.configuration.BukkitConfiguration;
import org.bukkit.plugin.Plugin;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

public class ConfigurationModule
        extends AbstractModule {

    @Provides
    @Singleton
    @Named("spawn")
    public BukkitConfiguration provideSpawnConfiguration(Plugin plugin) {
        return new BukkitConfiguration(plugin, "spawn");
    }
}
