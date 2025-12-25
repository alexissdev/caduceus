package dev.alexissdev.caduceus.plugin.service;

import dev.alexissdev.caduceus.api.service.Service;

import javax.inject.Inject;
import java.util.Set;
import java.util.logging.Logger;

public class PluginLoaderService
        implements Service {

    @Inject
    private Set<Service> services;
    @Inject
    private Logger logger;

    @Override
    public void start() {
        logger.info("Starting services...");

        services.forEach(Service::start);
    }

    @Override
    public void stop() {
        logger.info("Stopping services...");
        services.forEach(Service::stop);
    }
}
