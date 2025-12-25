package dev.alexissdev.caduceus.plugin.listener.module;

import dev.alexissdev.caduceus.plugin.listener.UserJoinListener;
import dev.alexissdev.caduceus.plugin.listener.UserQuitListener;
import org.bukkit.event.Listener;
import team.unnamed.inject.AbstractModule;

public class ListenerModule
        extends AbstractModule {

    @Override
    protected void configure() {
        multibind(Listener.class).asSet()
                .to(UserJoinListener.class)
                .to(UserQuitListener.class)
                .singleton();
    }
}
