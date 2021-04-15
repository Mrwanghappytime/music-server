package com.mrwang.happytime.musicserver.vo;

import com.mrwang.happytime.musicserver.po.Consumer;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;

import java.util.List;

@Data

public class ResultLogin {
    private int code;
    private String msg;
    private List<Consumer> userMsg;

    public ResultLogin(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultLogin(int code, String msg, List<Consumer> userMsg) {
        this.code = code;
        this.msg = msg;
        this.userMsg = userMsg;
    }

    public ResultLogin() {
    }
}
