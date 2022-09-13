package com.template.controller;

import com.template.common.BaseResult;
import com.template.common.ResultUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RBuckets;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 页面控制器
 *
 * @author 14328
 * @date 2021/12/22
 */
@RestController
public class RedissionTestController {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 测试跳转页面
     */
    @RequestMapping("/redisson/test")
    public BaseResult redissonTest(String name, String pwd) {
        RBucket<Object> backup = redissonClient.getBucket("redisson:string");
        backup.set(1);
        Object o = backup.get();
        RBuckets buckets = redissonClient.getBuckets();

        RList<Object> list = redissonClient.getList("redisson:list");
        return ResultUtil.ok(o);
    }
}
