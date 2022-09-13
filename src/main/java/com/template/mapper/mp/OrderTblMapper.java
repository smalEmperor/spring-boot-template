package com.template.mapper.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.template.entity.mp.OrderTbl;
import org.apache.ibatis.annotations.Mapper;

/**
 * (OrderTbl)表数据库访问层
 *
 * @author dufa
 * @since 2021-12-22 16:55:46
 */
 @Mapper
public interface OrderTblMapper extends BaseMapper<OrderTbl> {
 
}
