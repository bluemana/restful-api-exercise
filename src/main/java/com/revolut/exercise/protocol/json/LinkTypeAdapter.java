package com.revolut.exercise.protocol.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.revolut.exercise.protocol.Link;

import io.netty.handler.codec.http.HttpMethod;

public class LinkTypeAdapter implements JsonDeserializer<Link> {

	@Override
	public Link deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		return new Link(jsonObject.get("uri").getAsString(),
				jsonObject.get("rel").getAsString(),
				HttpMethod.valueOf(jsonObject.get("method").getAsString()));
	}
}