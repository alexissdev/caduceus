package dev.alexissdev.caduceus.api.user.sync;

import dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration;
import dev.alexissdev.caduceus.api.http.request.UpdateUserRequest;
import dev.alexissdev.caduceus.api.http.request.factory.RequestFactory;
import dev.alexissdev.caduceus.api.http.routes.ApiRoutes;
import dev.alexissdev.caduceus.api.user.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class RemoteUserSyncService
        implements UserSyncService {

    @Inject
    private OkHttpClient client;
    @Inject
    private Gson gson;
    @Inject
    private HttpConfiguration httpConfiguration;
    @Inject
    private RequestFactory requestFactory;

    @Override
    public CompletableFuture<Void> sync(User user) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        String json = gson.toJson(UpdateUserRequest.fromUser(user));

        RequestBody body = RequestBody.create(json, HttpConfiguration.APPLICATION_JSON);
        Request request = requestFactory.createAuthorizedRequest(
                        httpConfiguration.getBaseUrl() + ApiRoutes.userById(user.getId()))
                .put(body)
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
                                new RuntimeException("Failed to sync user " + user.getId() + " - HTTP " + response.code()));
                        return;
                    }

                    future.complete(null);
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
