package com.example.demo.web.request;

import com.example.demo.domain.User;

public class AddUserRequest extends CommRequest<Object>{
	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
