package com.easicare.device.common;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果封装工具类
 * @author df
 * @date 2019/8/6
 */
public class BaseResult implements Serializable {

    /**
     * 返回成功结果集
     */
    public static Result requestSuccess(Object data) {
        return new Result(Result.SUCCESS, Result.SUCCESSMSG, data);
    }

    /**
     * 返回成功结果
     */
    public static Result requestSuccess(String message) {
        return new Result(Result.SUCCESS, message);
    }

    /**
     * 返回成功结果和对象
     */
    public static Result requestSuccess(String message,Object data) {
        return new Result(Result.SUCCESS, message,data);
    }

    /**
     * 返回失败结果集
     */
    public static Result requestErr(String message) {
        return new Result(Result.ERROR, message);
    }

    /**
     * 自定义返回码失败结果集
     */
    public static Result requestErr(int errCode,String message) {
        return new Result(errCode, message);
    }

    /**
     * 封装分页结果
     * @param data 数据
     */
    public static Result requestSuccessPage(String message,IPage<?> data){
        Map<String, Object> resultObj = new HashMap<>(5);
        resultObj.put("pageNum",data.getCurrent());
        resultObj.put("pageSize",data.getSize());
        resultObj.put("totalCount",data.getTotal());
        resultObj.put("totalPage",data.getPages());
        resultObj.put("records", data.getRecords());
        return  new Result(Result.SUCCESS, message,resultObj);
    }

}
