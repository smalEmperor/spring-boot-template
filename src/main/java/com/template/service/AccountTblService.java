package com.template.service;

import com.template.entity.AccountTbl;

import java.util.List;

/**
 * (AccountTbl)表服务实现类
 *
 * @author dufa
 * @since 2021-12-22 16:47:51
 */
public interface AccountTblService{

    List<AccountTbl> getInfo();

    AccountTbl getById();
}
