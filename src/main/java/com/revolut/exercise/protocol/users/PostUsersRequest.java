package com.revolut.exercise.protocol.users;

public class PostUsersRequest {

	private final String name;
	private final int balance;
	
	public PostUsersRequest(String name, int balance) {
		this.name = name;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}
	
	public int getBalance() {
		return balance;
	}
}
