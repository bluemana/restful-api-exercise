package com.revolut.exercise.protocol;

import com.revolut.exercise.Context;

public abstract class ProtocolHandler {
	
	private final JsonConfiguration jsonConfiguration;
	
	public ProtocolHandler(JsonConfiguration jsonConfiguration) {
		this.jsonConfiguration = jsonConfiguration;
	}
	
	public abstract Link getLink();
	
	public JsonConfiguration getJsonConfiguration() {
		return jsonConfiguration;
	}
	
	public abstract String handle(Link link, String json, Context context) throws Exception;
}
