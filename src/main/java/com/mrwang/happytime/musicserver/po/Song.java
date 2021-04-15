package com.mrwang.happytime.musicserver.po;



import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@ToString
@Data
public class Song {
    @MybatisMap("id")
    private Integer id;
    @MybatisMap("singer_id")
    private Integer singerId;
    @MybatisMap("name")
    private String name;
    @MybatisMap("introduction")
    private String introduction;
    @MybatisMap("create_time")
    private Date createTime;
    @MybatisMap("update_time")
    private Date updateTime;
    @MybatisMap("pic")
    private String pic;
    @MybatisMap("lyric")
    private String lyric;
    @MybatisMap("url")
    private String url;

}
