package com.revolut.exercise.protocol.users;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class PostUsersHandler implements ProtocolHandler {

	private static final Link LINK = new Link("/users", "create", HttpMethod.POST);

	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(String json) throws Exception {
		PostUsersRequest request = Configuration.DEFAULT_GSON.fromJson(json, PostUsersRequest.class);
		User user = Context.INSTANCE.createUser(request.getName(), request.getBalance());
		return Configuration.DEFAULT_GSON.toJson(new PostUsersResponse(user));
	}
}
