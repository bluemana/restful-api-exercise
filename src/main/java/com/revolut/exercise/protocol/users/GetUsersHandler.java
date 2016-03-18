package com.revolut.exercise.protocol.users;

import com.revolut.exercise.Context;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class GetUsersHandler extends ProtocolHandler {

	private static final Link LINK = new Link("/users", "users", HttpMethod.GET);
	
	public GetUsersHandler(JsonConfiguration jsonConfiguration) {
		super(jsonConfiguration);
	}
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json) throws Exception {
		return getJsonConfiguration().getGson().toJson(new GetUsersResponse(Context.INSTANCE.getUsers()));
	}
}
