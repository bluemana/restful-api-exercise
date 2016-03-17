package com.revolut.exercise.protocol.root;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.ProtocolHandler;

public class GetRootHandlerTest {

	@Test
	public void handler_Get_StartingPoint() throws Exception {
		GetRootResponse expected = new GetRootResponse();
		ProtocolHandler handler = new GetRootHandler();
		String json = handler.handle(null, null);
		GetRootResponse response = Configuration.DEFAULT_GSON.fromJson(json, GetRootResponse.class);
		Assert.assertArrayEquals(new Object[] {expected.getLinks()}, new Object[] {response.getLinks()});
	}
}
