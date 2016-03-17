package com.revolut.exercise.connect;


import org.apache.log4j.Logger;

import com.revolut.exercise.protocol.Link;
import com.revolut.exercise.protocol.ProtocolHandler;
import com.revolut.exercise.protocol.Configuration;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderUtil;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class HttpProtocolBridge extends ChannelHandlerAdapter {

	private static final Logger LOGGER = Logger.getLogger(HttpProtocolBridge.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		FullHttpRequest request = (FullHttpRequest) msg;
		LOGGER.info("Received request:\n" + request);
		FullHttpResponse response = null;
		if (request.decoderResult().isSuccess()) {
			String uri = request.uri();
			HttpMethod httpMethod = request.method();
			Link link = new Link(uri, null, httpMethod);
			ProtocolHandler handler = Configuration.HANDLERS.get(link);
			if (handler != null) {
				try {
					String json = handler.handle(link, request.content().toString(CharsetUtil.UTF_8));
					response = createHttpResponse(HttpResponseStatus.OK, json);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
					response = createHttpResponse(HttpResponseStatus.BAD_REQUEST, e.getMessage());
				}
			} else {
				LOGGER.info("Unmapped link: " + link);
				response = createHttpResponse(HttpResponseStatus.BAD_REQUEST, "Unrecognized URI or Method");
			}
		} else {
			LOGGER.info("Malformed request");
			response = createHttpResponse(HttpResponseStatus.BAD_REQUEST,
					request.decoderResult().cause().getMessage());
		}
		response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");
		LOGGER.info("Sending response:\n" + response);
		ctx.writeAndFlush(response);
		if (!HttpHeaderUtil.isKeepAlive(request) || response.status() == HttpResponseStatus.BAD_REQUEST) {
			ctx.close();
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error(cause.getMessage(), cause);
		ctx.close();
	}
	
	private static FullHttpResponse createHttpResponse(HttpResponseStatus status, String content) {
		return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
				content == null ? Unpooled.buffer(0) : Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));
	}
}
