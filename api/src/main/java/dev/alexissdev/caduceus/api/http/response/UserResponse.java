package dev.alexissdev.caduceus.api.http.response;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the response for a user within the system, primarily used
 * to encapsulate user-related data that may be constructed or retrieved
 * from external sources, such as APIs or databases.
 * <p>
 * This class is immutable and provides details such as the user's unique
 * identifier, their selected username, language preference, and various
 * statistics including coins, gems, kills, deaths, wins, and losses.
 * <p>
 * Typical usage involves converting this object into a domain-specific
 * `User` object or other models for internal use.
 */

@Data
@Builder
public class UserResponse {

    private final String id;
    private final String username;
    private final String language;
    private final double coins;
    private final int gems;
    private final int kills;
    private final int deaths;
    private final int wins;
    private final int losses;
}
