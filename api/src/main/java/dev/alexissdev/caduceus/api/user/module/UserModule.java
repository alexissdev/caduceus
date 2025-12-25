package dev.alexissdev.caduceus.api.user.module;

import dev.alexissdev.caduceus.api.user.User;
import dev.alexissdev.caduceus.api.user.loader.RemoteUserLoaderService;
import dev.alexissdev.caduceus.api.user.loader.UserLoaderService;
import dev.alexissdev.caduceus.api.user.sync.RemoteUserSyncService;
import dev.alexissdev.caduceus.api.user.sync.UserSyncService;
import dev.alexissdev.storage.ModelService;
import dev.alexissdev.storage.dist.LocalModelService;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Singleton;

public class UserModule
        extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserLoaderService.class).to(RemoteUserLoaderService.class).singleton();
        bind(UserSyncService.class).to(RemoteUserSyncService.class).singleton();
    }

    @Provides
    @Singleton
    public ModelService<User> provideModelService() {
        System.out.println("Creating model service...");
        return LocalModelService.concurrent();
    }

}
