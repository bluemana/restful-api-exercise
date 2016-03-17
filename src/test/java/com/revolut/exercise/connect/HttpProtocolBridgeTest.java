package com.revolut.exercise.connect;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.exercise.protocol.Configuration;
import com.revolut.exercise.protocol.users.PostUsersRequest;
import com.revolut.exercise.protocol.users.PostUsersResponse;

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
		EmbeddedChannel channel = new EmbeddedChannel(new HttpProtocolBridge());
		HttpRequest request = createHttpRequest(HttpMethod.GET, "/", null);
		channel.writeInbound(request);
		channel.checkException();
		FullHttpResponse response = channel.readOutbound();
		Assert.assertArrayEquals(new Object[] {HttpResponseStatus.OK}, new Object[] {response.status()});
	}
	
	@Test
	public void http_GetUnmappedResource_400() throws Exception {
		EmbeddedChannel channel = new EmbeddedChannel(new HttpProtocolBridge());
		HttpRequest request = createHttpRequest(HttpMethod.GET, "/something", null);
		channel.writeInbound(request);
		channel.checkException();
		FullHttpResponse response = channel.readOutbound();
		Assert.assertArrayEquals(new Object[] {HttpResponseStatus.BAD_REQUEST}, new Object[] {response.status()});
	}
	
	@Test
	public void http_PostUsers_Created() throws Exception {
		EmbeddedChannel channel = new EmbeddedChannel(new HttpProtocolBridge());
		PostUsersRequest request = new PostUsersRequest("Giulio", 1000000);
		HttpRequest httpRequest = createHttpRequest(HttpMethod.POST, "/users", Configuration.DEFAULT_GSON.toJson(request));
		channel.writeInbound(httpRequest);
		channel.checkException();
		FullHttpResponse httpResponse = channel.readOutbound();
		PostUsersResponse response = Configuration.DEFAULT_GSON.fromJson(
			httpResponse.content().toString(CharsetUtil.UTF_8), PostUsersResponse.class);
		Assert.assertArrayEquals(new Object[] {
			HttpResponseStatus.OK,
			request.getName(),
			request.getBalance()}, new Object[] {
			httpResponse.status(),
			response.getUser().getName(),
			response.getUser().getBalance()});
	}
	
	public static HttpRequest createHttpRequest(HttpMethod method, String path, String content) {
		HttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
			method, path, content == null ? Unpooled.buffer(0) : Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));
		return request;
	}
}
