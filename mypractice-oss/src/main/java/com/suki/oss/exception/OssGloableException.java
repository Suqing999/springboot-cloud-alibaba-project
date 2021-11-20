package com.suki.oss.exception;

import com.suki.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class OssGloableException {
    //只要捕获了Exception.class 就走这个方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result arithmeticError(Exception e){
        e.printStackTrace();
        return Result.error().setMessage("除0异常");
    }
    @ExceptionHandler(MyOssException.class)
    @ResponseBody
    public Result myerror(MyOssException e){
        e.printStackTrace();
        System.out.println(e.getCode());
        System.out.println(e.getMsg());
        return Result.error().setCode(e.getCode()).setMessage(e.getMsg());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().setMessage("全局异常");
    }

}
