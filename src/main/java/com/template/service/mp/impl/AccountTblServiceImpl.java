package com.template.service.mp.impl;
 
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.template.entity.mp.AccountTbl;
import com.template.mapper.mp.AccountTblMapper;
import com.template.service.mp.AccountTblService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AccountTbl)表服务实现类
 *
 * @author dufa
 * @since 2021-12-22 16:47:51
 */
@Service
public class AccountTblServiceImpl implements AccountTblService {

     @Resource
    private AccountTblMapper accountTblMapper;

    @Override
    public List<AccountTbl> getInfo() {
        return accountTblMapper.selectList(null);
    }

    @Override
    public AccountTbl getById() {
        return accountTblMapper.selectOne(Wrappers.<AccountTbl>lambdaQuery().eq(AccountTbl::getId, 1));
    }
}
