package dev.alexissdev.caduceus.api.user;

import dev.alexissdev.caduceus.api.http.response.UserResponse;
import dev.alexissdev.caduceus.api.statistic.Statistic;
import dev.alexissdev.caduceus.api.user.economy.UserEconomy;
import dev.alexissdev.caduceus.api.user.statistic.UserStatistic;
import dev.alexissdev.storage.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User
        implements Model {

    public static final String DEFAULT_LANGUAGE = "en";

    private final String id;
    private final String username;
    private final UserEconomy economy;
    private final UserStatistic statistic;
    private String language;

    public static User fromResponse(UserResponse response) {
        UserEconomy economy = UserEconomy.builder()
                .coins(Statistic.builder().value((int) Math.ceil(response.getCoins())).build())
                .gems(Statistic.builder().value(response.getGems()).build())
                .build();

        UserStatistic statistic = UserStatistic.builder()
                .kills(Statistic.builder().value(response.getKills()).build())
                .deaths(Statistic.builder().value(response.getDeaths()).build())
                .wins(Statistic.builder().value(response.getWins()).build())
                .losses(Statistic.builder().value(response.getLosses()).build())
                .build();

        return User.builder()
                .id(response.getId())
                .username(response.getUsername())
                .economy(economy)
                .statistic(statistic)
                .language(response.getLanguage())
                .build();
    }
}
