package dev.alexissdev.caduceus.plugin.spawn.updater;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Service interface responsible for updating spawn-related data.
 * This interface provides methods to modify details associated with
 * specific locations within the system.
 */

public interface SpawnUpdaterService {

    /**
     * Updates the details of the specified location.
     *
     * @param location the location object to be updated; must not be null
     */

    default void update(@NotNull Location location) {
        update(location, null);
    }

    /**
     * Updates the details of the specified location and optionally executes a callback upon successful update.
     *
     * @param location the location object to be updated; must not be null
     * @param successCallback an optional callback that will be executed with the updated location
     *                         if the update operation succeeds; may be null
     */

    void update(@NotNull Location location, @Nullable Consumer<Location> successCallback);
}
