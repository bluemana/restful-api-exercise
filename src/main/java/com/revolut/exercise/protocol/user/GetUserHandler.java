package com.revolut.exercise.protocol.user;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.User;
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
	public String handle(Link link, String json, Context context) throws Exception {
		User user = context.getUser(link.getResourceId());
		if (user != null) {
			return getJsonConfiguration().getGson().toJson(new GetUserResponse(user));
		} else {
			throw new IllegalArgumentException("User not found");
		}
	}
}
