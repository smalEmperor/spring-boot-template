package com.template.config;

import com.template.common.BaseResult;
import com.template.common.BusinessException;
import com.template.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 全局参数错误异常返回
 * @author df
 * @date 2019/9/16
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 这里是处理 @PathVariable和@RequestParam 验证不通过抛出的异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handle(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringJoiner errorInfo = new StringJoiner(",");
        for (ConstraintViolation<?> item : violations) {
            errorInfo.add(item.getMessage());
        }
        log.error("框架捕获到异常:[{}][{}]", Result.BAD_REQUEST, errorInfo);
        return BaseResult.requestErr(Result.BAD_REQUEST, errorInfo.toString());
    }

    /**
     * 这里处理@RequestBody,验证不通过抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringJoiner errorMessage = new StringJoiner(",");
        // 解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorMessage.add(error.getDefaultMessage());
        }
        log.error("框架捕获到异常:[{}][{}]", Result.BAD_REQUEST, errorMessage);
        return BaseResult.requestErr(Result.BAD_REQUEST, errorMessage.toString());
    }

    /**
     * 这里处理Get请求多参数实体封装,验证不通过抛出的异常
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        StringJoiner errorMsg = new StringJoiner(",");
        for (ObjectError error : bindingResult.getAllErrors()) {
            errorMsg.add(error.getDefaultMessage());
        }
        log.error("框架捕获到异常:[{}][{}]", Result.BAD_REQUEST, errorMsg);
        return  BaseResult.requestErr(Result.BAD_REQUEST,errorMsg.toString());
    }

    /**
     * 这里处理上传文件大小验证
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handlerMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        return BaseResult.requestErr(Result.BAD_REQUEST, "上传文件大小不能超过100M");
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result business(BusinessException ex) {
        log.error("框架捕获到异常:[{}][{}]", ex.getCode(), ex.getMessage());
        return new Result(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        // web返回错误页面
         /* ModelAndView mv = new ModelAndView();
        mv.addObject("系统繁忙,请稍后再试");
        mv.setViewName("err");
        return mv;*/
         // 接口返回提示
        return BaseResult.requestErr(404, "路径不存在,请检查路径是否正确");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
       /* ModelAndView mv = new ModelAndView();
        mv.addObject("系统繁忙,请稍后再试");
        mv.setViewName("err");
        return mv;*/
        log.error("框架捕获到异常:[{}][{}]", e.getMessage(), e.getStackTrace());
        return BaseResult.requestErr(500, "系统繁忙,请稍后再试");
    }

}
