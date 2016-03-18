package com.revolut.exercise;

import com.revolut.exercise.connect.RevolutChannelInitializer;
import com.revolut.exercise.protocol.JsonConfiguration;
import com.revolut.exercise.protocol.ProtocolConfiguration;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class RevolutServer {

	private static final int DEFAULT_PORT = 8080;
	
	private final int port;
	private final ProtocolConfiguration protocolConfiguration;
	
	public RevolutServer() {
		this(DEFAULT_PORT);
	}
	
	public RevolutServer(int port) {
		this(port, new ProtocolConfiguration(new JsonConfiguration()));
	}
	
	public RevolutServer(int port, ProtocolConfiguration protocolConfiguration) {
		this.port = port;
		this.protocolConfiguration = protocolConfiguration;
	}
	
	public void run() throws Exception {
		EventLoopGroup incomingConnectionGroup = new NioEventLoopGroup(1);
		EventLoopGroup acceptedConnectionGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(incomingConnectionGroup, acceptedConnectionGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new RevolutChannelInitializer(protocolConfiguration));
			bootstrap.option(ChannelOption.SO_BACKLOG, 128);
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture channelFuture = bootstrap.bind(port).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			incomingConnectionGroup.shutdownGracefully();
			acceptedConnectionGroup.shutdownGracefully();
		}
	}
}
