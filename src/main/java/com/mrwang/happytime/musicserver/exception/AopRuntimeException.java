package com.mrwang.happytime.musicserver.exception;

import com.mrwang.happytime.musicserver.vo.Result;
import lombok.Data;

@Data
public class AopRuntimeException extends RuntimeException{
    private int code;
    private String msg;

    public AopRuntimeException(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
