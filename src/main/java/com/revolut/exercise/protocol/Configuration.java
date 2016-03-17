package com.revolut.exercise.protocol;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.revolut.exercise.protocol.users.GetUsersHandler;
import com.revolut.exercise.protocol.users.PostUsersHandler;

import io.netty.handler.codec.http.HttpMethod;

public class Configuration {

	public static final Map<Link, ProtocolHandler> HANDLERS = new HashMap<Link, ProtocolHandler>();
	public static final Gson DEFAULT_GSON;
	
	private static class HttpMethodTypeAdapter implements JsonSerializer<HttpMethod>, JsonDeserializer<HttpMethod> {

		@Override
		public JsonElement serialize(HttpMethod method, Type type, JsonSerializationContext ctx) {
			return new JsonPrimitive(method.toString());
		}

		@Override
		public HttpMethod deserialize(JsonElement elem, Type type, JsonDeserializationContext ctx) throws JsonParseException {
			return HttpMethod.valueOf(elem.getAsString());
		}
	}
	
	static {
		ProtocolHandler handler = new BaseResourceHandler();
		HANDLERS.put(handler.getLink(), handler);
		handler = new GetUsersHandler();
		HANDLERS.put(handler.getLink(), handler);
		handler = new PostUsersHandler();
		HANDLERS.put(handler.getLink(), handler);
		handler = new TransactionsHandler();
		HANDLERS.put(handler.getLink(), handler);
		
		DEFAULT_GSON = createDefaultGsonBuilder().create();
	}
	
	public static GsonBuilder createDefaultGsonBuilder() {
		GsonBuilder result = new GsonBuilder();
		result.registerTypeAdapter(HttpMethod.class, new HttpMethodTypeAdapter());
		return result;
	}
}
