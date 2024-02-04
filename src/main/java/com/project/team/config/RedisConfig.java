package com.project.team.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean // RedisConnectionFactory  = Redis 데이터베이스와의 연결을 생성하는 팩토리를 설정
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() { // Redis 데이터 조작을 위한 템플릿 클래스이다. 이빈은 Redis 에 데이터를 저장하고 읽는 등의 작업에 사용된다
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>(); // 바이트 배열을 키와 값의 데이터 형식으로 사용하겠다는것을 나타냄
        redisTemplate.setConnectionFactory(redisConnectionFactory()); // RedisTemplate에 RedisConnetionFactory를 설정하여 커넥션을 제공
        return redisTemplate;
    }
}