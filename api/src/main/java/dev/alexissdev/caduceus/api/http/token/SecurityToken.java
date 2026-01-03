package dev.alexissdev.caduceus.api.http.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SecurityToken {

    private String token;
    private String refreshToken;
}
