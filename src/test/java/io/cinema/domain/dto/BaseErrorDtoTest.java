package io.cinema.domain.dto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class BaseErrorDtoTest {

    @Test
    void shouldBuildCorrectly() {
        // act
        var err = new BaseErrorDto(
                "error-1",
                "ERR-01",
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        //assert
        assertThat(err)
                .extracting(
                        BaseErrorDto::message,
                        BaseErrorDto::code,
                        BaseErrorDto::status
                ).containsExactly(
                        "error-1",
                        "ERR-01",
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
    }

}