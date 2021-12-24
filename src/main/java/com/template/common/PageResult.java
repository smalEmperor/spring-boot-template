package com.template.common;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回结果封装工具类
 * @author df
 * @date 2019/8/6
 */
public class PageResult implements Serializable {

    /**
     * 封装分页结果
     *
     * @param data 数据
     */
    public static BaseResult requestSuccessPage(IPage<?> data) {
        Map<String, Object> resultObj = new HashMap<>(8);
        resultObj.put("pageNum", data.getCurrent());
        resultObj.put("pageSize", data.getSize());
        resultObj.put("total", data.getTotal());
        resultObj.put("pages", data.getPages());
        resultObj.put("list", data.getRecords());
        return ResultUtil.ok(Result.SUCCESS, resultObj);
    }

    /**
     * 封装分页结果
     *
     * @param data 数据
     */
    public static BaseResult requestSuccessPage(IPage<?> data, List<?> list) {
        Map<String, Object> resultObj = new HashMap<>(8);
        resultObj.put("pageNum", data.getCurrent());
        resultObj.put("pageSize", data.getSize());
        resultObj.put("totalCount", data.getTotal());
        resultObj.put("totalPage", data.getPages());
        resultObj.put("records", list);
        return ResultUtil.ok(Result.SUCCESS, resultObj);
    }

}
