package dev.alexissdev.caduceus.api.user.loader;

import dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration;
import dev.alexissdev.caduceus.api.http.routes.ApiRoutes;
import dev.alexissdev.caduceus.api.user.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.gradle.internal.impldep.com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Implementation of {@link UserLoaderService} for loading user data from a remote HTTP service.
 * This service uses OkHttp and Gson to send HTTP requests and parse the retrieved data.
 *
 * <h3>Dependency Injection</h3>
 * The following dependencies are injected into this service:
 * <ul>
 *   <li>{@link OkHttpClient}: To handle HTTP requests.</li>
 *   <li>{@link Gson}: To parse JSON responses.</li>
 *   <li>{@link HttpConfiguration}: To configure HTTP connection settings, such as the base URL.</li>
 *   <li>{@link Executor}: To manage asynchronous task execution.</li>
 * </ul>
 *
 * <h3>Functionality</h3>
 * This class provides a method to asynchronously load a {@link User} object by its unique identifier
 * through an HTTP GET request to a remote API. The request is executed using OkHttp, and the JSON
 * response is parsed into a {@link User} object using Gson.
 *
 * <h3>Threading Model</h3>
 * Asynchronous operations are performed using OkHttp's internal threading model, and the resulting
 * {@link CompletableFuture} allows clients to handle the response or any errors in a non-blocking fashion.
 *
 * <h3>Behavior on Failure</h3>
 * In case of an unsuccessful HTTP response or an I/O error, the {@link CompletableFuture} is completed
 * exceptionally with the corresponding exception. This ensures proper error handling upstream.
 */

public class RemoteUserLoaderService
        implements UserLoaderService {

    @Inject
    private OkHttpClient client;
    @Inject
    private Gson gson;
    @Inject
    private HttpConfiguration httpConfiguration;
    @Inject
    private Executor executor;

    @Override
    public CompletableFuture<User> loadById(@NotNull String userId) {
        CompletableFuture<User> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url(httpConfiguration.getBaseUrl() + ApiRoutes.userById(userId))
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try {
                    if (!response.isSuccessful()) {
                        future.completeExceptionally(
                                new RuntimeException("HTTP " + response.code())
                        );
                        return;
                    }

                    String json = response.body().string();
                    User user = gson.fromJson(json, User.class);
                    future.complete(user);

                } catch (Exception e) {
                    future.completeExceptionally(e);
                } finally {
                    response.close();
                }
            }

        });

        return future;
    }
}
