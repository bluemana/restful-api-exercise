package com.revolut.exercise.core;

public class Transaction {

	private final int id;
	private final int sourceUserId;
	private final int destinationUserId;
	private final int amount;
	
	public Transaction(int id, int sourceUserId, int destinationUserId, int amount) {
		this.id = id;
		this.sourceUserId = sourceUserId;
		this.destinationUserId = destinationUserId;
		this.amount = amount;
	}
	
	public int getId() {
		return id;
	}
	
	public int getSourceUserId() {
		return sourceUserId;
	}
	
	public int getDestinationUserId() {
		return destinationUserId;
	}
	
	public int getAmount() {
		return amount;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Transaction && ((Transaction) obj).id == id;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
	
	@Override
	public String toString() {
		return String.format("transaction{id=%d;sourceId=%d;destinationId=%d;amount=%d}",
				id, sourceUserId, destinationUserId, amount);
	}
}
