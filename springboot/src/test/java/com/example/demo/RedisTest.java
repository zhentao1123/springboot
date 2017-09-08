package com.example.demo;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RedisTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Test
	@Rollback(false)
	public void test(){
		//logger.info(stringRedisTemplate.getConnectionFactory().toString());
		
		//String
		stringRedisTemplate.delete("s1");
		stringRedisTemplate.opsForValue().set("s1", "v1");
		stringRedisTemplate.opsForValue().set("s1", "v2");
		Assert.assertEquals("v2", stringRedisTemplate.opsForValue().get("s1"));
		
		//List
		stringRedisTemplate.delete("l1");
		stringRedisTemplate.opsForList().rightPush("l1", "v1");
		stringRedisTemplate.opsForList().rightPush("l1", "v2");
		stringRedisTemplate.opsForList().rightPush("l1", "v3");
		stringRedisTemplate.opsForList().set("l1", 0, "v11");
		stringRedisTemplate.opsForList().set("l1", 1, "v22");
		Assert.assertEquals("v11", stringRedisTemplate.opsForList().index("l1", 0));
		Assert.assertEquals("v22", stringRedisTemplate.opsForList().index("l1", 1));
		
		//User
		User user = new User(1l, "Jack", 11, new Date());
		redisTemplate.delete("user1");
		redisTemplate.opsForValue().set("user1", user);
		//Assert.assertEquals(1, redisTemplate.opsForValue().get("s2"));
	}
}
