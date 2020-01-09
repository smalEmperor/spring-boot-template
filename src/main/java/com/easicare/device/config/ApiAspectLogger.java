package com.easicare.device.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 统一日志打印aop
 * @author df
 * @date 2019/7/9
 */
@Component
@Aspect
@Slf4j
public class ApiAspectLogger {

    /**
     * 执行service包下得代码进行必要的日志信息输出
     */
    private static final String POINT_CUT = "execution(* com.easicare.device.service..*.*(..))";

    /**
     * 执行方法封装
     */
    @Pointcut(POINT_CUT)
    private void pointcut(){}

    /**
     * 方法执行前的必要信息输出
     */
    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        StringBuilder sb = new StringBuilder();
        sb.append("调用方法: ").append(className).append("@").append(methodName).append(" , 参数: ");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            sb.append(arg.toString()).append(", ");
        }
        log.info(log.toString());
    }

    /**
     * 方法成功执行后必要信息输出
     */
    @AfterReturning(value = "pointcut()", returning = "returnObj")
    public void afterReturn(Object returnObj) {
        if (returnObj != null) {
            log.info("方法调用结果返回: "+returnObj.toString());
        } else {
            log.info("方法没有返回值");
        }
    }

    /**
     * 方法出现异常信息时日志信息输出
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e){
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        StringBuilder sb = new StringBuilder();
        sb.append("方法：").append(className).append("@").append(methodName).append("发生异常：");
        log.error(sb.append(e.fillInStackTrace()).toString(), e);
    }

}
