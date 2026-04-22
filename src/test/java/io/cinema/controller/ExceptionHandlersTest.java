package io.cinema.controller;

import io.cinema.domain.dto.BaseErrorDto;
import io.cinema.domain.enumerated.CinemaExceptionTypes;
import io.cinema.domain.exceptions.CinemaException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExceptionHandlersTest {

    private final ExceptionHandlers handler = new ExceptionHandlers();

    @Test
    void shouldHandleGenericException() {
        // Arrange
        Exception ex = new Exception("System failure");

        // Act
        ResponseEntity<BaseErrorDto> response = handler.handleException(ex);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("System failure", response.getBody().message());
        assertEquals("UNKNOWN", response.getBody().code());
    }

    @Test
    void shouldHandleCinemaException() {
        // Arrange
        CinemaException ex = new CinemaException("User already exists", CinemaExceptionTypes.USER_ALREADY_EXIST);

        // Act
        ResponseEntity<BaseErrorDto> response = handler.handleCinemaException(ex);

        // Assert
        assertEquals(HttpStatus.PRECONDITION_FAILED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("User already exists", response.getBody().message());
    }
}