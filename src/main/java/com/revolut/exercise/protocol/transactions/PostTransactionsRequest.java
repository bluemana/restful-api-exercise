package com.revolut.exercise.protocol.transactions;

public class PostTransactionsRequest {

	private final int sourceUserId;
	private final int destinationUserId;
	private final int amount;
	
	public PostTransactionsRequest(int sourceUserId, int destinationUserId, int amount) {
		this.sourceUserId = sourceUserId;
		this.destinationUserId = destinationUserId;
		this.amount = amount;
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
}
