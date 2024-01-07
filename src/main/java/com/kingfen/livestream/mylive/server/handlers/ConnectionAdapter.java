package com.kingfen.livestream.mylive.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author longyubo 2019年12月10日 上午10:28:04
 **/
@Slf4j
public class ConnectionAdapter extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {


		
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {


		ctx.fireChannelInactive();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {



	}

}
