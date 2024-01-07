package com.kingfen.livestream.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("livepassword")
public class Live {
    Integer uid;
    String keyword;
}
