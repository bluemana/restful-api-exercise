package com.revolut.exercise.protocol.root;

import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;

import io.netty.handler.codec.http.HttpMethod;

public class GetRootHandler implements ProtocolHandler {
	
	private static final Link LINK = new Link("/", "root", HttpMethod.GET);
	private static final GetRootResponse RESPONSE = new GetRootResponse();
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(Link link, String json) throws Exception {
		return Configuration.DEFAULT_GSON.toJson(RESPONSE);
	}
}
