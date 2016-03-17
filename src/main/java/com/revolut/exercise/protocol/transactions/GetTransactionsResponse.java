package com.revolut.exercise.protocol.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.revolut.exercise.core.Transaction;
import com.revolut.exercise.protocol.Link;

import io.netty.handler.codec.http.HttpMethod;

public class GetTransactionsResponse {

	private final Collection<Transaction> transactions;
	private final List<Link> links;
	
	public GetTransactionsResponse(Collection<Transaction> transactions) {
		this.transactions = transactions;
		links = new ArrayList<Link>();
		links.add(new Link("/transactions", "execute", HttpMethod.POST));
	}
	
	public Collection<Transaction> getTransactions() {
		return transactions;
	}
	
	public List<Link> getLinks() {
		return links;
	}
}
