package dev.alexissdev.caduceus.api;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

/**
 * A module responsible for providing an {@link Executor} instance as a singleton
 * dependency. This is useful for managing multi-threaded task execution within
 * the application.
 * <p>
 * The module leverages a cached thread pool, created using {@link Executors#newCachedThreadPool()},
 * to provide a flexible and efficient mechanism for handling concurrent tasks.
 * <p>
 * This module is intended to be used with a dependency injection framework
 * that supports the {@link AbstractModule} class. It simplifies the creation
 * and management of {@link Executor} instances by centralizing their configuration.
 */

public class ExecutorModule
        extends AbstractModule {

    @Provides
    @Singleton
    public Executor proivdeExecutor() {
        return Executors.newCachedThreadPool();
    }

}