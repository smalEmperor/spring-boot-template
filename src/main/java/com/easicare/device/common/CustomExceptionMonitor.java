package com.easicare.device.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义异常监控
 * @author df
 * @date 2019/8/6
 */
@Slf4j
public class CustomExceptionMonitor extends BaseResult {
    /**
     * 抛出自定义BusinessException异常
     */
    @ExceptionHandler({CustomException.class})
    @ResponseBody
    public Result business(CustomException ex) {
        log.info(ex.toString());
        return new Result(Result.ERROR,ex.getMessage());
    }

}
