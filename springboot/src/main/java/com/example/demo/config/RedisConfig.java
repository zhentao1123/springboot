package com.example.demo.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
	
	@Bean
	JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(5);
		jedisPoolConfig.setMaxTotal(5);
//		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//		jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		jedisPoolConfig.setMinIdle(5);
		jedisPoolConfig.setTestOnCreate(true);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		jedisPoolConfig.setTestWhileIdle(true);
		return jedisPoolConfig;
	}
	
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("127.0.0.1");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.setPassword("123456");
		jedisConnectionFactory.setDatabase(0);
		jedisConnectionFactory.setUsePool(false);
		jedisConnectionFactory.setTimeout(2*1000);
		//jedisConnectionFactory.setPoolConfig();
		//jedisConnectionFactory.setClientName(clientName);
        return jedisConnectionFactory;
    }
	
	@Bean
    public RedisTemplate<String, Object> redisTemplateObject() throws Exception {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.afterPropertiesSet();
        //Set Serializer
        {
        	Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            jackson2JsonRedisSerializer.setObjectMapper(om);
            template.setValueSerializer(jackson2JsonRedisSerializer);
            template.setHashValueSerializer(jackson2JsonRedisSerializer);
            //template.setKeySerializer(template.getStringSerializer());
            //template.setHashKeySerializer(template.getHashKeySerializer());
        }
        return template;
    }	
}
