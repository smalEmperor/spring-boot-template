package com.template.controller;

import com.template.common.BaseResult;
import com.template.common.Result;
import com.template.entity.AccountTbl;
import com.template.entity.OrderTbl;
import com.template.entity.StockTbl;
import com.template.service.AccountTblService;
import com.template.service.OrderTblService;
import com.template.service.StockTblService;
import com.template.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试使用Controller,根据业务自行修改
 * @author df
 * @date 2019/8/19
 */
@Validated
@Slf4j
@RestController
public class AdviceController {

    private  static int expireTime = 60;// redis中存储的过期时间 60s

    private final AccountTblService accountTblService;

    private final OrderTblService orderTblService;

    private final StockTblService stockTblService;

    @Autowired
    public AdviceController(AccountTblService accountTblService,
                            OrderTblService orderTblService, StockTblService stockTblService) {
        this.accountTblService = accountTblService;
        this.orderTblService = orderTblService;
        this.stockTblService = stockTblService;
    }

    @Resource
    private RedisUtil redisUtil;

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/infos")
    public Result medicalOperations() {
        List<AccountTbl> result = accountTblService.getInfo();
        if (result != null) {
            return BaseResult.requestSuccess(result);
        } else {
            // 测试使用
            log.info("数据获取失败");
            return BaseResult.requestErr("数据获取失败");
        }
    }

    /**
     * 测试sharding jdbc跨库操作
     * @param userId
     * @param id
     */
    @GetMapping("/info")
    public Result medicalOperationAndUser(@NotNull(message = "userId不能为空") Long userId, @NotNull(message = "id不能为空") Long id) {
        AccountTbl accountTbl = accountTblService.getById();
        OrderTbl orderTbl =  orderTblService.getById();
        StockTbl stockTbl = stockTblService.getById();
        Map<String, Object> result = new HashMap<>();
        result.put("accountTbl",accountTbl);
        result.put("orderTbl",orderTbl);
        result.put("stockTbl",stockTbl);
        return BaseResult.requestSuccess(result);
    }

    @GetMapping("/setString")
    public boolean redisSetString(){
        AccountTbl user = AccountTbl.builder()
                .id(15)
                .userId("0015")
                .money(200)
                .build();
        return redisUtil.set("userBean",user);
    }

    @GetMapping("get/{key}")
    public Object redisGet(@PathVariable("key") String key){
        return redisUtil.get(key);
    }

    @GetMapping("expire/{key}")
    public boolean expire(@PathVariable("key") String key){
        return redisUtil.expire(key,expireTime);
    }


    @GetMapping("expire/get/{key}")
    public Long getExpire(@PathVariable("key") String key){
        return redisUtil.getExpire(key );
    }

}
