package com.revolut.exercise.protocol.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.Link;

import io.netty.handler.codec.http.HttpMethod;

public class UserTypeAdapter implements JsonSerializer<User>, JsonDeserializer<User> {
	
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