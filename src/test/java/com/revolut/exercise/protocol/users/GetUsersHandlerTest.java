package com.revolut.exercise.protocol.users;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolHandlerTest;

public class GetUsersHandlerTest extends ProtocolHandlerTest {

	public GetUsersHandlerTest() {
		super(new GetUsersHandler(new JsonConfiguration()));
	}

	@Test
	public void handle_noUsers_Empty() throws Exception {
		Context context = new Context();
		String json = getHandler().handle(null, null, context);
		GetUsersResponse response = getHandler().getJsonConfiguration().getGson().fromJson(json, GetUsersResponse.class);
		Assert.assertTrue(response.getUsers().isEmpty());
	}
	
	@Test
	public void handle_Users_Returned() throws Exception {
		Context context = new Context();
		User user = context.createUser("Giulio", 1000000);
		String json = getHandler().handle(null, null, context);
		GetUsersResponse response = getHandler().getJsonConfiguration().getGson().fromJson(json, GetUsersResponse.class);
		Assert.assertTrue(response.getUsers().size() == 1);
		Assert.assertTrue(response.getUsers().contains(user));
	}
}
