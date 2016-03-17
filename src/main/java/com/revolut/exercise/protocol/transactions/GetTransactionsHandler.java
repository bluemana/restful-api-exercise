package com.revolut.exercise.protocol.transactions;

import com.revolut.exercise.Context;
import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class GetTransactionsHandler implements ProtocolHandler {

	private static final Link LINK = new Link("/transactions", "transactions", HttpMethod.GET);
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json) {
		return Configuration.DEFAULT_GSON.toJson(new GetTransactionsResponse(Context.INSTANCE.getTransactions()));
	}
}
