package com.revolut.exercise.protocol.users;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolHandler;

public class GetUsersHandlerTest {

	@Test
	public void noUsers_Get_Empty() throws Exception {
		GetUsersResponse expected = new GetUsersResponse(Collections.emptyList());
		ProtocolHandler handler = new GetUsersHandler(new JsonConfiguration());
		String json = handler.handle(null, null);
		GetUsersResponse response = handler.getJsonConfiguration().getGson().fromJson(json, GetUsersResponse.class);
		Assert.assertArrayEquals(new Object[] {expected.getUsers()}, new Object[] {response.getUsers()});
	}
}
