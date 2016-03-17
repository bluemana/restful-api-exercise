package com.revolut.exercise.protocol.transactions;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.ProtocolHandler;

public class GetTransactionsHandlerTest {

	@Test
	public void handler_NoUsersGet_Returned() throws Exception {
		GetTransactionsResponse expected = new GetTransactionsResponse(Collections.emptyList());
		ProtocolHandler handler = new GetTransactionsHandler();
		String json = handler.handle(null, null);
		GetTransactionsResponse response = Configuration.DEFAULT_GSON.fromJson(json, GetTransactionsResponse.class);
		Assert.assertArrayEquals(new Object[] {expected.getTransactions()}, new Object[] {response.getTransactions()});
	}
}
