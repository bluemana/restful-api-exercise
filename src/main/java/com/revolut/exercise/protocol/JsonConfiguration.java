package com.revolut.exercise.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revolut.exercise.core.Transaction;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.json.HttpMethodTypeAdapter;
import com.revolut.exercise.protocol.json.LinkTypeAdapter;
import com.revolut.exercise.protocol.json.TransactionTypeAdapter;
import com.revolut.exercise.protocol.json.UserTypeAdapter;

import io.netty.handler.codec.http.HttpMethod;

public class JsonConfiguration {

	private final Gson gson;
	
	public JsonConfiguration() {
		gson = createGsonBuilder().create();
	}
	
	private GsonBuilder createGsonBuilder() {
		GsonBuilder result = new GsonBuilder();
		result.registerTypeAdapter(HttpMethod.class, new HttpMethodTypeAdapter());
		result.registerTypeAdapter(Link.class, new LinkTypeAdapter());
		result.registerTypeAdapter(User.class, new UserTypeAdapter());
		result.registerTypeAdapter(Transaction.class, new TransactionTypeAdapter());
		return result;
	}
	
	public Gson getGson() {
		return gson;
	}
}
