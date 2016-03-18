package com.revolut.exercise.protocol.transactions;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolHandler;

public class GetTransactionsHandlerTest {

	@Test
	public void handler_NoTransactionsGet_Returned() throws Exception {
		GetTransactionsResponse expected = new GetTransactionsResponse(Collections.emptyList());
		ProtocolHandler handler = new GetTransactionsHandler(new JsonConfiguration());
		String json = handler.handle(null, null);
		GetTransactionsResponse response = handler.getJsonConfiguration().getGson().fromJson(json, GetTransactionsResponse.class);
		Assert.assertArrayEquals(new Object[] {expected.getTransactions()}, new Object[] {response.getTransactions()});
	}
}
