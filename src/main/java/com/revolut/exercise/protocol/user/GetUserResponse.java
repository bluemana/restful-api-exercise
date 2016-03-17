package com.revolut.exercise.protocol.user;

import com.revolut.exercise.core.User;

public class GetUserResponse {

	private final User user;
	
	public GetUserResponse(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
}
