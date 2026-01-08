package dev.alexissdev.caduceus.api;

import dev.alexissdev.caduceus.api.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * The {@code UserAPI} interface defines a contract for user-related operations,
 * such as fetching cached user data and updating user information.
 * <p>
 * Implementations of this interface are responsible for providing mechanisms
 * to retrieve user objects from a cache and update user information in the
 * underlying system or data store.
 */

public interface UserAPI {

    /**
     * Retrieves a cached user associated with the specified user ID.
     * If a user corresponding to the given ID exists in the cache, it returns an {@link Optional}
     * containing the {@code User} instance. Otherwise, it returns {@link Optional#empty()}.
     *
     * @param userId the unique identifier of the user to retrieve from the cache; must not be null.
     * @return an {@link Optional} containing the cached {@code User} if found; otherwise, {@link Optional#empty()}.
     */

    Optional<User> getCachedUser(@NotNull String userId);

    /**
     * Updates the provided user in the underlying data store or system.
     * The user object must not be null and should contain the necessary
     * details for the update operation.
     *
     * @param user the {@code User} instance to be updated; must not be null.
     */

    void updateUser(@NotNull User user);
}
