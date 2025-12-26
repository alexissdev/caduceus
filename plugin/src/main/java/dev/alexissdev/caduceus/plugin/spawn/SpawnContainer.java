package dev.alexissdev.caduceus.plugin.spawn;

import dev.alexissdev.caduceus.plugin.serializable.BukkitLocationSerializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a container that holds information about a spawn location.
 * <p>
 * This class is used to encapsulate a serializable location object that
 * represents a specific point in a Minecraft world. The location uses
 * the {@link BukkitLocationSerializable} class, which allows for YAML-based
 * serialization and deserialization of location data, facilitating storage
 * and transfer.
 * <p>
 * The spawn location contained within this class may include world, position
 * (x, y, z coordinates), and orientation (yaw and pitch) information,
 * making it suitable for scenarios requiring precise spatial reference.
 */

@Getter
@Setter
public class SpawnContainer {
    private BukkitLocationSerializable location;
}
