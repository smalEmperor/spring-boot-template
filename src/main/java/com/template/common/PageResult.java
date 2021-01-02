package com.template.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果封装工具类
 * @author df
 * @date 2019/8/6
 */
public class PageResult implements Serializable {
    /**
     * 封装分页结果
     * @param data 数据
     */
    public static Map<String, Object> requestPage(Page<?> data){
        Map<String, Object> resultPage = new HashMap<>(8);
        resultPage.put("pageNum", data.getCurrent());
        resultPage.put("pageSize", data.getSize());
        resultPage.put("total", data.getTotal());
        resultPage.put("pages", data.getPages());
        resultPage.put("list", data.getRecords());
        return resultPage;
    }
}
