package dev.alexissdev.caduceus.api.http.request;


import dev.alexissdev.caduceus.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@AllArgsConstructor
public class UpdateUserRequest {

    private final double coins;
    private final int gems;
    private final int kills;
    private final int deaths;
    private final int wins;
    private final int losses;

    /**
     * Creates an {@code UpdateUserRequest} instance using data extracted from the provided {@code User} object.
     * The method maps specific fields from the given user's economy and statistics to corresponding attributes
     * in the {@code UpdateUserRequest}.
     *
     * @param user the {@code User} object containing economy and statistic data to populate the {@code UpdateUserRequest}
     * @return a new {@code UpdateUserRequest} instance populated with data from the provided {@code User} object
     * @throws NullPointerException if the {@code user} argument is null
     */

    public static UpdateUserRequest fromUser(@NotNull User user) {
        return UpdateUserRequest.builder()
                .coins(user.getEconomy().getCoins().getValue())
                .gems(user.getEconomy().getGems().getValue())
                .kills(user.getStatistic().getKills().getValue())
                .deaths(user.getStatistic().getDeaths().getValue())
                .wins(user.getStatistic().getWins().getValue())
                .losses(user.getStatistic().getLosses().getValue())
                .build();
    }
}
