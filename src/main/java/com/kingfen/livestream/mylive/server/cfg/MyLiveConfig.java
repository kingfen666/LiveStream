package com.kingfen.livestream.mylive.server.cfg;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author longyubo 2020年1月9日 下午2:29:25
 **/
@Data
@Component
public class MyLiveConfig {

	public static MyLiveConfig INSTANCE = null;
	@Value("${rtmpPort}")
	int rtmpPort;
	@Value("${httpFlvPort}")
	int httpFlvPort;
	@Value("${saveFlvFile}")
	boolean saveFlvFile;
	@Value("${saveFlVFilePath}")
	String saveFlVFilePath;
	@Value("${handlerThreadPoolSize}")
	int handlerThreadPoolSize;
	@Value("${enableHttpFlv}")
	boolean enableHttpFlv;
	@Value("${keyword}")
	boolean keyword;
	
}
