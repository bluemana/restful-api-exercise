package com.revolut.exercise.protocol;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.root.GetRootHandler;
import com.revolut.exercise.protocol.transactions.GetTransactionsHandler;
import com.revolut.exercise.protocol.user.GetUserHandler;
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
	
	private static class LinkDeserializer implements JsonDeserializer<Link> {

		@Override
		public Link deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) throws JsonParseException {
			JsonObject jsonObject = json.getAsJsonObject();
			return new Link(jsonObject.get("uri").getAsString(),
					jsonObject.get("rel").getAsString(),
					HttpMethod.valueOf(jsonObject.get("method").getAsString()));
		}
	}
	
	private static class UserTypeAdapter implements JsonSerializer<User>, JsonDeserializer<User> {
		
		private static final Gson GSON;
		
		static {
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(HttpMethod.class, new HttpMethodTypeAdapter());
			GSON = builder.create();
		}
		
		@Override
		public JsonElement serialize(User user, Type type, JsonSerializationContext ctx) {
			JsonObject result = GSON.toJsonTree(user).getAsJsonObject();
			result.add("links", GSON.toJsonTree(new Link[] {
				new Link("/user/" + user.getId(), "self", HttpMethod.GET)
			}));
			return result;
		}
		
		@Override
		public User deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) throws JsonParseException {
			JsonObject jsonObject = json.getAsJsonObject();
			User user = new User(jsonObject.get("id").getAsInt(),
					jsonObject.get("name").getAsString(), jsonObject.get("balance").getAsInt());
			return user;
		}
	}
	
	static {
		ProtocolHandler handler = new GetRootHandler();
		HANDLERS.put(handler.getLink(), handler);
		handler = new GetUsersHandler();
		HANDLERS.put(handler.getLink(), handler);
		handler = new PostUsersHandler();
		HANDLERS.put(handler.getLink(), handler);
		handler = new GetUserHandler();
		HANDLERS.put(handler.getLink(), handler);
		handler = new GetTransactionsHandler();
		HANDLERS.put(handler.getLink(), handler);
		
		DEFAULT_GSON = createDefaultGsonBuilder().create();
	}
	
	public static GsonBuilder createDefaultGsonBuilder() {
		GsonBuilder result = new GsonBuilder();
		result.registerTypeAdapter(HttpMethod.class, new HttpMethodTypeAdapter());
		result.registerTypeAdapter(Link.class, new LinkDeserializer());
		result.registerTypeAdapter(User.class, new UserTypeAdapter());
		return result;
	}
}
