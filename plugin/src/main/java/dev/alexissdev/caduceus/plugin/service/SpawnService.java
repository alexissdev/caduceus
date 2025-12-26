package dev.alexissdev.caduceus.plugin.service;

import dev.alexissdev.caduceus.api.service.Service;
import dev.alexissdev.caduceus.plugin.configuration.BukkitConfiguration;
import dev.alexissdev.caduceus.plugin.serializable.BukkitLocationSerializable;
import dev.alexissdev.caduceus.plugin.spawn.SpawnContainer;

import javax.inject.Inject;
import javax.inject.Named;

public class SpawnService
        implements Service {

    @Inject
    private SpawnContainer spawnContainer;
    @Inject
    @Named("spawn")
    private BukkitConfiguration configuration;

    @Override
    public void start() {
        String serializedLocation = configuration.getString("spawn", null);
        if (serializedLocation == null || serializedLocation.trim().isEmpty()) {
            return;
        }

        spawnContainer.setLocation(BukkitLocationSerializable.fromString(serializedLocation));
    }

    @Override
    public void stop() {
        BukkitLocationSerializable location = spawnContainer.getLocation();
        if (location == null) {
            if (configuration.contains("spawn")) {
                configuration.set("spawn", null);
            }
        } else {
            configuration.set("spawn", location.serializeToString());
        }

        configuration.save();
    }
}
