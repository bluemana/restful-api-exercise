package com.revolut.exercise.connect;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.Context;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolConfiguration;

import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class HttpProtocolBridgeTest {
	
	@Test
	public void http_GetMappedResource_200() throws Exception {
		EmbeddedChannel channel = new EmbeddedChannel(
				new HttpProtocolBridge(new ProtocolConfiguration(new JsonConfiguration()), new Context()));
		HttpRequest request = createHttpRequest(HttpMethod.GET, "/", null);
		channel.writeInbound(request);
		channel.checkException();
		FullHttpResponse response = channel.readOutbound();
		Assert.assertTrue(response.status().equals(HttpResponseStatus.OK));
	}
	
	@Test
	public void http_GetUnmappedResource_400() throws Exception {
		EmbeddedChannel channel = new EmbeddedChannel(
				new HttpProtocolBridge(new ProtocolConfiguration(new JsonConfiguration()), new Context()));
		HttpRequest request = createHttpRequest(HttpMethod.GET, "/something", null);
		channel.writeInbound(request);
		channel.checkException();
		FullHttpResponse response = channel.readOutbound();
		Assert.assertTrue(response.status().equals(HttpResponseStatus.BAD_REQUEST));
	}
	
	public static HttpRequest createHttpRequest(HttpMethod method, String path, String content) {
		HttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
			method, path, content == null ? Unpooled.buffer(0) : Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));
		return request;
	}
}
