package com.template.service.mp.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.benmanes.caffeine.cache.Cache;
import com.template.entity.mp.OrderTbl;
import com.template.mapper.mp.OrderTblMapper;
import com.template.service.mp.OrderTblService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    Cache<String, Object> caffeineCache;

    @Override
    public OrderTbl getById() {
        return orderTblMapper.selectOne(Wrappers.<OrderTbl>lambdaQuery().eq(OrderTbl::getId, 1));
    }
}
