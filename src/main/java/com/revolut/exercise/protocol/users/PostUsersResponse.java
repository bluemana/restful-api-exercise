package com.revolut.exercise.protocol.users;

import com.revolut.exercise.core.User;

public class PostUsersResponse {

	private final User user;
	
	public PostUsersResponse(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
}
