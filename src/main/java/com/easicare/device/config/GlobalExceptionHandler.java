package com.easicare.device.config;

import com.easicare.device.common.BaseResult;
import com.easicare.device.common.CustomException;
import com.easicare.device.common.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局参数错误异常返回
 * @author df
 * @date 2019/9/16
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 这里是处理 @PathVariable和@RequestParam 验证不通过抛出的异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handle(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder errorInfo = new StringBuilder();
        for (ConstraintViolation<?> item : violations) {
            errorInfo.append("参数").append(item.getMessage()).append(";");
        }
        return BaseResult.requestErr(Result.BAD_REQUEST, errorInfo.toString());
    }

    /**
     * 这里处理@RequestBody,验证不通过抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMesssage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage.append("参数").append(fieldError.getDefaultMessage()).append(";");
        }
        return BaseResult.requestErr(Result.BAD_REQUEST, errorMesssage.toString());
    }

    /**
     * 这里处理Get请求多参数实体封装,验证不通过抛出的异常
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            errorMsg.append("参数").append(error.getDefaultMessage()).append(";");
        }
        return  BaseResult.requestErr(Result.BAD_REQUEST,errorMsg.toString());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public Result business(CustomException ex) {
        return new Result(Result.ERROR,ex.getMessage());
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
        return BaseResult.requestErr(500, "系统繁忙,请稍后再试");
    }

}
