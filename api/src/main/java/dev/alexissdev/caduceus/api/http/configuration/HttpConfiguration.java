package dev.alexissdev.caduceus.api.http.configuration;

import dev.alexissdev.caduceus.api.http.credentials.ServerCredentials;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import okhttp3.MediaType;

@Data
@AllArgsConstructor
@Builder
public class HttpConfiguration {

    public static final MediaType APPLICATION_JSON = MediaType.get("application/json");

    private final String baseUrl;
    private final ServerCredentials serverCredentials;
}
