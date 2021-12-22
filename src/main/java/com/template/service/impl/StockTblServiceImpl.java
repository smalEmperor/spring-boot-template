package com.template.service.impl;
 
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.template.entity.StockTbl;
import com.template.mapper.StockTblMapper;
import com.template.service.StockTblService;
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
