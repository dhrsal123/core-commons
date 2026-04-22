package io.cinema.service;

import io.cinema.factory.MockFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(MockitoExtension.class)
class AuditorAwareImplTest {
    @InjectMocks
    private AuditorAwareImpl auditorAware;

    @Test
    void shouldReturnCurrentAuditor() {
        // arrange
        var jwt = MockFactory.buildJwt();
        var auth = new JwtAuthenticationToken(jwt);
        auth.setAuthenticated(true);
        var securityContext = new SecurityContextImpl(auth);


        try (var contextHolder = Mockito.mockStatic(ReactiveSecurityContextHolder.class)) {
            contextHolder.when(ReactiveSecurityContextHolder::getContext).thenReturn(Mono.just(securityContext));
            // act
            var response = auditorAware.getCurrentAuditor();

            // assert
            assertEquals("Michael", response.block());
        }
    }

    @ParameterizedTest
    @MethodSource("provideEmptyOrNullAuth")
    void shouldReturnEmptyAuditorWhenAuthIsNotPresentOrInvalid(JwtAuthenticationToken auth) {
        // arrange
        var securityContext = new SecurityContextImpl(auth);

        try (var contextHolder = Mockito.mockStatic(ReactiveSecurityContextHolder.class)) {
            contextHolder.when(ReactiveSecurityContextHolder::getContext).thenReturn(Mono.just(securityContext));
            // act
            var response = auditorAware.getCurrentAuditor();

            // assert
            assertNull(response.block());
        }
    }

    //private methods
    private static Stream<Arguments> provideEmptyOrNullAuth() {
        return MockFactory.buildInvalidAuths();
    }

}