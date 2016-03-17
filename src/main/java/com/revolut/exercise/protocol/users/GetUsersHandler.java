package com.revolut.exercise.protocol.users;

import com.revolut.exercise.Context;
import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class GetUsersHandler implements ProtocolHandler {

	private static final Link LINK = new Link("/users", "users", HttpMethod.GET);
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(String json) throws Exception {
		return Configuration.DEFAULT_GSON.toJson(new GetUsersResponse(Context.INSTANCE.getUsers()));
	}
}
