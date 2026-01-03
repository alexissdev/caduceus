package dev.alexissdev.caduceus.api.user.sync;

import dev.alexissdev.caduceus.api.user.User;
import java.util.concurrent.CompletableFuture;

/**
 * Interface for a service that handles the synchronization of user data with an external system or database.
 * The synchronization operation is performed asynchronously to ensure non-blocking behavior.
 */

public interface UserSyncService {

    /**
     * Synchronizes the given user's data asynchronously with a designated system or database.
     *
     * @param user the user object whose data is to be synchronized. The user object includes
     *             properties such as user ID, name, language, and associated statistics.
     * @return a {@code CompletableFuture<Void>} that completes when the synchronization process
     *         finishes. If the synchronization fails, the {@code CompletableFuture} will complete
     *         exceptionally.
     */

    CompletableFuture<Void> sync(User user);
}
