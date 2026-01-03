package dev.alexissdev.caduceus.plugin.spawn.module;

import dev.alexissdev.caduceus.plugin.spawn.SpawnContainer;
import dev.alexissdev.caduceus.plugin.spawn.teleporter.DefaultSpawnTeleporterService;
import dev.alexissdev.caduceus.plugin.spawn.teleporter.SpawnTeleporterService;
import dev.alexissdev.caduceus.plugin.spawn.updater.LocalSpawnUpdaterService;
import dev.alexissdev.caduceus.plugin.spawn.updater.SpawnUpdaterService;
import team.unnamed.inject.AbstractModule;

/**
 * The SpawnModule is a configuration module that sets up binding definitions
 * for the dependency injection framework. This module is responsible for defining
 * the singleton bindings for various services and the primary spawn-related
 * container used in the application.
 * <p>
 * The following bindings are configured:
 * 1. {@code SpawnContainer} is set up as a singleton for managing
 *    spawn location data.
 * <p>
 * 2. {@code SpawnTeleporterService} is bound to its default implementation,
 *    {@code DefaultSpawnTeleporterService}, as a singleton. This service handles
 *    teleportation functionality related to the defined spawn points.
 * <p>
 * 3. {@code SpawnUpdaterService} is bound to its implementation,
 *    {@code LocalSpawnUpdaterService}, as a singleton. It manages the logic for
 *    updating spawn-related data locally.
 */

public class SpawnModule
        extends AbstractModule {

    @Override
    protected void configure() {
        bind(SpawnContainer.class).singleton();
        bind(SpawnTeleporterService.class).to(DefaultSpawnTeleporterService.class).singleton();
        bind(SpawnUpdaterService.class).to(LocalSpawnUpdaterService.class).singleton();
    }
}
