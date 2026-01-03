package dev.alexissdev.caduceus.api.http.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {

    private final String id;
    private final String username;
}
