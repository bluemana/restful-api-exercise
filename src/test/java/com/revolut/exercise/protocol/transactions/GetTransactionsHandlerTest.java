package com.revolut.exercise.protocol.transactions;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.Context;
import com.revolut.exercise.core.Transaction;
import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolHandlerTest;

public class GetTransactionsHandlerTest extends ProtocolHandlerTest {

	public GetTransactionsHandlerTest() {
		super(new GetTransactionsHandler(new JsonConfiguration()));
	}

	@Test
	public void handle_NoTransactions_Empty() throws Exception {
		Context context = new Context();
		String json = getHandler().handle(null, null, context);
		GetTransactionsResponse response = getHandler().getJsonConfiguration().getGson().fromJson(json, GetTransactionsResponse.class);
		Assert.assertTrue(response.getTransactions().isEmpty());
	}
	
	@Test
	public void handle_Transactions_Returned() throws Exception {
		Context context = new Context();
		User sourceUser = context.createUser("Joe", 10);
		User destinationUser = context.createUser("Kaho", 76);
		int amount = 9;
		Transaction transaction = context.transact(sourceUser.getId(), destinationUser.getId(), amount);
		String json = getHandler().handle(null, null, context);
		GetTransactionsResponse response = getHandler().getJsonConfiguration().getGson().fromJson(json, GetTransactionsResponse.class);
		Assert.assertTrue(response.getTransactions().size() == 1);
		Assert.assertTrue(response.getTransactions().contains(transaction));
	}
}
