package com.mrwang.happytime.musicserver.shiro.enums;

public enum LoginTypeEnum {
    CONSUMER("CONSUMER"),
    ADMIN("ADMIN");
    private String type;
    private LoginTypeEnum(String s){
        this.type = s;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
