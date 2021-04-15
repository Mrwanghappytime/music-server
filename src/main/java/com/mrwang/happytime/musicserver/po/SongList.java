package com.mrwang.happytime.musicserver.po;


import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class SongList {
    @MybatisMap("id")
    private Integer id;
    @MybatisMap("title")
    private String title;
    @MybatisMap("pic")
    private String pic;
    @MybatisMap("style")
    private String style;
    @MybatisMap("introduction")
    private String introduction;

}
