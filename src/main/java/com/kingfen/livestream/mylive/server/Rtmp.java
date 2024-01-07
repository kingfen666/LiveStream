package com.kingfen.livestream.mylive.server;

import com.kingfen.livestream.mylive.server.handlers.ChunkDecoder;
import com.kingfen.livestream.mylive.server.handlers.ChunkEncoder;
import com.kingfen.livestream.mylive.server.handlers.HandShakeDecoder;
import com.kingfen.livestream.mylive.server.handlers.RtmpMessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import com.kingfen.livestream.mylive.server.handlers.*;

import com.kingfen.livestream.mylive.server.manager.StreamManager;
import org.springframework.stereotype.Controller;

@Controller
public class Rtmp {
    public  void run(StreamManager streamManager) throws Exception {
        DefaultEventExecutorGroup executor = new DefaultEventExecutorGroup(128);
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>(){
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new HandShakeDecoder())
                                .addLast(new ChunkDecoder())
                                .addLast(new ChunkEncoder())
                                .addLast(executor, new RtmpMessageHandler(streamManager));

                    }
                }).bind(1935);
    }
}
