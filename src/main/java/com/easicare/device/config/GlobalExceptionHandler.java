package com.easicare.device.config;

import com.easicare.device.common.BaseResult;
import com.easicare.device.common.CustomException;
import com.easicare.device.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        StringBuilder errorInfo = new StringBuilder();
        for (ConstraintViolation<?> item : violations) {
            errorInfo.append("参数").append(item.getMessage()).append("不能为空").append(",");
        }
        return BaseResult.requestErr(Result.BAD_REQUEST, errorInfo.toString());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMesssage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage.append("参数").append(fieldError.getDefaultMessage()).append("不能为空").append(",");
        }
        return BaseResult.requestErr(Result.BAD_REQUEST, errorMesssage.toString());
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
        return BaseResult.requestErr(404, "路径不存在，请检查路径是否正确");
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
