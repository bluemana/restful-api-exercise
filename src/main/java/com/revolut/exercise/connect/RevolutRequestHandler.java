package com.revolut.exercise.connect;


import org.apache.log4j.Logger;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderUtil;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class RevolutRequestHandler extends ChannelHandlerAdapter {

	private static final Logger LOGGER = Logger.getLogger(RevolutRequestHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		FullHttpMessage request = (FullHttpMessage) msg;
		LOGGER.info("Received request:\n" + request);
		String content = request.decoderResult().isFailure() ?
				request.decoderResult().cause().getMessage() : null;
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				request.decoderResult().isSuccess() ? HttpResponseStatus.OK : HttpResponseStatus.BAD_REQUEST,
				content == null ? Unpooled.buffer(0) : Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));
		response.headers().set(HttpHeaderNames.CONTENT_TYPE, "charset=UTF-8");
		LOGGER.info("Sending response:\n" + response);
		ctx.writeAndFlush(response);
		if (!HttpHeaderUtil.isKeepAlive(request)) {
			ctx.close();
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error(cause.getMessage(), cause);
		ctx.close();
	}
}
