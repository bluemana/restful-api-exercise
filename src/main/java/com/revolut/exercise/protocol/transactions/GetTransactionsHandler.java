package com.revolut.exercise.protocol.transactions;

import com.revolut.exercise.Context;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class GetTransactionsHandler extends ProtocolHandler {
	
	private static final Link LINK = new Link("/transactions", "transactions", HttpMethod.GET);

	public GetTransactionsHandler(JsonConfiguration jsonConfiguration) {
		super(jsonConfiguration);
	}
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json) {
		return getJsonConfiguration().getGson().toJson(new GetTransactionsResponse(Context.INSTANCE.getTransactions()));
	}
}
