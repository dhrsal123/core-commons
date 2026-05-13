package io.cinema.config;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class RedisConfigTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(RedisConfig.class);

    @Test
    void shouldConfigureRedisCacheBeanWithCorrectSettings() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(RedisConfig.class);

            assertThat(context).hasBean("cacheConfiguration");

            RedisCacheConfiguration config = context.getBean(RedisCacheConfiguration.class);

            Duration ttl = config.getTtlFunction().getTimeToLive("anyCacheName", null);
            assertThat(ttl).isEqualTo(Duration.ofMinutes(60));

            assertThat(config.getAllowCacheNullValues()).isFalse();

            assertThat(config.getValueSerializationPair()).isNotNull();
        });
    }
}