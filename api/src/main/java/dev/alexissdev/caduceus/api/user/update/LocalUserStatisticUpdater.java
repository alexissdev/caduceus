package dev.alexissdev.caduceus.api.user.update;

import dev.alexissdev.caduceus.api.statistic.Statistic;
import dev.alexissdev.caduceus.api.user.User;
import dev.alexissdev.storage.ModelService;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * The LocalUserStatisticUpdater class is responsible for updating user-specific
 * statistical data based on various actions such as incrementing, decrementing,
 * or setting specific statistics. This class provides functionality to resolve
 * and apply updates to user statistics in a thread-safe and consistent manner.
 * <p>
 * It implements the UserStatisticUpdater interface and uses dependency-injected
 * services for accessing user data and logging.
 */

public class LocalUserStatisticUpdater
        implements UserStatisticUpdater {

    private static final Map<Type, Function<User, Statistic>> STATISTIC_MAP = new EnumMap<>(Type.class);

    static {
        STATISTIC_MAP.put(Type.KILLS, user -> user.getStatistic().getKills());
        STATISTIC_MAP.put(Type.DEATHS, user -> user.getStatistic().getDeaths());
        STATISTIC_MAP.put(Type.WINS, user -> user.getStatistic().getWins());
        STATISTIC_MAP.put(Type.LOSSES, user -> user.getStatistic().getLosses());
    }

    @Inject
    private ModelService<User> userModelService;
    @Inject
    private Logger logger;

    @Override
    public void update(@NotNull String userId, @NotNull Type statisticType,
                       @NotNull Action actionType, int value) {
        User user = userModelService.findSync(userId);
        if (user == null) {
            logger.warning("User not found for id " + userId);
            return;
        }

        Function<User, Statistic> statResolver = STATISTIC_MAP.get(statisticType);
        if (statResolver == null) {
            throw new IllegalArgumentException("Statistic type not supported: " + statisticType);
        }

        Statistic stat = statResolver.apply(user);
        applyAction(stat, actionType, value);
    }

    /**
     * Applies the specified action to the given statistic using the provided value.
     *
     * @param stat   the statistic to be modified; must not be null
     * @param action the action to perform on the statistic, such as INCREMENT, DECREMENT, or SET; must not be null
     * @param value  the value to be used in the update operation; its meaning depends on the action:
     *               for INCREMENT/DECREMENT, it specifies the amount of change; for SET, it specifies the new value
     * @throws IllegalArgumentException if the given action is not supported
     */

    private void applyAction(Statistic stat, Action action, int value) {
        switch (action) {
            case INCREMENT:
                stat.add(value);
                break;
            case DECREMENT:
                stat.subtract(value);
                break;
            case SET:
                stat.setValue(value);
                break;
            default:
                throw new IllegalArgumentException("Action not supported");
        }
    }

}
