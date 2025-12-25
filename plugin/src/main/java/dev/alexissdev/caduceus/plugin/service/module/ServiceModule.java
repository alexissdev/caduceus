package dev.alexissdev.caduceus.plugin.service.module;

import dev.alexissdev.caduceus.api.service.Service;
import dev.alexissdev.caduceus.plugin.service.ListenerLoaderService;
import dev.alexissdev.caduceus.plugin.service.PluginLoaderService;
import team.unnamed.inject.AbstractModule;

public class ServiceModule
        extends AbstractModule {

    @Override
    protected void configure() {
        multibind(Service.class).asSet()
                .to(ListenerLoaderService.class)
                .singleton();

        bind(Service.class).to(PluginLoaderService.class).singleton();
    }
}
