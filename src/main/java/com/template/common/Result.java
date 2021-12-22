package com.template.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回结果封装
 *
 * @author df
 * @date 2019/8/6
 */
@Setter
@Getter
public class Result implements Serializable {

    /*|200 |请求成功(OK)，操作成功类信息码|
     |204 |没有内容，当一个动作成功执行，但没有任何内容可以返回，比如执行查询数据操作成功，但是数据库没有相关数据，可以返回204|
    |400 |请求无效 (Bad request)，参数类错误信息码|
    |401 |请求要求身份验证（Unauthorized）。对于需要token的接口，用户未认证，请求失败 |
    |403 |服务器拒绝请求（Forbidden）token失效或者权限类错误信息码 |
    |500 |服务器遇到错误，无法完成请求（Internal Server Error） |
    |512 |数据操作（增删改）失败类信息错误码|
    |513 |数据操作（查）失败类信息错误码|*/

    public static final int SUCCESS = 200;
    public static final int NO_DATA_SELECT = 204;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBINDDEN = 403;
    public static final int ERROR = 500;
    public static final int DATABASE_ERR = 512;
    public static final int DATABASE_SELECT_ERR = 513;
    public static final String SUCCESSMSG = "成功";

    private int code;

    private String msg;

    private Object data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.msg = "success";
        this.data = data;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
