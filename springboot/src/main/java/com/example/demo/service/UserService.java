package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.User;
import com.example.demo.exception.MyException;

public interface UserService {
	
	public List<User> getUserList() throws MyException;
	
	public User getUser(Long id) throws MyException;
	
	public void addUser(User user) throws MyException;
	
	public void editUser(Long id, User user) throws MyException;
	
	public void removeUser(Long id) throws MyException;
}
