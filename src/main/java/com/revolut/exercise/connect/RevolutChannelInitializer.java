package com.revolut.exercise.connect;

import com.revolut.exercise.protocol.ProtocolConfiguration;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class RevolutChannelInitializer extends ChannelInitializer<Channel> {

	private final ProtocolConfiguration protocolConfiguration;
	
	public RevolutChannelInitializer(ProtocolConfiguration protocolConfiguration) {
		this.protocolConfiguration = protocolConfiguration;
	}
	
	@Override
	protected void initChannel(Channel channel) throws Exception {
		channel.pipeline().addLast(
			new HttpRequestDecoder(),
			new HttpResponseEncoder(),
			new HttpObjectAggregator(1024 * 1024),
			new HttpProtocolBridge(protocolConfiguration));
	}
}
