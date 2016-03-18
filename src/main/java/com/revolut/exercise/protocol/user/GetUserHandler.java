package com.revolut.exercise.protocol.user;

import com.revolut.exercise.Context;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class GetUserHandler extends ProtocolHandler {
	
	private static final Link LINK = new Link("/user", "user", HttpMethod.GET);
	
	public GetUserHandler(JsonConfiguration jsonConfiguration) {
		super(jsonConfiguration);
	}
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json) throws Exception {
		return getJsonConfiguration().getGson().toJson(new GetUserResponse(Context.INSTANCE.getUser(link.getResourceId())));
	}
}
