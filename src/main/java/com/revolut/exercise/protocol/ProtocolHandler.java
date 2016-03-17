package com.revolut.exercise.protocol;

import io.netty.handler.codec.http.HttpMethod;

public interface ProtocolHandler {
	
	public Link getLink();

	public String handle(HttpMethod method, String json) throws Exception;
}
