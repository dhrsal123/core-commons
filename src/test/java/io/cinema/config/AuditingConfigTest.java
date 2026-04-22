package io.cinema.config;

import io.cinema.service.AuditorAwareImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AuditingConfigTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(AuditingConfig.class)
            .withBean(R2dbcMappingContext.class, () -> mock(R2dbcMappingContext.class));

    @Test
    void shouldRegisterAuditorProviderBean() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(AuditingConfig.class);

            assertThat(context).hasBean("auditorProvider");

            ReactiveAuditorAware<?> auditorAwareBean = context.getBean("auditorProvider", ReactiveAuditorAware.class);
            assertThat(auditorAwareBean).isInstanceOf(AuditorAwareImpl.class);
        });
    }
}