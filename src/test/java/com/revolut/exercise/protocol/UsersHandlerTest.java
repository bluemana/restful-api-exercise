package com.revolut.exercise.protocol;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.core.User;

import io.netty.handler.codec.http.HttpMethod;

public class UsersHandlerTest {

	@Test
	public void handler_NoUsersGet_Returned() throws Exception {
		Map<String, Object> expected = new LinkedHashMap<String, Object>();
		expected.put("users", new User[] {});
		expected.put("links", new Link[] {});
		String expectedString = Configuration.DEFAULT_GSON.toJson(expected);
		ProtocolHandler handler = new UsersHandler();
		String result = handler.handle(HttpMethod.GET, null);
		Assert.assertArrayEquals(new String[] {expectedString}, new String[] {result});
	}
}
