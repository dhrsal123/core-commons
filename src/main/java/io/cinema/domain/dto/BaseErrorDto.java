package io.cinema.domain.dto;


import org.springframework.http.HttpStatus;

public record BaseErrorDto(
        String message,
        String code,
        HttpStatus status
) {
}
