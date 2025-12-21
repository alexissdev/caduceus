package dev.alexissdev.caduceus.api.http.token.updater;

import dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration;
import dev.alexissdev.caduceus.api.http.response.LoginResponse;
import dev.alexissdev.caduceus.api.http.token.SecurityToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.gradle.internal.impldep.com.google.gson.Gson;

import javax.inject.Inject;

import java.io.IOException;

import static dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration.APPLICATION_JSON;

/**
 * Handles the process of updating and refreshing a security token.
 * <p>
 * This class facilitates the acquisition of new security tokens by sending
 * refresh requests to the server and updating the current security token
 * upon successful response.
 */

public class SecurityTokenUpdater {

    @Inject
    private HttpConfiguration httpConfiguration;
    @Inject
    private SecurityToken securityToken;
    @Inject
    private OkHttpClient client;
    @Inject
    private Gson gson;

    /**
     * Attempts to refresh the security token by sending a refresh token request to the server.
     * If the request is successful, updates the current security token with the new access and refresh tokens.
     *
     * @return true if the token refresh is successful; false if the request fails or an error occurs.
     */

    public boolean refreshToken() {
        try {
            RequestBody body =
                    RequestBody.create(gson.toJson(new LoginResponse(null, securityToken.getRefreshToken())), APPLICATION_JSON);

            Request request = new Request.Builder()
                    .url(httpConfiguration.getBaseUrl() + "/auth/refresh")
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) return false;
                updateToken(response);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void updateToken(Response response) throws IOException {
        LoginResponse tokens = gson.fromJson(response.body().string(), LoginResponse.class);
        securityToken.setToken(tokens.getAccessToken());
        securityToken.setRefreshToken(tokens.getRefreshToken());
    }
}
