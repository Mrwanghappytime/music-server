package com.mrwang.happytime.musicserver.po;



import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@ToString
@Data
public class Consumer {
    @MybatisMap("id")
    private Integer id;
    @MybatisMap("username")
    private String username;
    @MybatisMap("password")
    private String password;
    @MybatisMap("sex")
    private Byte sex;
    @MybatisMap("phone_num")
    private String phone_num;
    @MybatisMap("email")
    private String email;
    @MybatisMap("birth")
    private String birth;
    @MybatisMap("introduction")
    private String introduction;
    @MybatisMap("location")
    private String location;
    @MybatisMap("avator")
    private String avator;
    @MybatisMap("create_time")
    private Date createTime;
    @MybatisMap("update_time")
    private Date updateTime;
}
