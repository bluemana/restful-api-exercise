package com.revolut.exercise.protocol;

import org.junit.Assert;
import org.junit.Test;

public abstract class ProtocolHandlerTest {

	private final ProtocolHandler handler;
	
	public ProtocolHandlerTest(ProtocolHandler handler) {
		this.handler = handler;
	}
	
	public ProtocolHandler getHandler() {
		return handler;
	}
	
	@Test
	public void configuration_Default_handlerRegistered() {
		ProtocolConfiguration protocolConfiguration = new ProtocolConfiguration(new JsonConfiguration());
		ProtocolHandler defaultHandler = protocolConfiguration.getHandlers().get(handler.getLink());
		Assert.assertNotNull(defaultHandler);
		Assert.assertTrue(handler.getClass().equals(defaultHandler.getClass()));
	}
}
