package com.kingfen.livestream.mylive.server.manager;
/**
@author longyubo
2020年1月2日 下午3:32:04
**/

import com.kingfen.livestream.mylive.server.entities.Stream;

import java.util.concurrent.ConcurrentHashMap;

/**
 * all stream info store here,including publisher and subscriber and their live type video & audio
 * @author longyubo
 *
 */
public class StreamManager {
	private ConcurrentHashMap<String, Stream> streams=new ConcurrentHashMap<>();
	
	public void newStream(String Str,Stream s) {
		streams.put(Str, s);
	}
	
	public boolean exist(String streamName) {
		return streams.containsKey(streamName);
	}
	
	public Stream getStream(String streamName) {
		return streams.get(streamName);
	}
	
	public void remove(String streamName) {
		streams.remove(streamName);
	}
}

