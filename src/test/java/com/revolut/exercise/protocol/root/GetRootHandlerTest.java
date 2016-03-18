package com.revolut.exercise.protocol.root;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolHandler;

public class GetRootHandlerTest {

	@Test
	public void default_Get_Response() throws Exception {
		GetRootResponse expected = new GetRootResponse();
		ProtocolHandler handler = new GetRootHandler(new JsonConfiguration());
		String json = handler.handle(null, null);
		GetRootResponse response = handler.getJsonConfiguration().getGson().fromJson(json, GetRootResponse.class);
		Assert.assertArrayEquals(new Object[] {expected.getLinks()}, new Object[] {response.getLinks()});
	}
}
