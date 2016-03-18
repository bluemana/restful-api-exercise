package com.revolut.exercise.protocol.transactions;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.Transaction;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class PostTransactionsHandler extends ProtocolHandler {
	
	private static final Link LINK = new Link("/transactions", "execute", HttpMethod.POST);

	public PostTransactionsHandler(JsonConfiguration jsonConfiguration) {
		super(jsonConfiguration);
	}
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json, Context context) throws Exception {
		PostTransactionsRequest request = getJsonConfiguration().getGson().fromJson(json, PostTransactionsRequest.class);
		Transaction transaction = context.transact(request.getSourceUserId(), request.getDestinationUserId(), request.getAmount());
		if (transaction != null) {
			return getJsonConfiguration().getGson().toJson(new PostTransactionsResponse(transaction));
		} else {
			throw new IllegalArgumentException("Invalid transaction");
		}
	}
}
