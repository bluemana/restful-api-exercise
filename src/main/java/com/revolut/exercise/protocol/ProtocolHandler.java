package com.revolut.exercise.protocol;

public abstract class ProtocolHandler {
	
	private final JsonConfiguration jsonConfiguration;
	
	public ProtocolHandler(JsonConfiguration jsonConfiguration) {
		this.jsonConfiguration = jsonConfiguration;
	}
	
	public abstract Link getLink();
	
	public JsonConfiguration getJsonConfiguration() {
		return jsonConfiguration;
	}
	
	public abstract String handle(Link link, String json) throws Exception;
}
