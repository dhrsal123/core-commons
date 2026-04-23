package io.cinema.controller;

import io.cinema.domain.dto.BaseErrorDto;
import io.cinema.domain.enumerated.CinemaExceptionTypes;
import io.cinema.domain.exceptions.CinemaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorDto> handleException(Exception e) {

        var errorResponse = new BaseErrorDto(
                e.getMessage(),
                "UNKNOWN",
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseErrorDto> handleAccessDeniedException(AccessDeniedException e) {

        var errorResponse = new BaseErrorDto(
                e.getMessage(),
                CinemaExceptionTypes.FORBIDDEN.getCode(),
                CinemaExceptionTypes.FORBIDDEN.getHttpStatus()
        );

        return new ResponseEntity<>(
                errorResponse,
                CinemaExceptionTypes.FORBIDDEN.getHttpStatus()
        );
    }

    @ExceptionHandler(CinemaException.class)
    public ResponseEntity<BaseErrorDto> handleCinemaException(CinemaException e) {

        var exceptionType = e.getExceptionType();
        var errorResponse = new BaseErrorDto(
                e.getMessage(),
                exceptionType.getCode(),
                exceptionType.getHttpStatus()
        );

        return new ResponseEntity<>(errorResponse, exceptionType.getHttpStatus());
    }


}
