package com.revolut.exercise.protocol;

public interface ProtocolHandler {
	
	public Link getLink();

	public String handle(String json) throws Exception;
}
