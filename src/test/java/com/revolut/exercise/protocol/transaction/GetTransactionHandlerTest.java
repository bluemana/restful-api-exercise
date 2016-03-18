package com.revolut.exercise.protocol.transaction;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.Transaction;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandlerTest;

import io.netty.handler.codec.http.HttpMethod;

public class GetTransactionHandlerTest extends ProtocolHandlerTest {

	public GetTransactionHandlerTest() {
		super(new GetTransactionHandler(new JsonConfiguration()));
	}

	@Test
	public void handle_Transaction_Returned() throws Exception {
		Context context = new Context();
		User sourceUser = context.createUser("Joe", 10);
		User destinationUser = context.createUser("Kaho", 76);
		int amount = 9;
		Transaction transaction = context.transact(sourceUser.getId(), destinationUser.getId(), amount);
		Link link = new Link("/transaction/" + transaction.getId(), null, HttpMethod.GET);
		String json = getHandler().handle(link, null, context);
		GetTransactionResponse response = getHandler().getJsonConfiguration().getGson().fromJson(json, GetTransactionResponse.class);
		Assert.assertTrue(transaction.equals(response.getTransaction()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void handle_InvalidTransaction_Exception() throws Exception {
		Context context = new Context();
		Link link = new Link("/transaction/1", null, HttpMethod.GET);
		getHandler().handle(link, null, context);
	}
}
