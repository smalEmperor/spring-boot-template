package com.template.service.impl;
 
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.template.entity.AccountTbl;
import com.template.entity.OrderTbl;
import com.template.mapper.OrderTblMapper;
import com.template.service.OrderTblService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (OrderTbl)表服务实现类
 *
 * @author dufa
 * @since 2021-12-22 16:55:46
 */
@Service
public class OrderTblServiceImpl implements OrderTblService {

     @Resource
    private OrderTblMapper orderTblMapper;

    @Override
    public OrderTbl getById() {
        return orderTblMapper.selectOne(Wrappers.<OrderTbl>lambdaQuery().eq(OrderTbl::getId, 1));
    }
}
