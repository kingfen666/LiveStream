package com.kingfen.livestream.Control;

import com.kingfen.livestream.sql.Live;
import com.kingfen.livestream.sql.LiveMap;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

import static com.kingfen.livestream.LiveStreamApplication.liveMap;

@Controller
public class Web {
    @RequestMapping("add")
    @ResponseBody
    public String a(){
        Live live = new Live();
        live.setUid(10000001);
        live.setKeyword(UUID.randomUUID()+UUID.randomUUID().toString());
        liveMap.insert(live);
        return "OK";
    }
}
