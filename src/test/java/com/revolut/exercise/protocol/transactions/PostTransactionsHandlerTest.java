package com.revolut.exercise.protocol.transactions;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolHandlerTest;

public class PostTransactionsHandlerTest extends ProtocolHandlerTest {

	public PostTransactionsHandlerTest() {
		super(new PostTransactionsHandler(new JsonConfiguration()));
	}

	@Test
	public void handle_Transaction_Returned() throws Exception {
		Context context = new Context();
		User sourceUser = context.createUser("Joe", 10);
		User destinationUser = context.createUser("Kaho", 76);
		int amount = 9;
		PostTransactionsRequest request = new PostTransactionsRequest(sourceUser.getId(), destinationUser.getId(), amount);
		String json = getHandler().handle(null, getHandler().getJsonConfiguration().getGson().toJson(request), context);
		PostTransactionsResponse response = getHandler().getJsonConfiguration().getGson().fromJson(json, PostTransactionsResponse.class);
		Assert.assertTrue(context.getTransactions().size() == 1);
		Assert.assertTrue(context.getTransactions().contains(response.getTransaction()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void handle_InvalidTransaction_Exception() throws Exception {
		Context context = new Context();
		PostTransactionsRequest request = new PostTransactionsRequest(1, 2, 7);
		getHandler().handle(null, getHandler().getJsonConfiguration().getGson().toJson(request), context);
	}
}
