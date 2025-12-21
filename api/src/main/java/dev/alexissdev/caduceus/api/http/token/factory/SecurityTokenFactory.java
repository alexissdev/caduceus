package dev.alexissdev.caduceus.api.http.token.factory;

import dev.alexissdev.caduceus.api.http.token.SecurityToken;
import dev.alexissdev.caduceus.api.http.token.checker.SecurityTokenExpirationChecker;
import dev.alexissdev.caduceus.api.http.token.register.SecurityTokenRegister;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Factory responsible for providing security tokens for authentication purposes.
 * This class manages the lifecycle of tokens by validating their expiration
 * and refreshing them when necessary, ensuring the application always has
 * a valid token for secured interactions with external systems.
 * <p>
 * The `getToken` method handles the retrieval of a valid token. It checks if the
 * current token is null or expired, and if so, triggers the registration process
 * using the SecurityTokenRegister instance. The method returns a valid token
 * after ensuring its availability.
 * <p>
 * Dependencies for this class are expected to be injected by a dependency
 * injection framework and include:
 * - SecurityTokenExpirationChecker: Determines if a token has expired.
 * - SecurityTokenRegister: Handles the process of acquiring or refreshing security tokens.
 * - SecurityToken: Holds the current security and refresh tokens.
 */

public class SecurityTokenFactory {

    @Inject
    private SecurityTokenExpirationChecker expirationChecker;
    @Inject
    private SecurityTokenRegister register;
    @Inject
    private SecurityToken securityToken;

    /**
     * Retrieves a valid security token for authentication purposes.
     * This method checks the current security token's validity. If the token is either null
     * or expired, it initiates the registration process to acquire a new token.
     *
     * @return the valid security token after ensuring its availability
     * @throws IOException if an I/O error occurs during token registration
     */

    public String getToken() throws IOException {
        String accessToken = securityToken.getToken();
        if (accessToken == null || expirationChecker.check(accessToken)) {
            register.register();
        }

        return securityToken.getToken();
    }
}
