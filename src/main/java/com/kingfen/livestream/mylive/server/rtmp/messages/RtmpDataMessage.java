package com.kingfen.livestream.mylive.server.rtmp.messages;

import com.kingfen.livestream.mylive.amf.AMF0;
import com.kingfen.livestream.mylive.server.rtmp.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 18 for AMF0 and message type value of 15 for AMF3
 * 
 * @author longyubo 2019年12月16日 下午5:17:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RtmpDataMessage extends RtmpMessage {
	List<Object> data;

	@Override
	public ByteBuf encodePayload() {
		ByteBuf buffer = Unpooled.buffer();
		AMF0.encode(buffer, data);
		return buffer;
	}

	@Override
	public int getOutboundCsid() {
		return 3;
	}

	@Override
	public int getMsgType() {
		return Constants.MSG_TYPE_DATA_MESSAGE_AMF0;
	}
}
