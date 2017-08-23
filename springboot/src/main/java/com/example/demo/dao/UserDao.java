package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.User;

public interface UserDao {
	
	public List<User> getUserList() throws Exception;
	
	public User getUser(Long id) throws Exception;
	
	public void createUser(User user) throws Exception;
	
	public void updateUser(Long id, User user) throws Exception;
	
	public void deleteUser(Long id) throws Exception;
}
