package com.revolut.exercise.protocol.transactions;

import com.revolut.exercise.core.Transaction;

public class PostTransactionsResponse {

	private final Transaction transaction;
	
	public PostTransactionsResponse(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
}
