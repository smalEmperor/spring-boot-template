package com.template.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.template.common.BaseResult;
import com.template.common.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 页面控制器
 *
 * @author 14328
 * @date 2021/12/22
 */
@RestController
public class LoginController {

    /**
     * 测试跳转页面
     */
    @RequestMapping("/login")
    public BaseResult login(String name, String pwd) {
        // 第一步：比对前端提交的账号名称、密码
        if("zhang".equals(name) && "123456".equals(pwd)) {
            // 第二步：根据账号id，进行登录
            StpUtil.login(10001);
            return ResultUtil.ok("登录成功");
        }
        return ResultUtil.error("登录失败");
    }

    /**
     * 测试跳转页面
     */
    @RequestMapping("/isLogin")
    public BaseResult isLogin() {
        boolean login = StpUtil.isLogin();
        return ResultUtil.ok(login);
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public BaseResult tokenInfo() {
        return ResultUtil.ok(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public BaseResult logout() {
        StpUtil.logout();
        return ResultUtil.ok();
    }

}
