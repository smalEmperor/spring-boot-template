package com.template.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 公共工具类
 * @date 2019/10/24
 */
public class CommonUtil {

    /**
     * 获取ip
     */
    protected String getRealIp(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
