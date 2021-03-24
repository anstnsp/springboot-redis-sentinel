package com.example.redis;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;


@Configuration
@EnableRedisHttpSession
public class RedisConfig   {

    @Value("${spring.redis.sentinel.master}")
    private String master; 
    @Value("${spring.redis.password}")
    private String password; 
    @Value("${spring.redis.sentinel.nodes}")
    private List<String> sentinels;  


    // @Value("${spring.redis.sentinel.host}")
    // private String sentinelHost;
    // @Value("${spring.redis.sentinel.port1}")
    // private int sentinelPort1; 
    // @Value("${spring.redis.sentinel.port2}")
    // private int sentinelPort2;
    // @Value("${spring.redis.sentinel.port3}")
    // private int sentinelPort3;
    // @Value("${spring.redis.password}")
    // private String password; 


    //아래 redisConnectionFactory팩토리는 sentinel 구성으로 되어 있음. 
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // redis : standalone 일시 아래와 같이 작성
        // LettuceConnectionFactory conn = new LettuceConnectionFactory("192.168.1.56", 6379); 
        // conn.setPassword("mypassword"); 
        // return conn; 
        // return new LettuceConnectionFactory("192.168.1.245", 6379).setPassword("mypassword");;
        // Sentinel 구성 config 
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration().master(master)
            
            .sentinel(sentinels.get(0).split(":")[0], Integer.parseInt(sentinels.get(0).split(":")[1])) 
            .sentinel(sentinels.get(1).split(":")[0], Integer.parseInt(sentinels.get(1).split(":")[1])) 
            .sentinel(sentinels.get(2).split(":")[0],Integer.parseInt(sentinels.get(2).split(":")[1]));
            // .sentinel(sentinelHost, sentinelPort2)
            // .sentinel(sentinelHost, sentinelPort3); 
        
        redisSentinelConfiguration.setPassword(password); 
        redisSentinelConfiguration.setSentinelPassword("mypassword");
        return new LettuceConnectionFactory(redisSentinelConfiguration); 
        
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>(); 
        redisTemplate.setConnectionFactory(this.redisConnectionFactory()); 
        redisTemplate.setKeySerializer(new StringRedisSerializer()); 
        redisTemplate.setHashKeySerializer(new StringRedisSerializer()); 
        redisTemplate.setHashValueSerializer(new StringRedisSerializer()); 
        redisTemplate.setValueSerializer(new StringRedisSerializer()); 
        
        return redisTemplate; 
    }

}

