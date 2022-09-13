package com.template.controller;

import com.template.common.BaseResult;
import com.template.common.ResultUtil;
import com.template.entity.mp.AccountTbl;
import com.template.entity.mp.OrderTbl;
import com.template.entity.mp.StockTbl;
import com.template.service.mp.AccountTblService;
import com.template.service.mp.OrderTblService;
import com.template.service.mp.StockTblService;
import com.template.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试使用Controller,根据业务自行修改
 * @author df
 * @date 2019/8/19
 */
@Validated
@Slf4j
//@RestController
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

    //@Resource
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/infos")
    public BaseResult medicalOperations() {
        List<AccountTbl> result = accountTblService.getInfo();
        if (result != null) {
            return ResultUtil.ok(result);
        } else {
            // 测试使用
            log.info("数据获取失败");
            return ResultUtil.error("数据获取失败");
        }
    }

    /**
     * 测试sharding jdbc跨库操作
     */
    @GetMapping("/info")
    public BaseResult info(@NotNull(message = "userId不能为空") Long userId, @NotNull(message = "id不能为空") Long id) {
        AccountTbl accountTbl = accountTblService.getById();
        OrderTbl orderTbl =  orderTblService.getById();
        StockTbl stockTbl = stockTblService.getById();
        Map<String, Object> result = new HashMap<>();
        result.put("accountTbl",accountTbl);
        result.put("orderTbl",orderTbl);
        result.put("stockTbl",stockTbl);
        return ResultUtil.ok(result);
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

    @GetMapping("test")
    public Long test(){
        Long value = 1479269600171405314L;
        return value;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 模拟下单减库存的场景
     * @return
     */
    @GetMapping(value = "/duduct_stock")
    public String deductStock(){
        String lockKey = "product_001";
        // 1.获取锁对象
        RLock redissonLock = redissonClient.getLock(lockKey);
        try{
            // 2.加锁
            redissonLock.lock();  // 等价于 setIfAbsent(lockKey,"wangcp",10,TimeUnit.SECONDS);
            // 从redis 中拿当前库存的值
            String stockStr = stringRedisTemplate.opsForValue().get("stock");
            int stock = Integer.parseInt(stockStr);
            if(stock > 0){
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock",realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            }else{
                System.out.println("扣减失败，库存不足");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // 3.释放锁
            redissonLock.unlock();
        }
        return "end";
    }
}
