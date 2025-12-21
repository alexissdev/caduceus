package dev.alexissdev.caduceus.api.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a response returned after a successful authentication attempt.
 * This class contains the tokens required for maintaining and refreshing the user's session.
 */

@Data
@AllArgsConstructor
public class LoginResponse {

    private final String accessToken;
    private final String refreshToken;
}
