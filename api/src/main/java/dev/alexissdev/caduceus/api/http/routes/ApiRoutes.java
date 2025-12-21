package dev.alexissdev.caduceus.api.http.routes;

import dev.alexisdev.validate.Validate;

/**
 * The ApiRoutes class provides a centralized location for defining
 * API endpoint routes used within the application. It includes
 * constants for base API paths and utility methods for constructing
 * specific endpoint routes dynamically.
 * <p>
 * This class is designed as a utility class and cannot be instantiated.
 */
public class ApiRoutes {

    private ApiRoutes() {
        throw new UnsupportedOperationException("Utility class, cannot be instantiated");
    }

    public static final String API_V1 = "/api/v1";
    public static final String USERS = API_V1 + "/users";
    public static final String AUTH = API_V1 + "/auth";
    public static final String AUTH_LOGIN = AUTH + "/login";
    public static final String AUTH_REFRESH = AUTH + "/refresh";

    /**
     * Constructs the API endpoint URL for retrieving a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return The complete API endpoint URL for accessing the specified user's information.
     */

    public static String userById(String id) {
        Validate.notNull(id, "id cannot be null");
        return USERS + "/" + id;
    }
}
