package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.dao.mongodb.UserRepository;
import com.example.demo.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MongodbTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	@Rollback(false)
	public void test(){
		User user = new User();
		user.init4test();
		
		userRepository.delete(user.getId());
		
		userRepository.insert(user);

		User u1 = userRepository.findById(user.getId());
		u1.setAge(21);
		
		userRepository.save(u1);
		
		Assert.assertEquals(user.getId(), u1.getId());
		
		User u2 = userRepository.findById(user.getId());
		
		Assert.assertEquals(u2.getAge(), new Integer(21));
	}
}
