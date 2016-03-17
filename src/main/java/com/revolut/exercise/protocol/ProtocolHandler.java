package com.revolut.exercise.protocol;

public interface ProtocolHandler {
	
	public Link getLink();

	public String handle(Link link, String json) throws Exception;
}
