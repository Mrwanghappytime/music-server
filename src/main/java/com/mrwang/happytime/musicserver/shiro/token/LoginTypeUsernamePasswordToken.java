package com.mrwang.happytime.musicserver.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class LoginTypeUsernamePasswordToken extends UsernamePasswordToken {
    private String loginType;

    public LoginTypeUsernamePasswordToken(String username, String password, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
