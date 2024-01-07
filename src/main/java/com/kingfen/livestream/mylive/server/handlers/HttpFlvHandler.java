package com.kingfen.livestream.mylive.server.handlers;

import com.google.common.base.Splitter;
import com.kingfen.livestream.mylive.server.entities.Stream;
import com.kingfen.livestream.mylive.server.manager.StreamManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.*;

/**
 * @author longyubo 2020年1月7日 下午3:19:43
 **/
@Slf4j
public class HttpFlvHandler extends SimpleChannelInboundHandler<HttpObject> {

	StreamManager streamManager;

	public HttpFlvHandler(StreamManager streamManager) {
		this.streamManager = streamManager;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		if (msg instanceof HttpRequest) {
			HttpRequest req = (HttpRequest) msg;

			String uri = req.uri();
			List<String> appAndStreamName = Splitter.on("/").omitEmptyStrings().splitToList(uri);
			if (appAndStreamName.size() != 1) {
				httpResponseStreamNotExist(ctx, uri);
				return;
			}


			String app=appAndStreamName.get(0);


			Stream stream = streamManager.getStream(app);

			if (stream == null) {
				httpResponseStreamNotExist(ctx, uri);
				return;
			}
			DefaultHttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
			response.headers().set(CONTENT_TYPE, "video/x-flv");
			response.headers().set(TRANSFER_ENCODING, "chunked");
			//we may need a cross domain configuration in future 
			response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
			response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT,DELETE");
		    response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept");
          
			ctx.writeAndFlush(response);

			stream.addHttpFlvSubscriber(ctx.channel());

		}

    }

	private void httpResponseStreamNotExist(ChannelHandlerContext ctx, String uri) {
		ByteBuf body = Unpooled.wrappedBuffer(("stream [" + uri + "] not exist").getBytes());
		DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.NOT_FOUND, body);
		response.headers().set(CONTENT_TYPE, "text/plain");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

}
