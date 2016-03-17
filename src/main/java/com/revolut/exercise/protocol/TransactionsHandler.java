package com.revolut.exercise.protocol;

import io.netty.handler.codec.http.HttpMethod;

public class TransactionsHandler implements ProtocolHandler {

	private static final Link LINK = new Link("/transactions", "transactions", HttpMethod.GET);
	
	private class Response {
		
	}
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json) {
		return Configuration.DEFAULT_GSON.toJson(new Response());
	}
}
