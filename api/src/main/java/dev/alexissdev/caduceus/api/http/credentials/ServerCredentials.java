package dev.alexissdev.caduceus.api.http.credentials;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents the credentials required to connect to a server.
 * This class holds the server identifier and the corresponding password.
 *
 * Instances of this class are immutable and encapsulate the server's
 * authentication details.
 */

@Data
@AllArgsConstructor
public class ServerCredentials {

    private final String serverId;
    private final String password;
}
