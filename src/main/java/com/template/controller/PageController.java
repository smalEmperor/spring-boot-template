package com.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制器
 *
 * @author 14328
 * @date 2021/12/22
 */
@Controller
public class PageController {

    /**
     * 测试跳转页面
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 测试跳转页面
     */
    @RequestMapping("/file")
    public String file() {
        return "file";
    }

    /**
     * 测试跳转页面
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
