package com.revolut.exercise.protocol.root;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolHandlerTest;

public class GetRootHandlerTest extends ProtocolHandlerTest {

	public GetRootHandlerTest() {
		super(new GetRootHandler(new JsonConfiguration()));
	}

	@Test
	public void handle_Default_DefaultResponse() throws Exception {
		GetRootResponse expected = new GetRootResponse();
		String json = getHandler().handle(null, null, null);
		GetRootResponse response = getHandler().getJsonConfiguration().getGson().fromJson(json, GetRootResponse.class);
		Assert.assertTrue(expected.getLinks().equals(response.getLinks()));
	}
}
