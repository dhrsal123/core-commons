package io.cinema.domain.exceptions;

import io.cinema.domain.enumerated.CinemaExceptionTypes;
import lombok.Getter;

@Getter
public class CinemaException extends RuntimeException {
    private final CinemaExceptionTypes exceptionType;

    public CinemaException(String message, CinemaExceptionTypes exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public CinemaException(String message, Throwable cause, CinemaExceptionTypes exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }
}
