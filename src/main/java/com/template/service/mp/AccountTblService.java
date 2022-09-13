package com.template.service.mp;


import com.template.entity.mp.AccountTbl;

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
