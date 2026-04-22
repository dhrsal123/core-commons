package io.cinema.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AuditableEntityTest {

    @Test
    void shouldBuildCorrectly() {
        // act
        var entity = new AuditableEntity(
                LocalDateTime.of(2025, 10, 12, 12, 12, 12, 12),
                LocalDateTime.of(2025, 11, 12, 12, 12, 12, 12),
                "System",
                "System"
        );

        //assert
        assertThat(entity)
                .extracting(
                        AuditableEntity::getCreatedAt,
                        AuditableEntity::getUpdatedAt,
                        AuditableEntity::getCreatedBy,
                        AuditableEntity::getUpdatedBy
                ).containsExactly(
                        LocalDateTime.of(2025, 10, 12, 12, 12, 12, 12),
                        LocalDateTime.of(2025, 11, 12, 12, 12, 12, 12),
                        "System",
                        "System"
                );
    }


}