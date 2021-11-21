package com.suki.teacher.exception;

import com.suki.common.Result;
import com.suki.teacher.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //全局异常处理
public class GloableException {

    //只要捕获了Exception.class 就走这个方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result arithmeticError(Exception e){
        e.printStackTrace();
        return Result.error().setMessage("除0异常");
    }


    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result myerror(MyException e){
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
