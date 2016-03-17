package com.revolut.exercise.protocol;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpMethod;

public class UsersHandler implements ProtocolHandler {

	private static final Link LINK = new Link("/users", "users", HttpMethod.GET);
	private static final Gson gson = new Gson();
	
	private class Response {
		
	}
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(String json) {
		return gson.toJson(new Response());
	}
}
