package com.revolut.exercise.protocol.transaction;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.Transaction;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class GetTransactionHandler extends ProtocolHandler {

	private static final Link LINK = new Link("/transaction", "transaction", HttpMethod.GET);
	
	public GetTransactionHandler(JsonConfiguration jsonConfiguration) {
		super(jsonConfiguration);
	}

	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json, Context context) throws Exception {
		Transaction transaction = context.getTransaction(link.getResourceId());
		if (transaction != null) {
			return getJsonConfiguration().getGson().toJson(new GetTransactionResponse(transaction));
		} else {
			throw new IllegalArgumentException("Transaction not found");
		}
	}
}
