package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.User;
import com.example.demo.exception.ServiceException;

public interface UserService {
	
	public List<User> getUserList() throws ServiceException;
	
	public User getUser(Long id) throws ServiceException;
	
	public void addUser(User user) throws ServiceException;
	
	public void editUser(Long id, User user) throws ServiceException;
	
	public void removeUser(Long id) throws ServiceException;
}
