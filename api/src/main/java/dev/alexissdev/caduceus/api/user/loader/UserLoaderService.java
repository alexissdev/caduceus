package dev.alexissdev.caduceus.api.user.loader;

import dev.alexissdev.caduceus.api.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Service interface for loading user data.
 * Provides methods to retrieve {@link User} instances asynchronously using various identifiers.
 */

public interface UserLoaderService {


    /**
     * Loads a user by their unique identifier.
     *
     * @param userId the ID of the user to be loaded; must not be null
     * @return a CompletableFuture containing the user associated with the given ID
     */

    CompletableFuture<User> loadById(@NotNull String userId);

    /**
     * Loads a user by their unique identifier (UUID).
     *
     * @param userUuid the UUID of the user to be loaded
     * @return a CompletableFuture containing the user associated with the given UUID
     */


    default CompletableFuture<User> loadById(UUID userUuid) {
        return loadById(userUuid.toString());
    }
}
