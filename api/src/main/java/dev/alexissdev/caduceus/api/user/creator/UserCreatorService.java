package dev.alexissdev.caduceus.api.user.creator;

import dev.alexissdev.caduceus.api.http.response.UserResponse;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * UserCreatorService is an interface that defines methods for creating new users.
 * It provides the ability to create users using either a string-based unique identifier
 * or a UUID-based identifier.
 */

public interface UserCreatorService {

    /**
     * Creates a new user with the specified ID and username.
     *
     * @param id       the unique identifier of the user, represented as a non-null string
     * @param username the username of the user, represented as a non-null string
     * @return a CompletableFuture containing the UserResponse for the created user
     */

    CompletableFuture<UserResponse> create(@NotNull String id, @NotNull String username);

    /**
     * Creates a new user based on the given UUID and username.
     *
     * @param id       the unique identifier of the user, represented as a UUID
     * @param username the username of the user
     * @return a CompletableFuture containing the UserResponse for the created user
     */

    default CompletableFuture<UserResponse> create(@NotNull UUID id, @NotNull String username) {
        return create(id.toString(), username);
    }
}
