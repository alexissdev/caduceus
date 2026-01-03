package dev.alexissdev.caduceus.api.user.update;

import org.jetbrains.annotations.NotNull;

public interface UserStatisticUpdater {

    /**
     * Represents the various types of statistics that can be tracked or updated for a user.
     * Each value corresponds to a specific metric associated with user performance or activity.
     */

    enum Type {
        KILLS,
        DEATHS,
        WINS,
        LOSSES
    }

    /**
     * Defines the actions that can be performed on a user's statistic.
     * Each action corresponds to a specific modification or operation
     * applied to a tracked statistic.
     * <p>
     * INCREMENT - Increases the value of the statistic.
     * DECREMENT - Decreases the value of the statistic.
     * SET - Assigns a specific value to the statistic.
     */

    enum Action {
        INCREMENT,
        DECREMENT,
        SET
    }

    /**
     * Updates the specified statistic for a user based on the given action and value.
     *
     * @param userId the unique identifier of the user whose statistic is being updated; must not be null
     * @param statisticType the type of statistic to be updated, such as KILLS, DEATHS, WINS, or LOSSES; must not be null
     * @param actionType the action to perform on the statistic, such as INCREMENT, DECREMENT, or SET; must not be null
     * @param value the value to be used in the update operation; its meaning depends on the action type:
     *              for INCREMENT/DECREMENT, it specifies the amount of change; for SET, it specifies the new value
     */

    void update(
            @NotNull String userId,
            @NotNull Type statisticType,
            @NotNull Action actionType,
            int value
    );

}
