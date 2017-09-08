package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
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
        RedisTemplate<String, Object> redisTemplateObject = new RedisTemplate<String, Object>();
        redisTemplateObject.setConnectionFactory(jedisConnectionFactory());
        setSerializer(redisTemplateObject);
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }
	
	private void setSerializer(RedisTemplate<String, Object> template) 
	{
		//Object
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
        
        template.setKeySerializer(template.getStringSerializer());
        template.setHashKeySerializer(template.getHashKeySerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        
        //在使用String的数据结构的时候使用这个来更改序列化方式
        /*
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        */
    }
	
}
