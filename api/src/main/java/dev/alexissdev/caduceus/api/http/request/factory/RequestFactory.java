package dev.alexissdev.caduceus.api.http.request.factory;

import dev.alexissdev.caduceus.api.http.token.factory.SecurityTokenFactory;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.IOException;

/**
 * A factory class for creating pre-configured HTTP request objects.
 * This class provides utility methods for building requests
 * with specific headers and configurations.
 */

public class RequestFactory {

    @Inject
    private SecurityTokenFactory tokenFactory;

    /**
     * Creates and returns a pre-configured HTTP request builder with authorization headers.
     * Specifically, it adds a Bearer token for authorization and sets the content type to JSON.
     * The Bearer token is retrieved using the {@code tokenFactory}.
     *
     * @param url the URL for the HTTP request; must not be null
     * @return a {@code Request.Builder} instance pre-configured with the specified headers
     * @throws IllegalArgumentException if an error occurs while retrieving the token
     */

    public Request.Builder createAuthorizedRequest(@NotNull String url) {
        try {
            String token = tokenFactory.getToken();
            return new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer " + token)
                    .addHeader("Content-Type", "application/json");
        } catch (IOException e) {
            throw new IllegalArgumentException("Not token provided", e);
        }
    }

}
