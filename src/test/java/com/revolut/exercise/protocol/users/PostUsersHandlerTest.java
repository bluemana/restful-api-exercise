package com.revolut.exercise.protocol.users;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.revolut.exercise.Context;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolHandlerTest;

public class PostUsersHandlerTest extends ProtocolHandlerTest {

	public PostUsersHandlerTest() {
		super(new PostUsersHandler(new JsonConfiguration()));
	}
	
	@Test
	public void handle_User_Returned() throws Exception {
		Gson gson = getHandler().getJsonConfiguration().getGson();
		Context context = new Context();
		PostUsersRequest request = new PostUsersRequest("Alex", 100);
		String json = getHandler().handle(null, gson.toJson(request), context);
		PostUsersResponse response = gson.fromJson(json, PostUsersResponse.class);
		Assert.assertTrue(context.getUsers().size() == 1);
		Assert.assertTrue(context.getUsers().contains(response.getUser()));
	}
}
