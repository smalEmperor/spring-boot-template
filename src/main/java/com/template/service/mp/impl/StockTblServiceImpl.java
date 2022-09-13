package com.template.service.mp.impl;
 
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.template.entity.mp.StockTbl;
import com.template.mapper.mp.StockTblMapper;
import com.template.service.mp.StockTblService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (StockTbl)表服务实现类
 *
 * @author dufa
 * @since 2021-12-22 16:59:12
 */
@Service
public class StockTblServiceImpl implements StockTblService {

     @Resource
    private StockTblMapper stockTblMapper;


    @Override
    public StockTbl getById() {
        return stockTblMapper.selectOne(Wrappers.<StockTbl>lambdaQuery().eq(StockTbl::getId, 1));
    }
}
