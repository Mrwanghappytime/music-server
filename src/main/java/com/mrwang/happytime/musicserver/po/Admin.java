package com.mrwang.happytime.musicserver.po;

import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Admin {
    @MybatisMap("id")
    private Integer id;
    @MybatisMap("name")
    private String name;
    @MybatisMap("password")
    private String password;

}