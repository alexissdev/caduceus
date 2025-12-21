package dev.alexissdev.caduceus.api.http;

import dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration;
import dev.alexissdev.caduceus.api.http.interceptors.AuthInterceptor;
import dev.alexissdev.caduceus.api.http.token.module.SecurityTokenModule;
import okhttp3.OkHttpClient;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * The HttpModule class is a dependency injection module used for configuring
 * and providing HTTP-related components.
 * <p>
 * This module extends the AbstractModule class and leverages dependency injection
 * to supply an instance of OkHttpClient as a singleton to the application.
 * <p>
 * It defines how HTTP-related dependencies should be constructed and managed,
 * ensuring centralized control over such components.
 */

public class HttpModule
        extends AbstractModule {

    private final HttpConfiguration httpConfiguration;

    public HttpModule(HttpConfiguration httpConfiguration) {
        this.httpConfiguration = httpConfiguration;
    }

    @Override
    protected void configure() {
        bind(HttpConfiguration.class).toInstance(httpConfiguration);
        install(new SecurityTokenModule());
    }

    @Provides
    @Singleton
    @Named("no-auth")
    public OkHttpClient provideNoAuthHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient(AuthInterceptor authInterceptor) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(authInterceptor)
                .build();
    }

}
