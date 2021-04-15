package com.mrwang.happytime.musicserver.vo;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result<T> {
    private int code;
    private String msg;
    private T avator;
    private Result(int code, String msg, T value) {
        this.code = code;
        this.msg = msg;
        this.avator = value;
    }
    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result getResult(int code, String msg){
        return getResult(code,msg,null);
    }
    public static Result getResult(int code, String msg,Object value){
        return new Result(code,msg,value);
    }

}
