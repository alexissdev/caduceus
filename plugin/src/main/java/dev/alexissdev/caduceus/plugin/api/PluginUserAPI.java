package dev.alexissdev.caduceus.plugin.api;

import dev.alexissdev.caduceus.api.UserAPI;
import dev.alexissdev.caduceus.api.user.User;
import dev.alexissdev.storage.ModelService;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.Optional;

public class PluginUserAPI
        implements UserAPI {

    @Inject
    private ModelService<User> userModelService;

    @Override
    public Optional<User> getCachedUser(@NotNull String userId) {
        return Optional.ofNullable(userModelService.findSync(userId));
    }

    @Override
    public void updateUser(@NotNull User user) {
        User cachedUser = userModelService.findSync(user.getId());
        if (cachedUser != null) {
            userModelService.deleteSync(cachedUser);
        }

        userModelService.saveSync(user);
    }
}
