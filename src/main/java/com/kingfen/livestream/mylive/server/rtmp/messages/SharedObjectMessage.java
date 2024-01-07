package com.kingfen.livestream.mylive.server.rtmp.messages;

import com.kingfen.livestream.mylive.server.rtmp.Constants;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The message types 19 for AMF0 and 16 for AMF3 are reserved for shared object
 * events
 * 
 * @author longyubo 2019年12月16日 下午5:18:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedObjectMessage extends RtmpMessage {
	List<Object> body;

	@Override
	public ByteBuf encodePayload() {
		return null;
	}

	@Override
	public int getOutboundCsid() {
		return 4;
	}

	@Override
	public int getMsgType() {
		return Constants.MSG_TYPE_SHARED_OBJECT_MESSAGE_AMF0;
	}
}
