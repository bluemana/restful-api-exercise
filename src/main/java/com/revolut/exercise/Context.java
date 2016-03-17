package com.revolut.exercise;

import java.util.HashMap;
import java.util.Map;

import com.revolut.exercise.core.User;

public enum Context {

	INSTANCE;
	
	private final Map<String, User> users;
	
	private Context() {
		users = new HashMap<String, User>();
	}
	
	public Map<String, User> getUsers() {
		return users;
	}
}
