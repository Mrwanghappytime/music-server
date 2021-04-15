package com.mrwang.happytime.musicserver.po;



import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@ToString
@Data
public class Comment {
    @MybatisMap("id")
    private Integer id;
    @MybatisMap("user_id")
    private Integer userId;
    @MybatisMap("song_id")
    private Integer songId;
    @MybatisMap("song_list_id")
    private Integer songListId;
    @MybatisMap("content")
    private String content;
    @MybatisMap("create_time")
    private Date createTime;
    @MybatisMap("type")
    private Byte type;
    @MybatisMap("up")
    private Integer up;

}