package com.mrwang.happytime.musicserver.po;



import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@ToString
@Data
public class Singer {
    @MybatisMap("id")
    private Integer id;
    @MybatisMap("name")
    private String name;
    @MybatisMap("sex")
    private Byte sex;
    @MybatisMap("pic")
    private String pic;
    @MybatisMap("birth")
    private Date birth;
    @MybatisMap("location")
    private String location;
    @MybatisMap("introduction")
    private String introduction;

}
