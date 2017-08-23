package com.example.demo.dao.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;

@Repository
public class UserDaoImpl extends BaseJdbcDao implements UserDao {

	@Override
	public List<User> getUserList() throws Exception {
		String sql = "SELECT * FROM `user`";
		List<User> result = getJdbcTemplateSecondary().query(sql, getRowMapper(User.class));
		return result;
	}

	@Override
	public User getUser(Long id) throws Exception {
		String sql = appenderStr("SELECT * FROM `user` WHERE id = ?");
		return getJdbcTemplateSecondary().queryForObject(sql, getRowMapper(User.class), id);
	}

	@Override
	public void createUser(User user) throws Exception {
		String sql = "INSERT INTO `user` (name, age, birthday) VALUES (?, ?, ?)";
		getJdbcTemplatePrimary().update(sql, user.getName(), user.getAge(), user.getBirthday());
	}
	
	@Override
	public void updateUser(Long id, User user) throws Exception {
		String sql = "UPDATE `user` SET name = ?, age = ?, birthday = ? WHERE id = ?";
		getJdbcTemplatePrimary().update(sql, user.getName(), user.getAge(), user.getBirthday(), id);
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		String sql = "DELETE FROM `user` WHERE id = ?";
		getJdbcTemplatePrimary().update(sql, id);
	}

}
