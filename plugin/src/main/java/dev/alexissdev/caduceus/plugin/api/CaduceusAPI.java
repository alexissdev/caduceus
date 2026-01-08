package dev.alexissdev.caduceus.plugin.api;

import dev.alexissdev.caduceus.api.UserAPI;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The {@code CaduceusAPI} class serves as the entry point for accessing core API functionalities
 * within the application. It provides access to the {@link UserAPI} implementation,
 * which enables user-related operations such as retrieving and updating user data.
 * <p>
 * This class relies on dependency injection to initialize and provide an instance of {@link UserAPI}.
 * The injected {@link UserAPI} can interact with implementations such as {@code PluginUserAPI} for
 * user-specific functionality like managing cached user data or updating user details.
 * <p>
 * The {@code CaduceusAPI} is designed to act as a centralized API provider, facilitating modular
 * and maintainable integration with user APIs and potentially other services in the future.
 */

@Singleton
public class CaduceusAPI {

    @Inject
    private UserAPI userAPI;

    /**
     * Provides access to the {@link UserAPI} implementation for user-related operations.
     *
     * @return an instance of {@link UserAPI} that allows interaction with user API functionalities.
     */

    public UserAPI getUserApi() {
        return userAPI;
    }
}
