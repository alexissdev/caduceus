package dev.alexissdev.caduceus.plugin.translation;

import dev.alexissdev.caduceus.api.user.User;
import dev.alexissdev.storage.ModelService;
import me.yushust.message.MessageHandler;
import me.yushust.message.MessageProvider;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Singleton;

import static dev.alexissdev.caduceus.api.user.User.DEFAULT_LANGUAGE;

public class TranslationModule
        extends AbstractModule {

    @Provides
    @Singleton
    public MessageHandler provideMessageHandler(Plugin plugin, ModelService<User> userModelService) {
        MessageSource source = MessageSourceDecorator
                .decorate(BukkitMessageAdapt.newYamlSource(plugin, "lang_%lang%.yml"))
                .addFallbackLanguage(DEFAULT_LANGUAGE)
                .get();

        MessageProvider messageProvider = MessageProvider.create(
                source,
                config -> {
                    config.specify(Player.class)
                            .setLinguist(player -> {
                                User possibleUser = userModelService.findSync(player.getUniqueId().toString());
                                if (possibleUser == null) {
                                    return DEFAULT_LANGUAGE;
                                }

                                return possibleUser.getLanguage();
                            })
                            .setMessageSender((player, mode, message) -> player.sendMessage(message));

                    config.specify(CommandSender.class)
                            .setLinguist(ignored -> DEFAULT_LANGUAGE)
                            .setMessageSender((sender, mode, message) -> sender.sendMessage(message));

                    config.addInterceptor(string -> ChatColor.translateAlternateColorCodes('&', string));
                }
        );

        return MessageHandler.of(messageProvider);
    }
}
