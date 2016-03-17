package com.revolut.exercise.protocol.users;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.ProtocolHandler;

public class GetUsersHandlerTest {

	@Test
	public void handler_NoUsersGet_Returned() throws Exception {
		GetUsersResponse expected = new GetUsersResponse(Collections.emptyList());
		ProtocolHandler handler = new GetUsersHandler();
		String json = handler.handle(null);
		GetUsersResponse response = Configuration.DEFAULT_GSON.fromJson(json, GetUsersResponse.class);
		Assert.assertArrayEquals(new Object[] {expected.getUsers()}, new Object[] {response.getUsers()});
	}
}
