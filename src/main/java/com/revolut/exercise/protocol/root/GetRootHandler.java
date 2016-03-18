package com.revolut.exercise.protocol.root;

import com.revolut.exercise.Context;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class GetRootHandler extends ProtocolHandler {
	
	private static final Link LINK = new Link("/", "root", HttpMethod.GET);
	private static final GetRootResponse RESPONSE = new GetRootResponse();
	
	public GetRootHandler(JsonConfiguration jsonConfiguration) {
		super(jsonConfiguration);
	}

	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json, Context context) throws Exception {
		return getJsonConfiguration().getGson().toJson(RESPONSE);
	}
}
