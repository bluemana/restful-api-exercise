package com.revolut.exercise.protocol;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.netty.handler.codec.http.HttpMethod;

public class BaseResourceHandlerTest {

	@Test
	public void json_Empty_Default() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("links", new Link[] {
			new Link("/users", "users", HttpMethod.GET),
			new Link("/transactions", "transactions", HttpMethod.GET)
		});
		String expectedString = Configuration.GSON.toJson(expected);
		BaseResourceHandler handler = new BaseResourceHandler();
		String result = handler.handle(null);
		Assert.assertArrayEquals(new String[] {expectedString}, new String[] {result});
	}
}
