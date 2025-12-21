package dev.alexissdev.caduceus.api.http.interceptors;

import dev.alexissdev.caduceus.api.http.token.factory.SecurityTokenFactory;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class AuthInterceptor
        implements Interceptor {

    @Inject
    private SecurityTokenFactory tokenFactory;

    @Override
    public @NotNull Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        String token = tokenFactory.getToken();

        Request request = chain.request().newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();

        return chain.proceed(request);
    }
}
