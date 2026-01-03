package dev.alexissdev.caduceus.api.http.token.checker;

import com.google.gson.Gson;

import javax.inject.Inject;
import java.util.Base64;
import java.util.Map;

/**
 * The SecurityTokenExpirationChecker class is responsible for validating the expiration
 * of security tokens. This is accomplished by decoding the token's payload to extract
 * the "exp" (expiration) field, which is expected to be a UNIX timestamp indicating
 * when the token expires. The class uses Gson for JSON processing and handles any
 * exceptions that arise during token validation.
 * <p>
 * The expiration check logic compares the current system time to the extracted
 * expiration timestamp, returning true if the token has expired or if an error
 * occurs during the process. This helps ensure robust handling of malformed
 * or invalid tokens.
 * <p>
 * Dependencies such as Gson are expected to be injected by a dependency injection framework.
 */

public class SecurityTokenExpirationChecker {

    @Inject
    private Gson gson;

    /**
     * Checks if the provided security token has expired.
     * The method decodes the token, extracts the payload, and parses it as JSON
     * to retrieve the "exp" (expiration) field. The expiration field is expected
     * to be a UNIX timestamp (in seconds), which is converted to milliseconds for comparison.
     *
     * @param token the security token to be checked for expiration
     * @return {@code true} if the token has expired or if an error occurs during the
     *         process, {@code false} otherwise
     */

    public boolean check(String token) {
        try {
            String payload = token.split("\\.")[1];
            String json = new String(Base64.getUrlDecoder().decode(payload));
            Map<?, ?> map = gson.fromJson(json, Map.class);
            long exp = ((Number) map.get("exp")).longValue() * 1000;
            return System.currentTimeMillis() > exp;
        } catch (Exception e) {
            return true;
        }
    }
}
