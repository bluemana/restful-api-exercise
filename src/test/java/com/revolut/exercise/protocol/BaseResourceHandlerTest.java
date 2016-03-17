package com.revolut.exercise.protocol;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.netty.handler.codec.http.HttpMethod;

public class BaseResourceHandlerTest {

	@Test
	public void handler_Get_StartingPoint() throws Exception {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("links", new Link[] {
			new Link("/users", "users", HttpMethod.GET),
			new Link("/transactions", "transactions", HttpMethod.GET)
		});
		String expectedString = Configuration.DEFAULT_GSON.toJson(expected);
		ProtocolHandler handler = new BaseResourceHandler();
		String result = handler.handle(null);
		Assert.assertArrayEquals(new String[] {expectedString}, new String[] {result});
	}
}
