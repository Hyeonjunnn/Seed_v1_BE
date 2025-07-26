package com.hyeonjunnn.seed_v1_be.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.password}")
    private String password;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // standalone 구성에 호스트, 포트, 비밀번호를 설정
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration(host, port);
        redisConf.setPassword(password);

        // LettuceConnectionFactory 에 구성 정보 전달
        return new LettuceConnectionFactory(redisConf); // -> 비동기 및 논블로킹 방식으로 효율적
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // Key, Value 직렬화 설정
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); //복잡한 문자열

        return redisTemplate;
    }


    //Redis 메시지 리스너 컨테이너 설정: 특정 채널("notification")을 구독하도록 구성
//    @Bean
//    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
//                                                        RedisSubscriber redisSubscriber) {
//
//
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//
//        // 로그 찍기 - 채널 등록 시점
//        log.info("✅ Redis 리스너 등록됨 - 채널: notificationChannel");
//
//        // commentNotification 채널만 구독
//        container.addMessageListener(
//                (message, pattern) -> {
//                    String channel = new String(message.getChannel());
//                    String body = new String(message.getBody());
//                    log.info("📥 [RedisListener] 수신된 채널: {}, 메시지: {}", channel, body);
//                    redisSubscriber.onMessage(channel, body);
//                },
//                new PatternTopic("notificationChannel")
//        );
//
//        return container;
//    }
}