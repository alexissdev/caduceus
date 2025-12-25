package dev.alexissdev.caduceus.api.http.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents an HTTP-specific exception that extends {@code RuntimeException}.
 * This exception is used to encapsulate HTTP status codes alongside exception messages,
 * providing additional context about HTTP errors.
 */

@Getter
@AllArgsConstructor
public class HttpException
        extends RuntimeException {

    private final int status;
}
