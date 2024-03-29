package com.kingfen.livestream.mylive.server.rtmp.messages;
/**
@author longyubo
2019年12月16日 下午5:37:47
**/

import com.kingfen.livestream.mylive.server.rtmp.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class AudioMessage extends RtmpMediaMessage {
	byte[] audioData;

	@Override
	public int getOutboundCsid() {
		return 10;
	}

	@Override
	public ByteBuf encodePayload() {
		return Unpooled.wrappedBuffer(audioData);
	}

	@Override
	public int getMsgType() {
		return Constants.MSG_TYPE_AUDIO_MESSAGE;
	}

	@Override
	public byte[] raw() {

		return audioData;
	}
	
	public boolean isAACAudioSpecificConfig(){
		return audioData.length>1 && audioData[1]==0;
	}

	@Override
	public String toString() {
		return "AudioMessage [audioData=" + Arrays.toString(audioData) + ", timestampDelta=" + timestampDelta
				+ ", timestamp=" + timestamp + ", inboundHeaderLength=" + inboundHeaderLength + ", inboundBodyLength="
				+ inboundBodyLength + "]";
	}
	
	
}
