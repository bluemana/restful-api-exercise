package com.revolut.exercise.protocol.user;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandlerTest;

import io.netty.handler.codec.http.HttpMethod;

public class GetUserHandlerTest extends ProtocolHandlerTest {

	public GetUserHandlerTest() {
		super(new GetUserHandler(new JsonConfiguration()));
	}
	
	@Test
	public void handle_User_Returned() throws Exception {
		Context context = new Context();
		User user = context.createUser("Alice", 85);
		Link link = new Link("/user/" + user.getId(), null, HttpMethod.GET);
		String json = getHandler().handle(link, null, context);
		GetUserResponse response = getHandler().getJsonConfiguration().getGson().fromJson(json, GetUserResponse.class);
		Assert.assertTrue(user.equals(response.getUser()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void handle_InvalidUser_Exception() throws Exception {
		Context context = new Context();
		Link link = new Link("/user/1", null, HttpMethod.GET);
		getHandler().handle(link, null, context);
	}
}
