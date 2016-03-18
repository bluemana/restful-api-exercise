package com.revolut.exercise.protocol.transaction;

import com.revolut.exercise.core.Transaction;

public class GetTransactionResponse {

	private final Transaction transaction;
	
	public GetTransactionResponse(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
}
