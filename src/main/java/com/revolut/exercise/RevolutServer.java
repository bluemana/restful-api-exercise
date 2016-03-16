package com.revolut.exercise;

import com.revolut.exercise.connect.RevolutChannelInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class RevolutServer {

	private final int port;
	
	public RevolutServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
		EventLoopGroup incomingConnectionGroup = new NioEventLoopGroup(1);
		EventLoopGroup acceptedConnectionGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(incomingConnectionGroup, acceptedConnectionGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new RevolutChannelInitializer());
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
