package com.kingfen.livestream;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.kingfen.livestream.sql.LiveMap;
import com.kingfen.livestream.mylive.server.Rtmp;
import com.kingfen.livestream.mylive.server.cfg.MyLiveConfig;
import com.kingfen.livestream.mylive.server.manager.StreamManager;
import com.kingfen.livestream.mylive.server.HttpFlvServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication

public class LiveStreamApplication {


     static Rtmp rtmp;
    public static LiveMap liveMap;
    static MyLiveConfig config;
    @Autowired
    private void auto(Rtmp rtmp, MyLiveConfig myLiveConfig,LiveMap liveMap){
        LiveStreamApplication.rtmp =rtmp;
        LiveStreamApplication.config=myLiveConfig;
        LiveStreamApplication.liveMap=liveMap;
    }

    public static StreamManager streamManager=new StreamManager();
    public static void main(String[] args) throws Exception {
        SpringApplication.run(LiveStreamApplication.class, args);
        MyLiveConfig.INSTANCE=config;
        new HttpFlvServer(MyLiveConfig.INSTANCE.getHttpFlvPort(),streamManager,128).run();
        rtmp.run(streamManager);
    }

}
