package dev.alexissdev.caduceus.plugin.http;

import dev.alexisdev.bukkit.yaml.ColorizedYamlConfiguration;
import dev.alexisdev.bukkit.yaml.LoadedYamlConfiguration;
import dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration;
import dev.alexissdev.caduceus.api.http.credentials.ServerCredentials;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

/**
 * HttpConfigurationProvider is responsible for providing an instance of
 * {@link HttpConfiguration} by loading the necessary configuration details
 * from a YAML configuration file specific to the provided plugin.
 * <p>
 * The configuration is expected to include details such as the base URL
 * and server credentials, which are then encapsulated in the {@link HttpConfiguration}
 * object. This class abstracts away the details of reading, parsing, and
 * validating the YAML configuration file, providing a convenient way to
 * retrieve a fully initialized {@link HttpConfiguration}.
 * <p>
 * If the configuration file is not properly formatted, missing required
 * fields, or otherwise cannot be loaded, a {@link RuntimeException} is thrown.
 */

public class HttpConfigurationProvider {

    /**
     * Provides an instance of {@link HttpConfiguration} by loading and parsing
     * the configuration details from a YAML file associated with the given plugin.
     * The YAML file is expected to include the base URL and server credentials
     * under specific keys.
     *
     * @param plugin the plugin instance for which the configuration file is loaded
     * @return an {@link HttpConfiguration} object containing the loaded configuration details
     * @throws RuntimeException if an I/O error occurs or the configuration is invalid
     */

    public static HttpConfiguration provideConfiguration(Plugin plugin) {
        try {
            YamlConfiguration configuration = LoadedYamlConfiguration.builder()
                    .plugin(plugin)
                    .name("http")
                    .build();

            return new HttpConfiguration(
                    configuration.getString("url"),
                    new ServerCredentials(
                            configuration.getString("credentials.username"),
                            configuration.getString("credentials.password")
                    )
            );
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
