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
import com.revolut.exercise.core.Transaction;
import com.revolut.exercise.protocol.Link;

import io.netty.handler.codec.http.HttpMethod;

public class TransactionTypeAdapter implements JsonSerializer<Transaction>, JsonDeserializer<Transaction> {
	
	private static final Gson GSON;
	
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(HttpMethod.class, new HttpMethodTypeAdapter());
		GSON = builder.create();
	}
	
	@Override
	public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext ctx) {
		JsonObject result = GSON.toJsonTree(transaction).getAsJsonObject();
		result.add("links", GSON.toJsonTree(new Link[] {
			new Link("/transaction/" + transaction.getId(), "self", HttpMethod.GET)
		}));
		return result;
	}
	
	@Override
	public Transaction deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		Transaction transaction = new Transaction(jsonObject.get("id").getAsInt(),
				jsonObject.get("sourceUserId").getAsInt(),
				jsonObject.get("destinationUserId").getAsInt(),
				jsonObject.get("amount").getAsInt());
		return transaction;
	}
}