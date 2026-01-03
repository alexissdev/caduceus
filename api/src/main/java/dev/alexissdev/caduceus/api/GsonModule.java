package dev.alexissdev.caduceus.api;

import com.google.gson.Gson;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Singleton;

/**
 * A module responsible for providing a {@link Gson} instance as a singleton
 * dependency. This is useful for managing JSON serialization and deserialization
 * within the application.
 * <p>
 * The module is designed to be used with a dependency injection framework
 * that supports the {@link AbstractModule} class. It enables the injectable
 * creation of a single shared {@link Gson} instance for consistent usage
 * across the application.
 * <p>
 * The provided {@link Gson} instance is configured with default settings.
 * Further customization of the {@link Gson} instance, if needed, should be
 * implemented separately.
 */

public class GsonModule
        extends AbstractModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }
}
