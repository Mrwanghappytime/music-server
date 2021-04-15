package com.mrwang.happytime.musicserver.exception_handler;

import com.mrwang.happytime.musicserver.exception.AopRuntimeException;
import com.mrwang.happytime.musicserver.vo.Result;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AopRuntimeExceptionHandler {

    @ExceptionHandler(AopRuntimeException.class)
    @ResponseBody
    public Result exceptionHandler(AopRuntimeException e){
        //e.printStackTrace();
        return  Result.getResult(0,"请求参数不符合要求，请重新请求");
    }
    @ResponseBody
    @ExceptionHandler(ShiroException.class)
    public Result unknownAccountExceptionHandler(ShiroException e){
        return  Result.getResult(0,"登录失败" + e);
    }

}
