package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.exception.MyException;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getUserList() throws MyException{
		try {
			return userDao.getUserList();
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
	}
	
	@Override
	public User getUser(Long id) throws MyException{
		try {
			return userDao.getUser(id);
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED)//默认只有捕捉到RunTimeException才会回滚
	public void addUser(User user) throws MyException{
		try {
			userDao.createUser(user);
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED)
	public void editUser(Long id, User user) throws MyException{
		try {
			userDao.updateUser(id, user);
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED)
	public void removeUser(Long id) throws MyException{
		try {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			userDao.deleteUser(id);
			throw new Exception();
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		}
	}
	
}
