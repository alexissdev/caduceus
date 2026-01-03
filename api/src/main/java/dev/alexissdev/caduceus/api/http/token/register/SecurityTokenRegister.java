package dev.alexissdev.caduceus.api.http.token.register;

import dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration;
import dev.alexissdev.caduceus.api.http.routes.ApiRoutes;
import dev.alexissdev.caduceus.api.http.token.SecurityToken;
import dev.alexissdev.caduceus.api.http.token.checker.SecurityTokenExpirationChecker;
import dev.alexissdev.caduceus.api.http.token.updater.SecurityTokenUpdater;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

import static dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration.APPLICATION_JSON;

/**
 * The SecurityTokenFactory class is responsible for managing the authentication process
 * for acquiring or refreshing security tokens. This includes validating token expiration,
 * performing token refresh operations, and initiating login requests to the server.
 * <p>
 * The class leverages injected dependencies for performing its operations, including:
 * - HttpConfiguration: Provides server credentials and base URL for API requests.
 * - SecurityToken: Holds the current authentication token and refresh token.
 * - Gson: Used for serializing and deserializing JSON payloads.
 * - OkHttpClient: HTTP client for executing API requests.
 * - SecurityTokenExpirationChecker: Validates if the security token has expired.
 * - SecurityTokenUpdater: Handles token refresh and update logic.
 * <p>
 * The `login` method ensures that a valid security token is available by either refreshing
 * an existing token or performing a new authentication request to the server.
 * <p>
 * Exceptions such as network errors or invalid server responses are propagated as necessary.
 */

public class SecurityTokenRegister {

    @Inject
    private HttpConfiguration httpConfiguration;
    @Inject
    private SecurityToken securityToken;
    @Inject
    private Gson gson;
    @Inject
    @Named("no-auth")
    private OkHttpClient client;
    @Inject
    private SecurityTokenExpirationChecker tokenExpirationChecker;
    @Inject
    private SecurityTokenUpdater securityTokenUpdater;

    public void register() throws IOException {
        String possibleRefreshToken = securityToken.getRefreshToken();
        if (possibleRefreshToken != null && !tokenExpirationChecker.check(possibleRefreshToken)) {
            if (securityTokenUpdater.refreshToken()) return;
        }

        RequestBody body = RequestBody.create(gson.toJson(httpConfiguration.getServerCredentials()), APPLICATION_JSON);
        Request request = new Request.Builder().url(httpConfiguration.getBaseUrl() + ApiRoutes.AUTH_LOGIN).post(body).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Server login failed");
            }

            securityTokenUpdater.updateToken(response);
        }
    }


}
