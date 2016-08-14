package com.concert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


/*
  Uncomment below lines if you want to use redis.
 */
//@Configuration
//@EnableAutoConfiguration
public class RedisConfiguration {
    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        JedisConnectionFactory ob = new JedisConnectionFactory(poolConfig);
        ob.setUsePool(true);
        ob.setHostName("localhost");
        ob.setPort(6379);
        return ob;
    }

    @Bean
    public StringRedisTemplate  stringRedisTemplate(){
        StringRedisTemplate template = new StringRedisTemplate(jedisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.setHashKeySerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return new StringRedisTemplate(jedisConnectionFactory());
    }
}
