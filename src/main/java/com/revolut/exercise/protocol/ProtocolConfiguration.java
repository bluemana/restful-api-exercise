package com.revolut.exercise.protocol;

import java.util.HashMap;
import java.util.Map;

import com.revolut.exercise.protocol.root.GetRootHandler;
import com.revolut.exercise.protocol.transactions.GetTransactionsHandler;
import com.revolut.exercise.protocol.transactions.PostTransactionsHandler;
import com.revolut.exercise.protocol.user.GetUserHandler;
import com.revolut.exercise.protocol.users.GetUsersHandler;
import com.revolut.exercise.protocol.users.PostUsersHandler;

public class ProtocolConfiguration {

	private final Map<Link, ProtocolHandler> handlers;
	
	public ProtocolConfiguration(JsonConfiguration jsonConfiguration) {
		handlers = new HashMap<Link, ProtocolHandler>();
		configureHandlers(jsonConfiguration);
	}
	
	private void configureHandlers(JsonConfiguration jsonConfiguration) {
		ProtocolHandler handler = new GetRootHandler(jsonConfiguration);
		handlers.put(handler.getLink(), handler);
		handler = new GetUsersHandler(jsonConfiguration);
		handlers.put(handler.getLink(), handler);
		handler = new PostUsersHandler(jsonConfiguration);
		handlers.put(handler.getLink(), handler);
		handler = new GetUserHandler(jsonConfiguration);
		handlers.put(handler.getLink(), handler);
		handler = new GetTransactionsHandler(jsonConfiguration);
		handlers.put(handler.getLink(), handler);
		handler = new PostTransactionsHandler(jsonConfiguration);
		handlers.put(handler.getLink(), handler);
	}
	
	public Map<Link, ProtocolHandler> getHandlers() {
		return handlers;
	}
}
