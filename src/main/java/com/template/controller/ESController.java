package com.template.controller;

import cn.hutool.json.JSONObject;
import com.template.common.BaseResult;
import com.template.common.ResultUtil;
import com.template.util.EsClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ESController {

    @Autowired
    private EsClientUtil esClientUtil;

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/index/{index}")
    public BaseResult index(@PathVariable String index) {
        boolean b = esClientUtil.createIndex(index);
        if (b) {
            return ResultUtil.ok();
        } else {
            return ResultUtil.error();
        }
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/index/exist/{index}")
    public BaseResult exist(@PathVariable String index) {
        boolean b = esClientUtil.existIndex(index);
        if (b) {
            return ResultUtil.ok();
        } else {
            return ResultUtil.error();
        }
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/index/delete/{index}")
    public BaseResult delete(@PathVariable String index) {
        boolean b = esClientUtil.deleteIndex(index);
        if (b) {
            return ResultUtil.ok();
        } else {
            return ResultUtil.error();
        }
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/index/create/{index}")
    public BaseResult create(@PathVariable String index) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("name", "张三");
        jsonObject.putOpt("age", 23);
        boolean b = esClientUtil.createDocument(index, "1", jsonObject.toString());
        if (b) {
            return ResultUtil.ok();
        } else {
            return ResultUtil.error();
        }
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/index/async/{index}")
    public BaseResult async(@PathVariable String index) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("name", "张四");
        map.put("age", 26);
        boolean b = esClientUtil.createAsyncDocument(index, "2", map);
        if (b) {
            return ResultUtil.ok();
        } else {
            return ResultUtil.error();
        }
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/doc/exist/{index}/{id}")
    public BaseResult doc(@PathVariable String index, @PathVariable String id) {
        boolean b = esClientUtil.isExist(index, id);
        if (b) {
            return ResultUtil.ok();
        } else {
            return ResultUtil.error();
        }
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/doc/get/{index}/{id}")
    public BaseResult get(@PathVariable String index, @PathVariable String id) {
        Map<String, Object> document = esClientUtil.getDocument(index, id);
        return ResultUtil.ok(document);
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/doc/update/{index}/{id}")
    public BaseResult update(@PathVariable String index, @PathVariable String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("name", "张三1");
        jsonObject.putOpt("age", 13);
        boolean b = esClientUtil.updateDocument(index, id, jsonObject.toString());
        return ResultUtil.ok(b);
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/doc/delete/{index}/{id}")
    public BaseResult docDelete(@PathVariable String index, @PathVariable String id) {
        boolean b = esClientUtil.deleteDocument(index, id);
        return ResultUtil.ok(b);
    }

}
