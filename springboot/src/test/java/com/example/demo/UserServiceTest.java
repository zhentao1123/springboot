package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.List;

import lombok.Setter;

import org.apache.juli.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {
	
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;
	
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@Test
	@Rollback(value = false)
	public void testAdd(){
		try {
			User user = new User();
			user.setName("Jack");
			user.setAge(20);
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1988-01-01"));
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll(){
		try {
			List<User> list = userService.getUserList();
			log.info(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGet(){
		try {
			User user = userService.getUser(2l);
			log.info(user.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	//@Rollback(value = false)
	public void testUpdate(){
		try {
			User user = userService.getUser(2l);
			user.setAge(18);
			userService.editUser(2l, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(value = false)
	public void testDelete(){
		try {
			userService.removeUser(5l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
