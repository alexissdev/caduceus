package dev.alexissdev.caduceus.api.user.economy;

import dev.alexissdev.caduceus.api.statistic.Statistic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserEconomy {

    private final Statistic coins;
    private final Statistic gems;

    /**
     * Creates and returns a new instance of {@code UserEconomy} with its initial state.
     * The initial state contains both {@code coins} and {@code gems} set to their
     * respective initial values as defined in the {@code Statistic.initial()} method.
     *
     * @return a new {@code UserEconomy} instance with initialized {@code coins} and {@code gems}.
     */

    public static UserEconomy initial() {
        return UserEconomy.builder().coins(Statistic.initial()).gems(Statistic.initial()).build();
    }
}
