package com.revolut.exercise.protocol.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import io.netty.handler.codec.http.HttpMethod;

public class HttpMethodTypeAdapter implements JsonSerializer<HttpMethod>, JsonDeserializer<HttpMethod> {

	@Override
	public JsonElement serialize(HttpMethod method, Type type, JsonSerializationContext ctx) {
		return new JsonPrimitive(method.toString());
	}

	@Override
	public HttpMethod deserialize(JsonElement elem, Type type, JsonDeserializationContext ctx) throws JsonParseException {
		return HttpMethod.valueOf(elem.getAsString());
	}
}

