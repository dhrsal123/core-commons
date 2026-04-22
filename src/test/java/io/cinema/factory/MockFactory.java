package io.cinema.factory;

import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@UtilityClass
public class MockFactory {

    public static Jwt buildJwt() {
        return buildBaseJwt()
                .header("something", "hey")
                .claim("realm_access", Map.of("roles", List.of("admin")))
                .claim(
                        "resource_access",
                        Map.of(
                                "resource", Map.of("roles", List.of("ROLE_employee")))
                )
                .subject("Michael")
                .build();

    }


    public static Jwt buildNoNameJwt() {
        return buildBaseJwt()
                .header("something", "hey")
                .claim("realm_access", Map.of("roles", List.of("admin")))
                .claim(
                        "resource_access",
                        Map.of(
                                "resource", Map.of("roles", List.of("ROLE_employee")))
                )
                .build();

    }

    public static Jwt.Builder buildBaseJwt() {
        return Jwt.withTokenValue("eyJhbGciOiJIUzI1NiJ9")
                .audience(List.of("audience-test"));

    }

    public static Stream<Arguments> buildInvalidAuths() {
        var jwt = MockFactory.buildJwt();
        var noAuth = new JwtAuthenticationToken(jwt);

        var noNameJwt = MockFactory.buildNoNameJwt();
        var noName = new JwtAuthenticationToken(noNameJwt);
        noName.setAuthenticated(true);

        return Stream.of(
                Arguments.of(noAuth),
                Arguments.of((JwtAuthenticationToken) null),
                Arguments.of(noName)
        );
    }
}
