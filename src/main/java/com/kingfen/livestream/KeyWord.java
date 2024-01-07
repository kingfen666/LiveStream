package com.kingfen.livestream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingfen.livestream.mylive.server.cfg.MyLiveConfig;
import com.kingfen.livestream.mylive.server.entities.StreamName;
import com.kingfen.livestream.sql.Live;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import static com.kingfen.livestream.LiveStreamApplication.liveMap;

@Slf4j
@Controller
public class KeyWord {
    public static void CanConnect(ChannelHandlerContext ctx, StreamName name){

        if (!MyLiveConfig.INSTANCE.isKeyword()){
            return;
        }
        if (liveMap.exists(new QueryWrapper<Live>().eq("uid",name.getApp()).eq("keyword",name.getName()))){
            log.info("用户{}开始推流",name.getApp());
        }else {
            ctx.channel().close();
        }
    }
}
