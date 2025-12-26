package dev.alexissdev.caduceus.plugin.serializable;

import dev.alexissdev.storage.bukkit.codec.YamlCodec;
import dev.alexissdev.storage.bukkit.codec.YamlWriter;
import dev.alexissdev.storage.codec.ModelReader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * A class that extends the {@link Location} class and implements the {@link YamlCodec} interface
 * to provide functionality for serializing and deserializing location data to YAML format.
 * <p>
 * This class represents a location within a specific {@link World} and includes optional
 * orientation data (yaw and pitch).
 * <p>
 * It is primarily used in scenarios where location data needs to be stored or transferred
 * in a serialized form, such as configuration files or network communication.
 */

public class BukkitLocationSerializable
        extends Location
        implements YamlCodec {

    public BukkitLocationSerializable(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public BukkitLocationSerializable(World world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
    }

    /**
     * It takes a string, splits it into an array, and then uses that array to create a new location
     *
     * @param serializedLocation The location to deserialize.
     * @return A location
     */
    public static @Nullable BukkitLocationSerializable fromString(String serializedLocation) {
        if (serializedLocation == null) {
            return null;
        }

        String[] split = serializedLocation.split(";");
        if (split.length != 6) {
            return null;
        }

        return new BukkitLocationSerializable(
                Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5])
        );
    }

    /**
     * Deserializes a {@link BukkitLocationSerializable} instance from the provided {@link ModelReader}.
     *
     * @param reader The {@link ModelReader} containing serialized location data.
     *               It must include keys "world_name", "x", "y", "z", "yaw", and "pitch".
     * @return A {@link BukkitLocationSerializable} object created from the deserialized data.
     * @throws IllegalArgumentException If the world specified by "world_name" does not exist.
     */

    public static BukkitLocationSerializable deserialize(ModelReader<Map<String, Object>> reader) {
        World possibleWorld = Bukkit.getWorld(reader.readString("world_name"));
        if (possibleWorld == null) {
            throw new IllegalArgumentException("World not found: " + reader.readString("world_name"));
        }

        return new BukkitLocationSerializable(
                possibleWorld,
                reader.readDouble("x"),
                reader.readDouble("y"),
                reader.readDouble("z"),
                reader.readFloat("yaw"),
                reader.readFloat("pitch"));
    }

    /**
     * Creates a {@link BukkitLocationSerializable} instance from the given {@link Location} object.
     *
     * @param location The {@link Location} object to be transformed into a {@link BukkitLocationSerializable}.
     *                 Must not be null.
     * @return A new {@link BukkitLocationSerializable} instance based on the provided {@link Location}.
     */

    public static BukkitLocationSerializable from(@NotNull Location location) {
        return new BukkitLocationSerializable(
                location.getWorld(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch()
        );
    }


    @Override
    public Map<String, Object> serialize() {
        return YamlWriter.create()
                .write("world_name", getWorld().getName())
                .write("x", getX())
                .write("y", getY())
                .write("z", getZ())
                .write("yaw", getYaw())
                .write("pitch", getPitch())
                .end();
    }

    public @NotNull String serializeToString() {
        return getWorld().getName()
                + ";"
                + getX() + ";"
                + getY()
                + ";"
                + getZ()
                + ";"
                + getYaw()
                + ";"
                + getPitch();
    }
}
