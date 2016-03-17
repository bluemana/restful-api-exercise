package com.revolut.exercise.protocol.user;

import com.revolut.exercise.Context;
import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class GetUserHandler implements ProtocolHandler {
	
	private static final Link LINK = new Link("/user", "user", HttpMethod.GET);
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json) throws Exception {
		return Configuration.DEFAULT_GSON.toJson(new GetUserResponse(Context.INSTANCE.getUser(link.getResourceId())));
	}
}
