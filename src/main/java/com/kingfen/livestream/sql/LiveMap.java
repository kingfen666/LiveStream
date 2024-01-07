package com.kingfen.livestream.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Mapper
public interface LiveMap  extends BaseMapper<Live>{
}
