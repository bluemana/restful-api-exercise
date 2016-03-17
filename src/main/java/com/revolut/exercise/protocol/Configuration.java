package com.revolut.exercise.protocol;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import io.netty.handler.codec.http.HttpMethod;

public class Configuration {

	public static final Map<Link, ProtocolHandler> HANDLERS = new HashMap<Link, ProtocolHandler>();
	public static final Gson DEFAULT_GSON;
	
	static {
		ProtocolHandler handler = new BaseResourceHandler();
		HANDLERS.put(handler.getLink(), handler);
		handler = new UsersHandler();
		HANDLERS.put(handler.getLink(), handler);
		handler = new TransactionsHandler();
		HANDLERS.put(handler.getLink(), handler);
		
		DEFAULT_GSON = createDefaultGsonBuilder().create();
	}
	
	public static GsonBuilder createDefaultGsonBuilder() {
		GsonBuilder result = new GsonBuilder();
		result.registerTypeAdapter(HttpMethod.class, new JsonSerializer<HttpMethod>() {

			@Override
			public JsonElement serialize(HttpMethod method, Type type, JsonSerializationContext ctx) {
				return new JsonPrimitive(method.toString());
			}
		});
		return result;
	}
}
