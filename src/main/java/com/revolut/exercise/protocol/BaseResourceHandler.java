package com.revolut.exercise.protocol;

import java.util.ArrayList;
import java.util.List;

import io.netty.handler.codec.http.HttpMethod;

public class BaseResourceHandler implements ProtocolHandler {
	
	private static final Link LINK = new Link("/", "root", HttpMethod.GET);
	private static final Response RESPONSE = new Response();
	
	private static class Response {
		
		private final List<Link> links;
		
		public Response() {
			links = new ArrayList<Link>();
			links.add(new Link("/users", "users", HttpMethod.GET));
			links.add(new Link("/transactions", "transactions", HttpMethod.GET));
		}
	}

	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(String json) throws Exception {
		return Configuration.DEFAULT_GSON.toJson(RESPONSE);
	}
}
