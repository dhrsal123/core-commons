package io.cinema.service;

import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("auditorAwareImpl")
public class AuditorAwareImpl implements ReactiveAuditorAware<String> {

    @Override
    public Mono<String> getCurrentAuditor() {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(context -> {

                    Authentication auth = context.getAuthentication();
                    if (auth != null && auth.isAuthenticated() && auth.getName() != null) {
                        return Mono.just(auth.getName());
                    }

                    return Mono.empty();
                });
    }
}
