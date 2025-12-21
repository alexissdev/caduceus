package dev.alexissdev.caduceus.api;

import dev.alexissdev.caduceus.api.http.HttpModule;
import dev.alexissdev.caduceus.api.http.configuration.HttpConfiguration;
import team.unnamed.inject.AbstractModule;


public class APIModule
        extends AbstractModule {

    /**
     * Encapsulates the configuration settings required for HTTP communication within the application.
     * <p>
     * This instance of {@link HttpConfiguration} is supplied to the {@link APIModule}, enabling
     * the provision and initialization of HTTP-related components, such as the HTTP client,
     * and ensuring consistent configuration across the application.
     * <p>
     * It includes properties like the base URL for the HTTP server and credentials for authentication,
     * facilitating secure and centralized management of HTTP communication settings.
     * <p>
     * Used within the dependency injection setup to bind and install HTTP-related modules, such
     * as {@link HttpModule}, and seamlessly integrates with other modules, like {@link SecurityTokenModule},
     * for enhanced functionality.
     */
    private final HttpConfiguration httpConfiguration;

    public APIModule(HttpConfiguration httpConfiguration) {
        this.httpConfiguration = httpConfiguration;
    }

    @Override
    protected void configure() {
        install(new ExecutorModule());
        install(new GsonModule());
        install(new HttpModule(httpConfiguration));
    }
}
