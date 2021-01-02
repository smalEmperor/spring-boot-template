package com.template.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.template.common.BaseResult;
import com.template.util.RedisUtil;
import com.template.common.Result;
import com.template.entity.KabaMedicalOperation;
import com.template.entity.KabaUser;
import com.template.service.KabaMedicalOperationService;
import com.template.service.KabaUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试使用Controller,根据业务自行修改
 * @author df
 * @date 2019/8/19
 */
@Api(value="Controller描述信息",tags={"用户操作接口"})
@Validated
@Slf4j           //测试使用,日志一般要在service打印
@Controller
public class TestController {

    private  static int expireTime = 60;// redis中存储的过期时间 60s

    private final KabaMedicalOperationService kabaMedicalOperationServiceImpl;

    private final KabaUserService kabaUserServiceImpl;

    @Autowired
    public TestController(KabaMedicalOperationService kabaMedicalOperationServiceImpl,
                          KabaUserService kabaUserServiceImpl) {
        this.kabaMedicalOperationServiceImpl = kabaMedicalOperationServiceImpl;
        this.kabaUserServiceImpl = kabaUserServiceImpl;
    }

    @Resource
    private RedisUtil redisUtil;

    /**
     * 测试sharding jdbc单库操作
     * @param kabaMedicalOperation
     * @return
     */
    @ApiOperation("根据操作的人和操作的表查询操作记录数据")
    @PostMapping("/medicalOperations")
    @ResponseBody
    public Result medicalOperations(@Valid  @RequestBody KabaMedicalOperation kabaMedicalOperation) {
        List<KabaMedicalOperation> result = kabaMedicalOperationServiceImpl.getMedicalOperationBySelect(kabaMedicalOperation);
        if (result != null) {
            return BaseResult.requestSuccess("数据获取成功",result);
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
     * @return
     */
    @ApiOperation("根据id查询用户数据和操作记录")
    @GetMapping("/medicalOperationAndUser")
    @ResponseBody
    public Result medicalOperationAndUser(@NotNull(message = "userId不能为空") Long userId, @NotNull(message = "id不能为空") Long id) {
        KabaMedicalOperation medicalOperation = kabaMedicalOperationServiceImpl.getMedicalOperationById(id);
        KabaUser user =  kabaUserServiceImpl.getUser(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("medicalOperation",medicalOperation);
        result.put("user",user);
        return BaseResult.requestSuccess("数据获取成功",result);
    }

    /**
     * 测试sharding jdbc跨库操作
     * @param id
     * @return
     */
    @GetMapping("/user")
    @ResponseBody
    public Result getUser(@NotNull(message = "id不能为空") Long id) {
        KabaUser user =  kabaUserServiceImpl.getUser(id);
        return BaseResult.requestSuccess("数据获取成功",user);
    }

    /**
     * 测试mybatisplus分页
     * @return
     */
    @ApiOperation("根据id查询用户数据和操作记录")
    @GetMapping("/medicalOperationPage")
    @ResponseBody
    public Result medicalOperationPage() {
        IPage<KabaMedicalOperation> medicalOperation = kabaMedicalOperationServiceImpl.getMedicalOperationByPage();
        return BaseResult.requestSuccessPage("数据获取成功",medicalOperation);
    }

    /**
     * 测试跳转页面
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/setString")
    @ResponseBody
    public boolean redisSetString(){
        KabaUser user = KabaUser.builder().id(15L)
                .userEmail("0015")
                .userName("BlaineLi")
                .build();
        return redisUtil.set("userBean",user);
    }

    @RequestMapping("get/{key}")
    @ResponseBody
    public Object redisGet(@PathVariable("key") String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire/{key}")
    @ResponseBody
    public boolean expire(@PathVariable("key") String key){
        return redisUtil.expire(key,expireTime);
    }


    @RequestMapping("expire/get/{key}")
    @ResponseBody
    public Long getExpire(@PathVariable("key") String key){
        return redisUtil.getExpire(key );
    }

}
