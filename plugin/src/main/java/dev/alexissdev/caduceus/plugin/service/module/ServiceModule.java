package dev.alexissdev.caduceus.plugin.service.module;

import dev.alexissdev.caduceus.api.service.Service;
import dev.alexissdev.caduceus.plugin.service.ListenerLoaderService;
import dev.alexissdev.caduceus.plugin.service.PluginLoaderService;
import dev.alexissdev.caduceus.plugin.service.SpawnService;
import team.unnamed.inject.AbstractModule;

public class ServiceModule
        extends AbstractModule {

    @Override
    protected void configure() {
        multibind(Service.class).asSet()
                .to(ListenerLoaderService.class)
                .to(SpawnService.class)
                .singleton();

        bind(Service.class).to(PluginLoaderService.class).singleton();
    }
}
