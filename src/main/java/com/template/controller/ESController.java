package com.template.controller;

import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import cn.easyes.core.conditions.LambdaEsUpdateWrapper;
import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONObject;
import com.template.common.BaseResult;
import com.template.common.ResultUtil;
import com.template.entity.es.Document;
import com.template.mapper.es.DocumentMapper;
import com.template.util.EsClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ESController {

    //@Autowired
    private EsClientUtil esClientUtil;

    @Autowired
    private DocumentMapper documentMapper;

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

    //.........................................................
    // esay-es

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/es/insert")
    public BaseResult insert() {
        // 测试插入数据
        Document document = new Document();
        document.setTitle("老汉");
        document.setContent("推*技术过硬");
        int successCount = documentMapper.insert(document);
        System.out.println(successCount);
        return ResultUtil.ok();
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @GetMapping("/es/select/{title}")
    public BaseResult select(@PathVariable String title) {
        // 测试查询
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Document::getTitle,title);
        Document document = documentMapper.selectOne(wrapper);
        return ResultUtil.ok(document);
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/es/update")
    public BaseResult update() {
        // 测试更新 更新有两种情况 分别演示如下:
        // case1: 已知id, 根据id更新 (为了演示方便,此id是从上一步查询中复制过来的,实际业务可以自行查询)
        String id = "oV2-NYMBmEKfsuAiBJLB";
        String title1 = "隔壁老王";
        /*Document document1 = new Document();
        document1.setId(id);
        document1.setTitle(title1);
        documentMapper.updateById(document1);*/

        // case2: id未知, 根据条件更新
        LambdaEsUpdateWrapper<Document> wrapper = new LambdaEsUpdateWrapper<>();
        wrapper.eq(Document::getTitle,title1);
        Document document2 = new Document();
        document2.setTitle("隔壁老李");
        document2.setContent("推*技术过软");
        documentMapper.update(document2,wrapper);
        return ResultUtil.ok();
    }

    /**
     * 测试sharding jdbc单库操作
     */
    @PostMapping("/es/delete")
    public BaseResult delete() {
        // 测试删除数据 删除有两种情况:根据id删或根据条件删
        // 鉴于根据id删过于简单,这里仅演示根据条件删,以老李的名义删,让老李心理平衡些
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        String title = "隔壁老李";
        wrapper.eq(Document::getTitle,title);
        int successCount = documentMapper.delete(wrapper);
        System.out.println(successCount);
        return ResultUtil.ok(successCount);
    }

}
