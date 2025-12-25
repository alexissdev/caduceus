package dev.alexissdev.caduceus.api.user.statistic;

import dev.alexissdev.caduceus.api.statistic.Statistic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserStatistic {

    private final Statistic kills;
    private final Statistic deaths;
    private final Statistic wins;
    private final Statistic losses;

    /**
     * Creates and returns a new instance of UserStatistic with initialized default values.
     * Each field (kills, deaths, wins, losses) is assigned a new Statistic object
     * initialized with default values via the Statistic.initial() method.
     *
     * @return a new UserStatistic instance with all statistics initialized to default values
     */

    public static UserStatistic initial() {
        return UserStatistic.builder().kills(Statistic.initial()).deaths(Statistic.initial()).wins(Statistic.initial()).losses(Statistic.initial()).build();
    }

}
