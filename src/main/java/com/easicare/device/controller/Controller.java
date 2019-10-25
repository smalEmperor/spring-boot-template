package com.easicare.device.controller;

import com.alibaba.fastjson.JSONObject;
import com.easicare.device.common.BaseResult;
import com.easicare.device.common.Result;
import com.easicare.device.entity.KabaMedicalOperation;
import com.easicare.device.entity.KabaUser;
import com.easicare.device.service.KabaMedicalOperationService;
import com.easicare.device.service.KabaUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 测试使用
 * @author df
 * @date 2019/8/19
 */
@Api(value="Controller描述信息",tags={"用户操作接口"})
@RestController
@Validated
@Slf4j           //测试使用,日期一般要在service打印
public class Controller {

    private final KabaMedicalOperationService kabaMedicalOperationServiceImpl;

    private final KabaUserService kabaUserServiceImpl;

    @Autowired
    public Controller(KabaMedicalOperationService kabaMedicalOperationServiceImpl,
                      KabaUserService kabaUserServiceImpl) {
        this.kabaMedicalOperationServiceImpl = kabaMedicalOperationServiceImpl;
        this.kabaUserServiceImpl = kabaUserServiceImpl;
    }

    /**
     * 测试sharding jdbc单库操作
     * @param kabaMedicalOperation
     * @return
     */
    @ApiOperation("根据操作的人和操作的表查询操作记录数据")
    @PostMapping("/medicalOperations")
    public Result medicalOperations(@Valid  @RequestBody KabaMedicalOperation kabaMedicalOperation) {
        List<KabaMedicalOperation> result = kabaMedicalOperationServiceImpl.getMedicalOperationBySelect(kabaMedicalOperation);
        if (result != null) {
            JSONObject data = new JSONObject();
            data.put("data",result);
            return BaseResult.requestSuccess("数据获取成功",data);
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
    public Result medicalOperationAndUser(@NotNull(message = "userId") Long userId, @NotNull(message = "id") Long id) {
        KabaMedicalOperation medicalOperation = kabaMedicalOperationServiceImpl.getMedicalOperationById(id);
        KabaUser user =  kabaUserServiceImpl.getUser(userId);
        JSONObject result = new JSONObject();
        result.put("medicalOperation",medicalOperation);
        result.put("user",user);
        return BaseResult.requestSuccess("数据获取成功",result);
    }

}
