package dev.alexissdev.caduceus.plugin.api.module;

import dev.alexissdev.caduceus.api.UserAPI;
import dev.alexissdev.caduceus.plugin.api.PluginUserAPI;
import team.unnamed.inject.AbstractModule;

public class InternalAPIModule
        extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserAPI.class).to(PluginUserAPI.class).singleton();
    }
}
