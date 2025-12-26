package dev.alexissdev.caduceus.api.user.creator;

import com.google.gson.Gson;
import dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration;
import dev.alexissdev.caduceus.api.http.exception.HttpException;
import dev.alexissdev.caduceus.api.http.request.CreateUserRequest;
import dev.alexissdev.caduceus.api.http.request.factory.RequestFactory;
import dev.alexissdev.caduceus.api.http.response.UserResponse;
import dev.alexissdev.caduceus.api.http.routes.ApiRoutes;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * RemoteUserCreatorService is an implementation of the UserCreatorService interface
 * that facilitates the creation of remote users by interacting with a remote HTTP API.
 * This class uses dependency injection to manage its dependencies and relies on OkHttp
 * for HTTP communication.
 * <p>
 * The create method sends a POST request to the specified API endpoint defined
 * within the HttpConfiguration. The request includes user details, such as unique
 * ID and username, in a JSON format. It also handles authentication by adding
 * an authorization header with a security token obtained from the SecurityTokenFactory.
 * <p>
 * Internally, the method uses asynchronous execution via CompletableFuture, allowing
 * the caller to handle the response or any exceptions asynchronously.
 * <p>
 * Dependencies:
 * - OkHttpClient: For sending HTTP requests.
 * - Gson: For JSON serialization and deserialization.
 * - HttpConfiguration: Provides HTTP connection details, including the base URL and media type.
 * - SecurityTokenFactory: Supplies security tokens for secure API interactions.
 * <p>
 * Error Handling:
 * - If the HTTP request fails or the response indicates an error, the method completes
 * the returned CompletableFuture with an exception.
 * - If the response cannot be parsed into a UserResponse, an exception is also propagated.
 * <p>
 * Thread Safety:
 * - The thread safety of this class depends on the thread safety of its dependencies,
 * particularly OkHttpClient and SecurityTokenFactory.
 */

public class RemoteUserCreatorService
        implements UserCreatorService {

    @Inject
    private OkHttpClient client;
    @Inject
    private Gson gson;
    @Inject
    private HttpConfiguration httpConfiguration;
    @Inject
    private RequestFactory requestFactory;

    @Override
    public CompletableFuture<UserResponse> create(@NotNull String id, @NotNull String username) {
        CompletableFuture<UserResponse> future = new CompletableFuture<>();
        String json = gson.toJson(CreateUserRequest.builder().id(id).username(username).build());

        RequestBody body = RequestBody.create(json, HttpConfiguration.APPLICATION_JSON);
        Request request = requestFactory.createAuthorizedRequest(
                        httpConfiguration.getBaseUrl() + ApiRoutes.USERS)
                .post(body)
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
                        future.completeExceptionally(new HttpException(response.code()));
                        return;
                    }

                    String responseJson = response.body().string();
                    UserResponse userResponse = gson.fromJson(responseJson, UserResponse.class);

                    future.complete(userResponse);
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
