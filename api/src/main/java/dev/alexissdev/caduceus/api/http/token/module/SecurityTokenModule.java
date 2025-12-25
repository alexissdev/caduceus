package dev.alexissdev.caduceus.api.http.token.module;

import dev.alexissdev.caduceus.api.http.request.factory.RequestFactory;
import dev.alexissdev.caduceus.api.http.token.SecurityToken;
import dev.alexissdev.caduceus.api.http.token.checker.SecurityTokenExpirationChecker;
import dev.alexissdev.caduceus.api.http.token.factory.SecurityTokenFactory;
import dev.alexissdev.caduceus.api.http.token.register.SecurityTokenRegister;
import dev.alexissdev.caduceus.api.http.token.updater.SecurityTokenUpdater;
import team.unnamed.inject.AbstractModule;

public class SecurityTokenModule
        extends AbstractModule {

    @Override
    protected void configure() {
        bind(SecurityToken.class).toInstance(SecurityToken.builder().build());
        bind(SecurityTokenExpirationChecker.class).singleton();
        bind(SecurityTokenUpdater.class).singleton();

        bind(SecurityTokenRegister.class).singleton();
        bind(SecurityTokenFactory.class).singleton();
        bind(RequestFactory.class).singleton();
    }
}
