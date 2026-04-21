package io.cinema.domain.enumerated;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CinemaExceptionTypesTest {

    @Test
    void shouldVerifyEnumMappings() {
        CinemaExceptionTypes type = CinemaExceptionTypes.USER_ALREADY_EXIST;

        assertThat(type.getCode()).isEqualTo("CINEMA-006");
        assertThat(type.getHttpStatus()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
        assertThat(type.getMessage()).isEqualTo("El usuario ya existe.");
    }

    @Test
    void shouldNotHaveNullFieldsInEnums() {
        for (CinemaExceptionTypes type : CinemaExceptionTypes.values()) {
            assertThat(type.getCode()).isNotBlank();
            assertThat(type.getMessage()).isNotBlank();
            assertThat(type.getHttpStatus()).isNotNull();
        }
    }
}