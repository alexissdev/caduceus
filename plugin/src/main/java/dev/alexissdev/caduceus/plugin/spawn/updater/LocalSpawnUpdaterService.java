package dev.alexissdev.caduceus.plugin.spawn.updater;

import dev.alexissdev.caduceus.plugin.serializable.BukkitLocationSerializable;
import dev.alexissdev.caduceus.plugin.spawn.SpawnContainer;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.function.Consumer;

public class LocalSpawnUpdaterService
        implements SpawnUpdaterService {

    @Inject
    private SpawnContainer spawnContainer;

    @Override
    public void update(@NotNull Location location, @Nullable Consumer<Location> successCallback) {
        if (successCallback != null) {
            successCallback.accept(location);
        }

        spawnContainer.setLocation(BukkitLocationSerializable.from(location));
    }
}
