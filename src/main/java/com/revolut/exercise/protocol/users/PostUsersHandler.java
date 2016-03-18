package com.revolut.exercise.protocol.users;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class PostUsersHandler extends ProtocolHandler {
	
	private static final Link LINK = new Link("/users", "create", HttpMethod.POST);

	public PostUsersHandler(JsonConfiguration jsonConfiguration) {
		super(jsonConfiguration);
	}

	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json, Context context) throws Exception {
		PostUsersRequest request = getJsonConfiguration().getGson().fromJson(json, PostUsersRequest.class);
		User user = context.createUser(request.getName(), request.getBalance());
		return getJsonConfiguration().getGson().toJson(new PostUsersResponse(user));
	}
}
