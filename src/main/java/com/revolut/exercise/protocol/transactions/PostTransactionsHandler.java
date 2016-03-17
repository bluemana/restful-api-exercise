package com.revolut.exercise.protocol.transactions;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.Transaction;
import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class PostTransactionsHandler implements ProtocolHandler {

	private static final Link LINK = new Link("/transactions", "execute", HttpMethod.POST);
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json) throws Exception {
		PostTransactionsRequest request = Configuration.DEFAULT_GSON.fromJson(json, PostTransactionsRequest.class);
		Transaction transaction = Context.INSTANCE.transact(request.getSourceUserId(), request.getDestinationUserId(), request.getAmount());
		return Configuration.DEFAULT_GSON.toJson(new PostTransactionsResponse(transaction));
	}
}
