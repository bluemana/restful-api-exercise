package com.revolut.exercise.protocol;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.revolut.exercise.Context;
import com.revolut.exercise.core.User;

import io.netty.handler.codec.http.HttpMethod;

public class UsersHandler implements ProtocolHandler {

	private static final Link LINK = new Link("/users", "users", HttpMethod.GET);
	private static final UsersResponse USERS_RESPONSE = new UsersResponse();
	private static final Gson GSON;
	
	static {
		GsonBuilder builder = Configuration.createDefaultGsonBuilder();
		builder.registerTypeAdapter(UsersResponse.class, new JsonSerializer<UsersResponse>() {

			@Override
			public JsonElement serialize(UsersResponse response, Type type, JsonSerializationContext ctx) {
				JsonObject result = new JsonObject();
				result.add("users", Configuration.DEFAULT_GSON.toJsonTree(response.getUsers()));
				result.add("links", Configuration.DEFAULT_GSON.toJsonTree(response.getLinks()));
				return result;
			}
		});
		GSON = builder.create();
	}
	
	private static class UsersResponse {
		
		private final List<Link> links;
		
		public UsersResponse() {
			links = new ArrayList<Link>();
		}
		
		public List<Link> getLinks() {
			return links;
		}
		
		public Collection<User> getUsers() {
			return Context.INSTANCE.getUsers().values();
		}
	}
	
	@Override
	public Link getLink() {
		return LINK;
	}

	@Override
	public String handle(HttpMethod method, String json) throws Exception {
		String result = null;
		if (method.equals(HttpMethod.GET)) {
			result = GSON.toJson(USERS_RESPONSE);
		} else if (method.equals(HttpMethod.POST)) {
			result = null;
		}
		return result;
	}
}
