package io.cinema.domain.enumerated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CinemaExceptionTypes {

    BAD_REQUEST("CINEMA-001", "Petición Invalida.", HttpStatus.BAD_REQUEST),

    TECHNICAL_ERROR("CINEMA-002", "Error Interno en la API.", HttpStatus.INTERNAL_SERVER_ERROR),

    BUSINESS_ERROR("CINEMA-003", "Business Error.", HttpStatus.CONFLICT),

    FORBIDDEN("CINEMA-004", "Forbidden.", HttpStatus.FORBIDDEN),

    UNAUTHORIZED("CINEMA-005", "Unauthorized.", HttpStatus.UNAUTHORIZED),

    USER_ALREADY_EXIST("CINEMA-006", "El usuario ya existe.", HttpStatus.PRECONDITION_FAILED),

    NOT_FOUND("CINEMA-007", "Not Found.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
