package dev.alexissdev.caduceus.api.http.exception;

import lombok.Getter;

/**
 * Represents an HTTP-specific exception that extends {@code RuntimeException}.
 * This exception is used to encapsulate HTTP status codes alongside exception messages,
 * providing additional context about HTTP errors.
 */

@Getter
public class HttpException
        extends RuntimeException {

    private final int status;

    public HttpException(int status) {
        super();
        this.status = status;
    }

    public HttpException(int status, String message) {
        super(message);
        this.status = status;
    }
}
