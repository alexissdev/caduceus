package dev.alexissdev.caduceus.api.user.loader;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Service interface for loading user information based on unique identifiers.
 * Provides methods to retrieve user details using string-based or UUID-based identifiers.
 */

public interface UserLoaderService {

    /**
     * Loads the user information based on the provided unique user identifier.
     *
     * @param userId the unique identifier for the user to be loaded; must not be null
     */

    void loadById(@NotNull String userId);

    /**
     * Loads the user information based on the provided unique user identifier in the form of a UUID.
     *
     * @param userUuid the unique user identifier as a UUID; must not be null
     */

    default void loadById(UUID userUuid) {
        loadById(userUuid.toString());
    }
}
