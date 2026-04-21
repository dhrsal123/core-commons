package io.cinema.domain.exceptions;


import io.cinema.domain.enumerated.CinemaExceptionTypes;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CinemaExceptionTest {

    @Test
    void shouldCreateExceptionWithType() {
        String message = "Custom error message";
        CinemaExceptionTypes type = CinemaExceptionTypes.BAD_REQUEST;

        CinemaException exception = new CinemaException(message, type);

        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getExceptionType()).isEqualTo(type);
        assertThat(exception.getExceptionType().getCode()).isEqualTo("CINEMA-001");
    }

    @Test
    void shouldRetainThrowableCause() {
        RuntimeException cause = new RuntimeException("Original cause");
        CinemaException exception = new CinemaException("New message", cause, CinemaExceptionTypes.TECHNICAL_ERROR);

        assertThat(exception.getCause()).isEqualTo(cause);
        assertThat(exception.getExceptionType()).isEqualTo(CinemaExceptionTypes.TECHNICAL_ERROR);
    }
}