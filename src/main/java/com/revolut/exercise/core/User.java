package com.revolut.exercise.core;

public class User {

	private final int id;
	private final String name;
	private final int balance;
	
	public User(int id, String name, int balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getBalance() {
		return balance;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof User && ((User) obj).id == id;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
	
	@Override
	public String toString() {
		return String.format("user{id=%d;name=%s;balance=%d}", id, name, balance);
	}
}
