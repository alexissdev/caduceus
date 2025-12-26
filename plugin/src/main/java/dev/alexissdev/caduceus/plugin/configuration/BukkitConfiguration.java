package dev.alexissdev.caduceus.plugin.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class BukkitConfiguration
        extends YamlConfiguration {

    private final String fileName;
    private final Plugin plugin;
    private final File folder;

    public BukkitConfiguration(Plugin plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }

    public BukkitConfiguration(Plugin plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    public BukkitConfiguration(Plugin plugin, String fileName, String fileExtension, File folder) {
        this.folder = folder;
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : fileExtension);
        this.create();
    }

    /**
     * Creates a configuration file for the plugin. If the file already exists, it is loaded and saved again.
     * If the file does not exist but a resource with the same name is available within the plugin's resources,
     * the resource is copied to the file. Otherwise, an empty file is created, saved, and loaded.
     * <p>
     * If an error occurs during any file operation or while loading the configuration, a log message is written
     * to the plugin's logger with details of the failure.
     * <p>
     * This method ensures that the configuration file is correctly initialized and loaded, allowing later
     * interactions with the configuration data.
     * <p>
     * Exceptions such as {@code IOException} or {@code InvalidConfigurationException} arising during the file
     * operations are caught and logged to prevent disruption of the program execution.
     */

    public void create() {
        try {
            File file = new File(this.folder, this.fileName);
            if (file.exists()) {
                this.load(file);
                this.save(file);
                return;
            }
            if (this.plugin.getResource(this.fileName) != null) {
                this.plugin.saveResource(this.fileName, false);
            } else {
                this.save(file);
            }

            this.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            this.plugin
                    .getLogger()
                    .log(
                            Level.SEVERE, "Creation of Configuration '" + this.fileName + "' failed.", exception);
        }
    }

    /**
     * Saves the configuration data to a file on disk.
     * <p>
     * This method attempts to save the current state of the configuration to a file located in the
     * plugin's data folder. If the file already exists, it will be overwritten with the current
     * configuration state.
     * <p>
     * If an {@code IOException} occurs during the file saving process, the exception is caught and
     * logged to the plugin's logger. The log entry provides detailed information about the file
     * that failed to save, as well as the exception details.
     * <p>
     * The file name and location are determined by the configuration's initialization parameters,
     * including the name of the file and the folder associated with the plugin.
     * <p>
     * This method ensures that any changes made to the configuration are persisted and available
     * for future retrieval.
     */

    public void save() {
        File folder = this.plugin.getDataFolder();
        File file = new File(folder, this.fileName);

        try {
            this.save(file);
        } catch (IOException exception) {
            this.plugin
                    .getLogger()
                    .log(Level.SEVERE, "Save of the file '" + this.fileName + "' failed.", exception);
        }
    }

    /**
     * Retrieves a numeric value from the configuration file at the specified path.
     *
     * @param path the configuration key to locate the numeric value
     * @return the numeric value found at the specified path
     * @throws IllegalStateException if no value is found at the specified path
     */

    public Number getNumber(String path) {
        Object object = get(path, null);
        if (object == null) {
            throw new IllegalStateException("The number not found in path='" + path + "'");
        }

        return (Number) object;
    }

    /**
     * Reloads the configuration file associated with this instance.
     * <p>
     * This method attempts to reload the configuration file from disk,
     * ensuring that any updates made to the file externally are reflected
     * in the current state of the configuration. The file is located based
     * on the file name and data folder specified during the initialization
     * of this configuration instance.
     * <p>
     * If an exception occurs during the reload process, such as an
     * {@code IOException} or {@code InvalidConfigurationException}, a
     * detailed error message is logged to the plugin's logger. This error
     * message includes the name of the file being reloaded and the
     * exception details to aid in troubleshooting.
     * <p>
     * This method is typically used to synchronize the in-memory
     * representation of the configuration with its on-disk counterpart.
     */

    public void reload() {
        File folder = this.plugin.getDataFolder();
        File file = new File(folder, this.fileName);

        try {
            this.load(file);
        } catch (InvalidConfigurationException | IOException exception) {
            this.plugin
                    .getLogger()
                    .log(Level.SEVERE, "Reload of the file '" + this.fileName + "' failed.", exception);
        }
    }
}